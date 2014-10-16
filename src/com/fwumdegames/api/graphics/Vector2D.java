package com.fwumdegames.api.graphics;

/**
 * A vector that stores a change in x and y and a direction.
 * @author Jason Carrete
 * @since Oct. 15, 2014
 */
public class Vector2D
{
	public enum Direction {UP, RIGHT, DOWN, LEFT, NONE}
	
	public double x, y;
	private Direction dir;
	
	public Vector2D(Direction dir, double x_spd, double y_spd)
	{
		if(x_spd < 0 || y_spd < 0)
			throw new IllegalArgumentException("Speed cannot be a negative value");
		
		x = x_spd;
		y = y_spd;
		this.dir = dir;
	}
	
	public Vector2D(Direction dir)
	{
		this(dir, 0, 0);
	}
	
	public Vector2D()
	{
		this(Direction.NONE);
	}
	
	/**
	 * Copy constructor.
	 */
	public Vector2D(Vector2D v)
	{
		this(v.getDir(), v.x, v.y);
	}
	
	public void setDir(Vector2D.Direction dir)
	{
		this.dir = dir;
	}
	
	public Direction getDir()
	{
		return dir;
	}
}
