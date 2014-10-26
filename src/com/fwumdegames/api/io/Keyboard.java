package com.fwumdegames.api.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Holds the data for the keyboard
 * @author Ryan Goldstein
 */
public class Keyboard extends KeyAdapter
{
	private static HashMap<Integer, Boolean> data;
	private static boolean active;
	static
	{
		data = new HashMap<Integer, Boolean>();
		active = false;
	}
	
	/**
	 * Creates a new Keyboard object
	 * @throws KeyboardInstantiatedException Only one keyboard may be instantiated to avoid concurrency errors
	 */
	public Keyboard() throws KeyboardInstantiatedException
	{
		if(active)
			throw new KeyboardInstantiatedException();
		else
			active = true;
	}
	
	/**
	 * Checks to see if a key is currently being held down
	 * Will only work if there has been a Keyboard object added as a KeyListener
	 * NOTE: FGame will automatically add a Keyboard listener
	 * @param keyCode The key constant from KeyEvent
	 * @return The key is being held down on the keyboard
	 */
	public static boolean isDown(int keyCode)
	{
		return data.containsKey(keyCode) && data.get(keyCode);
	}
	
	/**
	 * Checks to see if there is an activated keyboard
	 * @return If there is currently a keyboard object
	 */
	public static boolean keyboardActive()
	{
		return active;
	}
	
	@Override
	public void finalize() throws Throwable
	{
		super.finalize();
		active = false;
	}
	
	@Override
	/**
	 * Record that the key is now pressed
	 */
	public void keyPressed(KeyEvent evt)
	{
		data.put(evt.getKeyCode(), true);
	}
		
	@Override
	/**
	 * Record that the key is now released
	 */
	public void keyReleased(KeyEvent evt)
	{
		data.put(evt.getKeyCode(), true);
	}
	
	public class KeyboardInstantiatedException extends Exception
	{	private static final long serialVersionUID = 1L; }
}
