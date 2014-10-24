package com.fwumdegames.api.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Represents an image with a hit-box and a vector.
 * @author Jason Carrete
 * @since Oct 20, 2014
 */
public class Sprite extends BufferedImage implements Serializable, Drawable
{
	private static final long serialVersionUID = -7599243419137335696L;
	
	public Rectangle hitbox;
	public Vector2D v;
	private Color bgcolor;
	private int stretchWidth, stretchHeight;

	public Sprite(BufferedImage bimg, double x_spd, double y_spd, int x, int y)
	{
		super(bimg.getWidth(), bimg.getHeight(), bimg.getType());
		setData(bimg.getData());
		
		hitbox = new Rectangle(x, y, getWidth(), getHeight());
		v = new Vector2D(x_spd, y_spd);
	}
	
	public Sprite(BufferedImage bimg, Vector2D v, int x, int y)
	{
		this(bimg, v.x, v.y, x, y);
	}
	
	public Sprite(BufferedImage bimg, Vector2D v)
	{
		this(bimg, v.x, v.y, 0, 0);
	}
	
	public Sprite(BufferedImage bimg)
	{
		this(bimg, 0.0, 0.0, 0, 0);
	}
	
	public void setBackgroundColor(Color background)
	{
		this.bgcolor = background;
	}
	
	public int getStretchWidth()
	{
		return stretchWidth;
	}
	
	public int getStretchHeight()
	{
		return stretchHeight;
	}
	
	public void setStretchWidth(int width)
	{
		this.stretchWidth = width;
	}
	
	public void setStretchHeight(int height)
	{
		this.stretchHeight = height;
	}
	
	/**
	 * Draws the sprite
	 * @param g
	 */
	public void draw(Graphics g)
	{
		if(bgcolor == null)
			if(stretchWidth == 0 && stretchHeight == 0)
				g.drawImage(this, (int) hitbox.x, (int) hitbox.y, null);
			else
				g.drawImage(this, (int) hitbox.x, (int) hitbox.y, stretchWidth, stretchHeight, null);
		else
			if(stretchWidth == 0 && stretchHeight == 0)
				g.drawImage(this, (int) hitbox.x, (int) hitbox.y, bgcolor, null);
			else
				g.drawImage(this, (int) hitbox.x, (int) hitbox.y, stretchWidth, stretchHeight, bgcolor, null);
	}	
	
	/**
	 * Applies the vector to the position of the sprite.
	 */
	public void apply()
	{
		hitbox.x += v.x;
		hitbox.y += v.y;
	}
}
