package com.fwumdegames.api.collision;

import java.io.Serializable;
import com.fwumdegames.api.math.Fraction;

/**
 * A vector that stores a change in x and y positions in two dimensions.
 * @author Jason Carrete
 * @since Oct. 15, 2014
 */
public class Vector2D implements Serializable
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
	 * Copy constructor.
	 */
	public Vector2D(Vector2D v)
	{
		this(v.x, v.y);
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
	 * Sets the x_spd to the y_spd and vice versa.
	 */
	public void inverse()
	{
		double t = x;
		x = y;
		y = t;
	}
}
