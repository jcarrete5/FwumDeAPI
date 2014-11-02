package com.fwumdegames.api.math.geom;

/**
 * Holds the data for a linear function.
 * @author Ryan Goldstein
 */
public class Line extends Function
{
	State state;
	double b;
	Vector2D slope;
	
	public Line(Vector2D slope, double b)
	{
		this.slope = slope;
		this.b = b;
	}
	
	@Override
	protected double f(double x)
	{
		return x * (slope.y / slope.x) + b; 
	}
}
