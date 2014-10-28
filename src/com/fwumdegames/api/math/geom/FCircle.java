package com.fwumdegames.api.math.geom;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Represents a perfect circle.
 * @author Ryan Goldstein
 * @author Jason Carrete
 */
public class FCircle extends Ellipse2D.Double
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Center point of the circle.
	 */
	public Point2D.Double center;
	
	/**
	 * Radius of the circle.
	 */
	public double radius;
	
	/**
	 * Instantiates an FCircle object with the specified center point and radius.
	 * @param center The center coordinate of the circle.
	 * @param radius The radius of the circle.
	 */
	public FCircle(Point2D.Double center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Instantiates an FCircle object with the specified x and y coordinates and radius.
	 * @param x The x position of the center point.
	 * @param y The y position of the center point.
	 * @param radius The radius of the circle.
	 */
	public FCircle(double x, double y, double radius)
	{
		this(new Point2D.Double(x, y), radius);
	}
	
	/**
	 * Checks to see if any point of the parameterized FCircle is inside the calling FCircle.
	 * @param other The FCircle that is potentially intersecting the calling FCircle.
	 * @return True if the calling FCircle intersects the parameterized FCircle.
	 */
	public boolean intersects(FCircle other)
	{
		return (this.radius + other.radius) < center.distance(other.center); 
	}
	
	/**
	 * Checks to see if every point of the parameterized FCircle is completely inside the calling FCircle.
	 * @param other The FCircle that is potentially containing the calling FCircle.
	 * @return True if the every point in the parameterized FCircle is inside the calling FCircle.
	 */
	public boolean contains(FCircle other)
	{
		return (this.center.distance(other.center) + other.radius) < this.radius;
	}
}
