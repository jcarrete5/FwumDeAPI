package com.fwumdegames.io;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Ryan Goldstein
 * @since Oct 7 2014
 */
public class Sound
{
	File soundFile;
	public Sound(String path)
	{
		soundFile = new File(path);
	}
	
	public Sound(File file)
	{
		soundFile = file;
	}
	
	public void play() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException
	{
		 AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
         Clip clip = AudioSystem.getClip();
         clip.open(ais);
         clip.start();
         int tts = (int)(clip.getFrameLength() / clip.getFormat().getFrameRate() * 1000);
         Thread.sleep(tts + 500);
	}
}
