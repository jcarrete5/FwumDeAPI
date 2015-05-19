package com.fwumdesoft.api.io;

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
	private static Keyboard instance;
	
	static
	{
		data = new HashMap<Integer, Boolean>();
		active = false;
		instance = new Keyboard();
	}
	
	private Keyboard()
	{
		
	}
	
	public static Keyboard getInstance()
	{
		return instance;
	}
	
	/**
	 * Checks to see if a key is currently being held down
	 * Will only work if there has been a Keyboard object added as a KeyListener
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
		active = true;
		data.put(evt.getKeyCode(), true);
	}
		
	@Override
	/**
	 * Record that the key is now released
	 */
	public void keyReleased(KeyEvent evt)
	{
		active = false;
		data.put(evt.getKeyCode(), false);
	}
}
