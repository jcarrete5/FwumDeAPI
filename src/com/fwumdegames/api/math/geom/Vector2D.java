package com.fwumdegames.api.math.geom;

import java.awt.geom.Point2D;
import java.io.Serializable;
import com.fwumdegames.api.math.Fraction;

/**
 * Represents a vector that stores a change in x and y positions in two dimensions.
 * @author Jason Carrete
 * @author Ryan Goldstein
 */
public class Vector2D extends Point2D implements Serializable
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
	public Vector2D(double x_spd, double y_spd)
	{
		x = x_spd;
		y = y_spd;
	}
	
	/**
	 * Instantiates a Vector2D with a fraction (rise/run).
	 * @param m A Fraction
	 */
	public Vector2D(Fraction m)
	{
		this(m.getDenominator(), m.getNumerator());
	}
	
	/**
	 * Calculates and returns the direction the vector is traveling in degrees.
	 * @return The direction the vector is traveling in degrees.
	 */
	public double getDirection()
	{
		double degBoost = 0.0;
		
		if(x > 0 && y < 0)
			degBoost += 90.0;
		else if(x < 0 && y < 0)
			degBoost += 180.0;
		else if(x < 0 && y > 0)
			degBoost += 270.0;
		
		double degrees;
		if(x == 0)
			degrees = 0;
		else
		{
			double slopePercent = Math.abs(y / x);
			degrees = Math.toDegrees(Math.atan(slopePercent)) + degBoost;
		}
		return degrees;
	}

	/**
	 * Swaps the x with the y of this Vector2D.
	 */
	public Vector2D reciprocal()
	{
		double t = x;
		x = y;
		y = t;
		return this;
	}
	
	public void add(Vector2D toAdd)
	{
		this.x += toAdd.x;
		this.y += toAdd.y;
	}
	
	public void add(Vector2D toAdd, float deltaTime)
	{
		this.x += toAdd.x * deltaTime;
		this.y += toAdd.y * deltaTime;
	}

	@Override
	public double getX()
	{
		return x;
	}
	
	@Override
	public double getY()
	{
		return y;
	}

	@Override
	public void setLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
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
		else
			if(Math.abs(x) > 1)
			{
				x /= x;
				y /= x;
			}
			else
				if(Math.abs(y) > 1)
				{
					x /= y;
					y /= y;
				}
	}
	
	/**
	 * Tests whether both Vector2D's are equal.
	 * @return True if the difference between the x and y of both Vector2D's is less than 0.000001f;
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Vector2D)
		{
			Vector2D v = (Vector2D)o;
			float xDiff = (float)Math.abs(this.x - v.x), yDiff = (float)Math.abs(this.y - v.y);
			return xDiff < 0.000001f && yDiff < 0.000001f;
		}
		
		return false;
	}
}
