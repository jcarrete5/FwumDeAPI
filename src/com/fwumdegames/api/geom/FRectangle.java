package com.fwumdegames.api.geom;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents a rectangle
 * @author Ryan Goldstein
 */
public class FRectangle implements Shape
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
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public Rectangle2D getBounds2D()
	{
		return getBounds();
	}

	@Override
	public boolean contains(double x, double y)
	{
		return x > this.x && x < this.width && y > this.y && y < this.width;
	}

	@Override
	public boolean contains(Point2D p)
	{
		return contains(p.getX(), p.getY());
	}

	@Override
	public boolean intersects(double x, double y, double w, double h)
	{
		return contains(x, y) || contains(x + w, y) || contains(x, y + h) 
				|| contains(x + w, y + h);
	}

	@Override
	public boolean intersects(Rectangle2D r)
	{
		return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	
	public boolean intersects(FRectangle fr)
	{
		return intersects(fr.x, fr.y, fr.width, fr.height);
	}

	@Override
	public boolean contains(double x, double y, double w, double h)
	{
		return contains(x, y) && contains(x + w, y) && contains(x, y + h) 
				&& contains(x + w, y + h);
	}

	@Override
	public boolean contains(Rectangle2D r)
	{
		return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	
	public boolean contains(FRectangle fr)
	{
		return contains(fr.x, fr.y, fr.width, fr.height);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
