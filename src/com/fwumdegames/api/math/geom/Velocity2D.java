package com.fwumdegames.api.math.geom;

import java.io.Serializable;
import com.fwumdegames.api.math.Fraction;

/**
 * Represents a vector that stores a change in x and y positions in two dimensions.
 * @author Jason Carrete
 * @author Ryan Goldstein
 */
public class Velocity2D implements Serializable, Cloneable
{
	private static final long serialVersionUID = -8436361899368277887L;

	/**
	 * Represents the speed(in pixels) a graphical object has when being drawn.
	 */
	public double x, y;
	
	/**
	 * Instantiates a Vector2D with a change in x and a change in y.
	 * @param x_spd Change in x.
	 * @param y_spd Change in y.
	 */
	public Velocity2D(double x_spd, double y_spd)
	{
		x = x_spd;
		y = y_spd;
	}
	
	/**
	 * Instantiates a Vector2D with a fraction (rise/run).
	 * @param m A Fraction
	 */
	public Velocity2D(Fraction m)
	{
		this(m.getDenom(), m.getNumer());
	}
	
	/**
	 * Calculates and returns the direction the vector is traveling in degrees.
	 * @return The direction the vector is traveling in degrees.
	 */
	public double getDirection()
	{
		return (360 + Math.toDegrees(Math.atan2(y, x))) % 360;
	}

	/**
	 * Swaps the x with the y of this Vector2D.
	 */
	public Velocity2D reciprocal()
	{
		double t = x;
		x = y;
		y = t;
		return this;
	}
	
	public void add(Velocity2D toAdd)
	{
		this.x += toAdd.x;
		this.y += toAdd.y;
	}
	
	public void add(Velocity2D toAdd, float deltaTime)
	{
		this.x += toAdd.x * deltaTime;
		this.y += toAdd.y * deltaTime;
	}
	
	/**
	 * Ensures the absolute value of x and y are less than or equal to 1
	 */
	public void simplify()
	{
		if(Math.abs(x) > 1 && Math.abs(y) > 1)
		{
			x /= y;
			y /= y;
			if(Math.abs(x) > 1)
			{
				x /= x;
				y /= x;
			}
		}
		else if(Math.abs(x) > 1)
		{
			x /= x;
			y /= x;
		}
		else if(Math.abs(y) > 1)
		{
			x /= y;
			y /= y;
		}
	}
	
	/**
	 * Tests whether both Vector2D's are equal.
	 * @return True if both vectors have equivalent x and y values.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Velocity2D)
		{
			Velocity2D v = (Velocity2D)o;
			return v.x == x && v.y == y;
		}
		
		return false;
	}
	
	@Override
	public Velocity2D clone()
	{
		return new Velocity2D(x, y);
	}
}
