package com.fwumdegames.api.collision;

/**
 * Represents a circle
 * @author Ryan Goldstein
 */
public class FCircle
{
	public Vector2D center;
	public double radius;
	
	/**
	 * Creates an FCircle
	 * @param x The x of the center
	 * @param y The y of the center
	 * @param radius The radius
	 */
	public FCircle(double x, double y, double radius)
	{
		center = new Vector2D(x, y);
		this.radius = radius;
	}
	
	/**
	 * Creates an FCircle
	 * @param center The center of the circle
	 * @param radius The radius of the circle
	 */
	public FCircle(Vector2D center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Checks to see if a point (x, y) is in the calling object
	 * @param x The x coordinate of the point
	 * @param y The y coordinate of the point
	 * @return The point is inside the circle
	 */
	public boolean contains(double x, double y)
	{
		return center.distance(new Vector2D(x, y)) <= radius;
	}
	
	/**
	 * Checks to see if the parameterized object is in the calling object
	 * @param point The point to check if contained
	 * @return The point is inside the circle
	 */
	public boolean contains(Vector2D point)
	{
		return center.distance(point) <= radius;
	}
	
	/**
	 * Checks to see if any of the paramterized object is inside the calling object
	 * @param other The circle to check if contained
	 * @return If any of the paramterized circle is contained in the calling circle
	 */
	public boolean intersects(FCircle other)
	{
		return (this.radius + other.radius) < center.distance(other.center); 
	}
	
	/**
	 * Checks to see if any of the paramterized object is inside the calling object
	 * @param other The rectangle to check if contained
	 * @return If any of the paramterized rectangle's corners are contained in the circle
	 */
	public boolean intersects(FRectangle other)
	{
		return contains(other.x, other.y) || contains(other.x + other.width, other.y) ||
				contains(other.x, other.y + other.height) || contains(other.x + other.width, other.y + other.height);
	}
	
	/**
	 * Checks to see if the paramterized object is completely inside the calling object.
	 * @param other The rectangle to check if contained
	 * @return The paramterized rectangle is conatined within the calling circle
	 */
	public boolean contains(FRectangle other)
	{
		return contains(other.x, other.y) && contains(other.x + other.width, other.y) &&
				contains(other.x, other.y + other.height) && contains(other.x + other.width, other.y + other.height);
	}
	
	/**
	 * Checks to see if the paramterized object is completely inside the calling object.
	 * @param other The circle to check if contained
	 * @return The paramterized circle is contained within the calling circle
	 */
	public boolean contains(FCircle other)
	{
		return (this.center.distance(other.center) + other.radius) < this.radius;
	}
}
