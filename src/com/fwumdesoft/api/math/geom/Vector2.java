package com.fwumdesoft.api.math.geom;

import java.io.Serializable;
import com.fwumdesoft.api.math.Fraction;

/**
 * Represents a vector that stores a change in x and y positions in two dimensions.
 * @author Jason Carrete
 * @author Ryan Goldstein
 */
public class Vector2 implements Serializable, Cloneable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the speed(in pixels) a graphical object has when being drawn.
	 */
	public double x, y;
	
	/**
	 * Instantiates a Vector2 with a change in x and a change in y.
	 * @param x_spd Change in x.
	 * @param y_spd Change in y.
	 */
	public Vector2(double x_spd, double y_spd)
	{
		x = x_spd;
		y = y_spd;
	}
	
	/**
	 * Instantiates a Vector2 with a fraction (rise/run).
	 * @param m A Fraction
	 */
	public Vector2(Fraction m)
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
	 * Swaps the x with the y of this Vector2.
	 * @return A new Vector2 that is this Vector2's reciprocal.
	 */
	public Vector2 reciprocal()
	{
		return new Vector2(y, x);
	}
	
	public void add(Vector2 toAdd)
	{
		this.x += toAdd.x;
		this.y += toAdd.y;
	}
	
	public void add(Vector2 toAdd, float deltaTime)
	{
		this.x += toAdd.x * deltaTime;
		this.y += toAdd.y * deltaTime;
	}
	
	/**
	 * Normalizes this Vector2.
	 * @return A new Vector2 that is the normalized version of this one.
	 */
	public Vector2 normalize()
	{
		double len = Math.sqrt(x*x + y*y);
		return new Vector2(x / len, y / len);
	}
	
	/**
	 * Tests whether both Vector2's are equal.
	 * @return True if both vectors have equivalent x and y values.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Vector2)
		{
			Vector2 v = (Vector2)o;
			return v.x == x && v.y == y;
		}
		
		return false;
	}
	
	@Override
	public Vector2 clone()
	{
		return new Vector2(x, y);
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
