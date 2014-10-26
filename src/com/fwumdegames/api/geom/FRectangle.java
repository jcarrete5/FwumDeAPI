package com.fwumdegames.api.geom;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Represents a rectangle
 * @author Ryan Goldstein
 */
public class FRectangle extends FPolygon
{
	public double x, y, width, height;
	
	public FRectangle(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public ArrayList<Point2D.Double> getVertices()
	{
		ArrayList<Point2D.Double> vertices = new ArrayList<Point2D.Double>();
		vertices.add(new Point2D.Double(x, y));
		vertices.add(new Point2D.Double(x + width, y));
		vertices.add(new Point2D.Double(x, y + height));
		vertices.add(new Point2D.Double(x + width, y + height));
		return vertices;
	}

	@Override
	public boolean contains(Point2D.Double point)
	{
		return point.x > x && point.x < x + width && point.y > y && point.y > y + height;
	}

}
