package com.fwumdegames.api.math;

import java.awt.geom.Point2D;
import com.fwumdegames.api.math.geom.Vector2;

/**
 * Holds the data for a linear function.
 * @author Ryan Goldstein
 */
public class Line extends Function
{
	State state;
	//Holds either the y-intercept or the x-coordinate of the vertical line
	double b;
	Vector2 slope;
	
	public Line(Vector2 slope, double b)
	{
		this.slope = slope;
		this.b = b;
	}
	
	public Line(Point2D.Double point, Vector2 slope)
	{
		this.slope = slope;
		b = point.y + point.x * (slope.y / slope.x);
		if(b == 1 / 0.0)
			b = point.x;
	}
	
	@Override
	public boolean contains(Point2D.Double point)
	{
		if(b == 1 / 0.0)
			return point.x == b;
		else
			return super.contains(point);
	}
	
	@Override
	protected double f(double x)
	{
		return x * (slope.y / slope.x) + b; 
	}
}
