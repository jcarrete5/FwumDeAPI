package com.fwumdesoft.api.sound;

import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import com.badlogic.gdx.utils.Disposable;

/**
 * All classes in this package extend AbstractSound.java
 * @author Jason Carrete
 * @since Oct 9, 2014
 */
public abstract class AbstractSound implements Disposable
{
	public static final int LOOP_CONTINUOUSLY = Clip.LOOP_CONTINUOUSLY;
	
	protected Thread activeThread;
	
	/**
	 * Opens the clip to be played and makes it ready to be read.<br>
	 * Note: This call is not necessary since loop() and play() both will call open() if the clip isn't already opened.
	 */
	public abstract void open() throws LineUnavailableException, IOException, MidiUnavailableException;
	
	/**
	 * Plays the sound once. Can also be used to resume the sound after it has been paused.
	 */
	public abstract void play() throws LineUnavailableException, IOException, MidiUnavailableException;
	
	/**
	 * Plays the sound <tt>count</tt> times.
	 * @param count Number of times the audio should loop.
	 */
	public abstract void loop(int count) throws LineUnavailableException, IOException, MidiUnavailableException;
	
	/**
	 * Pauses the sound from playing. Has no effect if the sound has already been paused.
	 */
	public abstract void pause();
	
	
	/**
	 * Stops the <tt>activeThread</tt> and flushes the AudioBuffer of the sound file.<br>
	 * Renders this object useless.
	 */
	public abstract void close() throws IOException;
}
