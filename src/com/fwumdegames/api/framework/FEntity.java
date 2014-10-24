package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * Represents an entity within the game
 * @author Ryan Goldstein
 */
public abstract class FEntity implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	private FEnvironment parent;
	
	/**
	 * Creates a new entity
	 * @param parent The environment it is contained in
	 */
	public FEntity(FEnvironment parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Trigger the next FGame screen
	 */
	protected void nextScreen()
	{
		parent.nextScreen();
	}
	
	/**
	 * Reverts to the previous FGame screen
	 */
	protected void previousScreen()
	{
		parent.previousScreen();
	}
	
	public void draw(Graphics g)
	{
		
	}
	
	public abstract void update(float deltaTime);
}
