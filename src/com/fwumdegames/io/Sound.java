package com.fwumdegames.io;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Creates a Sound object that can play a sound.
 * @author Ryan Goldstein, Jason Carrete
 * @since Oct 8 2014
 */
public class Sound
{
	private AudioInputStream ais;
	private Clip clip;
	private Thread activeThread;
	
	/**
	 * Instantiates a new Sound object from the specified file.
	 * @param soundFile The audio file the be used.
	 */
	public Sound(File soundFile) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		ais = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
	}
	
	/**
	 * Instantiates a new Sound object from the specified String.
	 * @param path Path to the audio file.
	 */
	public Sound(String path) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		this(new File(path));
	}
	
	/**
	 * Plays the sound once. Can also be used to resume the sound after it has been paused.
	 */
	public void play() throws LineUnavailableException, IOException
	{
		if(!clip.isOpen())
			clip.open(ais);
		
		if(!clip.isRunning())
		{
			clip.start();
			active(1);
		}
	}
	
	/**
	 * Plays the sound <tt>count</tt> times.
	 * @param count Number of times the audio should loop.
	 */
	public void loop(int count) throws LineUnavailableException, IOException
	{
		if(!clip.isOpen())
			clip.open(ais);
		
		if(!clip.isRunning())
		{
			clip.loop(count);
			active(count);
		}
	}
	
	/**
	 * Pauses the sound from playing. Has no effect if the sound has already been paused.
	 */
	public void pause()
	{
		if(clip.isRunning())
			activeThread.interrupt();
	}
	
	/**
	 * Stops the <tt>activeThread</tt> and flushes the AudioBuffer of the sound file.<br>
	 * Renders this object useless.
	 */
	public void close() throws IOException
	{
		activeThread.interrupt();
		clip.flush();
		clip.close();
		clip = null;
		ais.close();
		ais = null;
		System.gc();
	}
	
	/**
	 * Runs the activeThread which guarantees that the sound will be playable.<br>
	 * The thread will loop <tt>TIMES</tt> times.
	 */
	private void active(final int TIMES)
	{
		Runnable active = () ->
		{
			int count = TIMES;
			
			int ms = 10000;
			if(count != Clip.LOOP_CONTINUOUSLY)
			{
				//calculates how many more ms it will take before the sound ends
				long framesLeft = clip.getFrameLength() - clip.getLongFramePosition();
				ms = (int)(framesLeft / clip.getFormat().getFrameRate() * 1000);
			}
			
			try
			{
				do
				{
					Thread.sleep(ms);
					count--;
				}
				while(count > 0 || TIMES == Clip.LOOP_CONTINUOUSLY);
			}
			catch(InterruptedException e)
			{
				clip.stop();
			}
		};
		
		activeThread = new Thread(active);
		activeThread.start();
	}
}
