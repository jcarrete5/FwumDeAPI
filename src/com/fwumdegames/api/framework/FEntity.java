package com.fwumdegames.api.framework;

import java.io.Serializable;

/**
 * Represents an entity within the game
 * @author Ryan Goldstein
 */
public abstract class FEntity implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	private FEnvironment parent;
	
	public FEntity(FEnvironment parent)
	{
		this.parent = parent;
	}
	
	protected void nextScreen()
	{
		parent.nextScreen();
	}
	
	protected void previousScreen()
	{
		parent.previousScreen();
	}
	
	public abstract void update(float deltaTime);
}
