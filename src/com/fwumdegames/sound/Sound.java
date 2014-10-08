package com.fwumdegames.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author Ryan Goldstein
 * @since Oct 7 2014
 */
public class Sound implements Playable
{
	File soundFile;
	public Sound(String path)
	{
		soundFile = new File(path);
	}
	
	public Sound(File file)
	{
		soundFile = file;
		if(!soundFile.exists())
			System.out.println("POTATO");
	}
	
	public void play()
	{
		Runnable run = () ->
		{
			try
			{
				 AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
		         Clip clip = AudioSystem.getClip();
		         clip.open(ais);
		         clip.start();
		         int tts = (int)(clip.getFrameLength() / clip.getFormat().getFrameRate() * 1000);
		         Thread.sleep(tts + 500);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		};
		(new Thread(run)).start();
	}
}
