package com.fwumdegames.api.framework;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

/**
 * A basic game object
 * @author Ryan Goldstein
 */
public abstract class Entity
{
	private Rectangle2D.Double bounds;
	private Image texture;
	
	public Entity()
	{
		bounds = new Rectangle2D.Double();
	}
	
	public Entity(double x, double y, double width, double height)
	{
		bounds = new Rectangle2D.Double(x, y, width, height);
	}
	
	public void setX(double x)
	{
		bounds.x = x;
	}
	
	public void setY(double y)
	{
		bounds.y = y;
	}
	
	public void setWidth(double width)
	{
		bounds.width = width;
	}
	
	public void setHeight(double height)
	{
		bounds.height = height;
	}
	
	public double getX()
	{
		return bounds.x;
	}
	
	public double getY()
	{
		return bounds.y;
	}
	
	public boolean contains(double x, double y)
	{
		return bounds.contains(x, y);
	}
	
	public boolean contains(double x, double y, double width, double height)
	{
		return bounds.contains(x, y, width, height);
	}
	
	public boolean intersects(double x, double y, double width, double height)
	{
		return bounds.intersects(x, y, width, height);
	}
	
	public void setImage(Image img)
	{
		this.texture = img;
	}
	
	public Image getImage()
	{
		return texture;
	}
}
