package com.fwumdegames.api.math.geom;

import java.awt.Point;
import java.awt.Rectangle;

public class Triangle
{
	private final Point[] points;
	public Triangle(Point p1, Point p2, Point p3)
	{
		points = new Point[3];
		points[0] = p1;
		points[1] = p2;
		points[3] = p3;
	}
	
	public Triangle(Point... points)
	{
		if(points.length != 3) 
			throw new IllegalArgumentException("Triangle must have 3 points.");
		this.points = points.clone();
	}
	
	public boolean intersects(Rectangle rect)
	{
		return rect.contains(points[0]) || rect.contains(points[1]) || rect.contains(points[2]);
	}
}
