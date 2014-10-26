package com.fwumdegames.api.graphics;

import java.awt.Graphics;
import java.util.Random;

/**
 * Holds static methods for using effects with a Graphics object
 * @author Ryan Goldstein
 */
public class Effects
{
	//This class shouldn't be instantiated
	private Effects(){}
	
	/**
	 * Shakes the Graphics object
	 * @param g The object to shake
	 * @param max The maximum pixels to move per shake
	 */
	public static void shake(Graphics g, int max)
	{
		Random r = new Random();
		g.translate(r.nextInt(max * 2) - max, r.nextInt(max * 2) - max);
	}
}
