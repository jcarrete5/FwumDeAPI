package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.io.Serializable;
import com.fwumdegames.api.collision.Vector2D;

/**
 * Represents an entity within the game
 * @author Ryan Goldstein
 */
public abstract class FEntity implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	protected FEnvironment parent;
	protected Vector2D position, velocity;
	
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
	
	@Override
	public void update(float delta)
	{
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
	}
	
	public void draw(Graphics g)
	{
		
	}
}
