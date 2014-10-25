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
	public Keyboard()
	{
		data = new HashMap<Integer, Boolean>();
	}
	
	public static boolean isDown(int keyCode)
	{
		return data.containsKey(keyCode) && data.get(keyCode);
	}
	
	@Override
	public void keyPressed(KeyEvent evt)
	{
		data.put(evt.getKeyCode(), true);
	}
		
	@Override
	public void keyReleased(KeyEvent evt)
	{
		data.put(evt.getKeyCode(), true);
	}
}
