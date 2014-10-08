package com.fwumdegames.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * Represents an MIDI sound sequence
 * @author Ryan Goldstein
 */
public class MIDI implements Playable
{
	private File midiFile;
	public MIDI(String path)
	{
		midiFile = new File(path);
	}
	
	public MIDI(File file)
	{
		midiFile = file;
	}
	
	public void play()
	{
		try
		{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			InputStream input = new BufferedInputStream(new FileInputStream(midiFile));
			sequencer.setSequence(input);
			sequencer.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
