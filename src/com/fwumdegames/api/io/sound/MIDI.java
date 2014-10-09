package com.fwumdegames.api.io.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.LineUnavailableException;

/**
 * Represents an MIDI sound sequence.
 * @author Ryan Goldstein
 * @author Jason Carrete
 */
public class MIDI extends AbstractSound
{
	private Sequencer seq;
	private InputStream in;
	
	public MIDI(File file) throws MidiUnavailableException, IOException, InvalidMidiDataException
	{
		seq = MidiSystem.getSequencer();
		in = new BufferedInputStream(new FileInputStream(file));
		seq.setSequence(in);
	}
	
	public MIDI(String path) throws MidiUnavailableException, IOException, InvalidMidiDataException
	{
		this(new File(path));
	}
	
	@Override
	public void open() throws LineUnavailableException, IOException, MidiUnavailableException
	{
		seq.open();
	}
	
	@Override
	public void play() throws LineUnavailableException, IOException, MidiUnavailableException
	{
		if(!seq.isOpen())
			open();
		
		if(seq.isRunning())
		{
			seq.setLoopCount(0);
			seq.start();
			active(1);
		}
	}
	
	@Override
	public void loop(int count) throws LineUnavailableException, IOException, MidiUnavailableException
	{
		if(!seq.isOpen())
			open();
		
		if(!seq.isRunning())
		{
			seq.setLoopCount(count);
			seq.start();
			active(count);
		}
	}
	
	@Override
	public void pause()
	{
		if(seq.isRunning())
			activeThread.interrupt();
	}
	
	@Override
	public void setMicrosecondPosition(long µs)
	{
		seq.setMicrosecondPosition(µs);
	}
	
	@Override
	public void close() throws IOException
	{
		activeThread.interrupt();
		seq.close();
		in.close();
		seq = null;
		in = null;
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
			//calculates how many more ms it will take before the sound ends
			long ms = (seq.getMicrosecondLength() - seq.getMicrosecondPosition()) / 1000;
			if(TIMES != AbstractSound.LOOP_CONTINUOUSLY)
				ms *= TIMES;
			
			try
			{
				do
				{
					Thread.sleep(ms);
				}
				while(TIMES == AbstractSound.LOOP_CONTINUOUSLY);
			}
			catch(InterruptedException e)
			{
				seq.stop();
			}
		};
		
		activeThread = new Thread(active);
		activeThread.start();
	}
}
