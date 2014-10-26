package com.fwumdegames.api.geom;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Holds methods that any shape object in the geom package should implement
 * @author Ryan Goldstein
 */
public abstract class FPolygon
{
	public abstract ArrayList<Point2D.Double> getVertices();
	public abstract boolean contains(Point2D.Double point);
	public boolean contains(FPolygon shape)
	{
		boolean contains = true;
		for(Point2D.Double vertex : shape.getVertices())
			contains = contains && this.contains(vertex);
		return contains;
	}
	public boolean intersects(FPolygon shape)
	{
		boolean intersects = true;
		for(Point2D.Double vertex : shape.getVertices())
			intersects = intersects || this.contains(vertex);
		return intersects;
	}
}
