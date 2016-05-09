package com.fwumdesoft.api.math.geom;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Represents a perfect circle.
 * @author Ryan Goldstein
 * @author Jason Carrete
 */
public class FCircle extends Ellipse2D.Double {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates an FCircle object with the specified x and y coordinates and radius.
	 * @param x The x position of the center point.
	 * @param y The y position of the center point.
	 * @param radius The radius of the circle.
	 */
	public FCircle(double x, double y, double radius) {
		super.x = x;
		super.y = y;
		super.width = radius * 2;
		super.height = radius * 2;
	}
	
	/**
	 * Instantiates an FCircle object with the specified center point and radius.
	 * @param center The center coordinate of the circle.
	 * @param radius The radius of the circle.
	 */
	public FCircle(Point2D.Double center, double radius) {
		this(center.x, center.y, radius);
	}
	
	/**
	 * Checks to see if any point of the parameterized FCircle is inside the calling FCircle.
	 * @param other The FCircle that is potentially intersecting the calling FCircle.
	 * @return True if the calling FCircle intersects the parameterized FCircle.
	 */
	public boolean intersects(FCircle other) {
		Point2D.Double thisCenter = new Point2D.Double(x, y);
		Point2D.Double otherCenter = new Point2D.Double(other.x, other.y);
		return (this.width / 2 + other.width / 2) > thisCenter.distance(otherCenter);
	}
	
	/**
	 * Checks to see if every point of the parameterized FCircle is completely inside the calling FCircle.
	 * @param other The FCircle that is potentially containing the calling FCircle.
	 * @return True if the every point in the parameterized FCircle is inside the calling FCircle.
	 */
	public boolean contains(FCircle other) {
		Point2D.Double thisCenter = new Point2D.Double(x, y);
		Point2D.Double otherCenter = new Point2D.Double(other.x, other.y);
		return (thisCenter.distance(otherCenter) + other.width / 2) < this.width / 2;
	}
}
