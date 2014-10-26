package com.fwumdegames.api.geom;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Represents a perfect circle.
 * @author Ryan Goldstein
 */
public class FCircle extends Ellipse2D.Double
{
	private static final long serialVersionUID = 8867108865417055042L;
	
	public Point2D.Double center;
	public double radius;
	
	/**
	 * Creates an FCircle
	 * @param center The center of the circle
	 * @param radius The radius of the circle
	 */
	public FCircle(Point2D.Double center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Creates an FCircle
	 * @param x The x of the center
	 * @param y The y of the center
	 * @param radius The radius
	 */
	public FCircle(double x, double y, double radius)
	{
		this(new Point2D.Double(x, y), radius);
	}
	
	/**
	 * Checks to see if any of the parameterized object is inside the calling object
	 * @param other The circle to check if contained
	 * @return If any of the parameterized circle is contained in the calling circle
	 */
	public boolean intersects(FCircle other)
	{
		return (this.radius + other.radius) < center.distance(other.center); 
	}
	
	/**
	 * Checks to see if the parameterized object is completely inside the calling object.
	 * @param other The circle to check if contained
	 * @return The parameterized circle is contained within the calling circle
	 */
	public boolean contains(FCircle other)
	{
		return (this.center.distance(other.center) + other.radius) < this.radius;
	}
}
