package com.fwumdesoft.api.framework;

import java.awt.Graphics2D;

/**
 * Runs the game logic without having to write your own game loop
 * If added to an FGame object, it will run at 60FPS or higher
 * @author Ryan
 */
public interface GameListener
{
	/**
	 * The logic called before the first update and paint
	 */
	public abstract void start();
	/**
	 * Draws the game to the FGame JPanel
	 * Occurs after updating every frame
	 * @param g The Graphics object to paint to
	 */
	public abstract void draw(Graphics2D g);
	/**
	 * Updates the game state
	 * Occurs before drawing every frame
	 */
	public abstract void update();
}
