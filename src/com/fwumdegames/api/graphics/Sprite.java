package com.fwumdegames.api.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.fwumdegames.api.framework.Updatable;
import com.fwumdegames.api.math.geom.Vector2D;

/**
 * Represents an image with a hit-box and a vector.
 * @author Jason Carrete
 */
public class Sprite extends BufferedImage implements Serializable, Updatable
{
	private static final long serialVersionUID = -7599243419137335696L;
	
	public Rectangle2D.Double hitbox;
	public Vector2D v;
	private Color bgcolor;
	private int stretchWidth, stretchHeight;
	private double rotation;

	public Sprite(BufferedImage bimg, double x_spd, double y_spd, int x, int y)
	{
		super(bimg.getWidth(), bimg.getHeight(), bimg.getType());
		setData(bimg.getData());
		
		hitbox = new Rectangle2D.Double(x, y, getWidth(), getHeight());
		v = new Vector2D(x_spd, y_spd);
		rotation = 0;
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
	
	public double getRotation()
	{
		return rotation;
	}
	
	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}
	
	/**
	 * Draws the sprite
	 * @param g
	 */
	public void draw(Graphics g)
	{
		AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(rotation));
		((Graphics2D)g).transform(rotate);
		if(bgcolor == null)
			if(stretchWidth == 0 && stretchHeight == 0)
				g.drawImage(this, (int)hitbox.x, (int)hitbox.y, null);
			else
				g.drawImage(this, (int)hitbox.x, (int)hitbox.y, stretchWidth, stretchHeight, null);
		else
			if(stretchWidth == 0 && stretchHeight == 0)
				g.drawImage(this, (int)hitbox.x, (int)hitbox.y, bgcolor, null);
			else
				g.drawImage(this, (int) hitbox.x, (int) hitbox.y, stretchWidth, stretchHeight, bgcolor, null);
		//Reset rotation
		((Graphics2D)g).transform(new AffineTransform());
	}	
	
	@Override
	public void update(float deltaTime)
	{
		this.applyVector(deltaTime);
	}
	
	/**
	 * Applies the vector to the position of the sprite.
	 */
	public void applyVector(float deltaTime)
	{
		hitbox.x += v.x * deltaTime;
		hitbox.y += v.y * deltaTime;
	}
	
	/**
	 * Ensures the Sprite uses a compatible image type with the renderer
	 * @param image The image loaded from memory
	 * @return The image converted into a compatible type
	 */
	public static BufferedImage toCompatibleImage(BufferedImage image)
	{
		//obtain the current system graphical settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

		//If the image is already optimized, return it
		if(image.getColorModel().equals(gfx_config.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D)new_image.getGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return new_image; 
	}
}
