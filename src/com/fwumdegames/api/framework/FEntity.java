package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import com.fwumdegames.api.math.geom.Vector2D;

/**
 * Represents an entity within the game
 * @author Ryan Goldstein
 */
public abstract class FEntity implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	protected FEnvironment parent;
	protected Vector2D velocity;
	protected Point2D.Double pos;
	protected Rectangle2D.Double hitbox;
	
	/**
	 * Creates a new entity
	 * @param parent The environment it is contained in
	 */
	public FEntity(FEnvironment parent)
	{
		this.parent = parent;
		pos = new Point2D.Double(0, 0);
		velocity = new Vector2D(0,0);
		hitbox = new Rectangle2D.Double(0,0,0,0);
	}
	
	public FEntity(FEnvironment parent, double width, double height)
	{
		this(parent);
		hitbox.width = width;
		hitbox.height = height;
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
		pos.x += velocity.x * delta;
		pos.y += velocity.y * delta;
		hitbox.x = pos.x;
		hitbox.y = pos.y;
	}
	
	public void setX(double x)
	{
		pos.x = x;
		hitbox.x = x;
	}
	
	public void setY(double y)
	{
		pos.y = y;
		hitbox.y = y;
	}
	
	public double getX()
	{
		return pos.x;
	}
	
	public double getY()
	{
		return pos.y;
	}
	
	public Rectangle2D getHitbox()
	{
		return hitbox;
	}
	
	public boolean colliding(FEntity other)
	{
		return hitbox.intersects(other.hitbox);
	}
	
	public abstract void draw(Graphics g);
}
