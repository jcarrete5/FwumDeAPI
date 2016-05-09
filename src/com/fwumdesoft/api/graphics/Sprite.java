package com.fwumdesoft.api.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import com.fwumdesoft.api.math.geom.Vector2;

/**
 * Represents an image with a hit-box and a vector.
 * @author Jason Carrete
 */
public class Sprite extends BufferedImage implements Serializable {
	private static final long serialVersionUID = -7599243419137335696L;
	
	public final Rectangle2D.Double hitbox;
	public final Vector2 v;
	
	private Color bgcolor;
	private int stretchWidth, stretchHeight;
	private double rotation;
	
	public Sprite(final BufferedImage bimg, double x_spd, double y_spd, int x, int y) {
		super(bimg.getWidth(), bimg.getHeight(), bimg.getType());
		setData(bimg.getData());
		
		hitbox = new Rectangle2D.Double(x, y, getWidth(), getHeight());
		v = new Vector2(x_spd, y_spd);
		rotation = 0;
	}
	
	public Sprite(final BufferedImage bimg, final Vector2 v, int x, int y) {
		this(bimg, v.x, v.y, x, y);
	}
	
	public Sprite(final BufferedImage bimg, final Vector2 v) {
		this(bimg, v.x, v.y, 0, 0);
	}
	
	public Sprite(final BufferedImage bimg) {
		this(bimg, 0.0, 0.0, 0, 0);
	}
	
	/**
	 * Sets the background color
	 * @param background
	 */
	public void setBackgroundColor(final Color background) {
		this.bgcolor = background;
	}
	
	public int getStretchWidth() {
		return stretchWidth;
	}
	
	public int getStretchHeight() {
		return stretchHeight;
	}
	
	/**
	 * Sets how many pixels the image will take up when drawn
	 * @param width The pixel width of the drawn image
	 */
	public void setStretchWidth(final int width) {
		this.stretchWidth = width;
	}
	
	/**
	 * Sets how many pixels the image will take up when drawn
	 * @param height The pixel height of the drawn image
	 */
	public void setStretchHeight(final int height) {
		this.stretchHeight = height;
	}
	
	/**
	 * Gets how rotated the sprite is when drawn
	 * @return The rotation
	 */
	public double getRotation() {
		return rotation;
	}
	
	/**
	 * Rotate the sprite when drawn
	 * @param rotation The degrees to rotate
	 */
	public void setRotation(final double rotation) {
		this.rotation = rotation;
	}
	
	/**
	 * Draws the sprite
	 * NOTE: Mutates g by adding the sprite
	 * @param g The graphics object to draw to<br>
	 */
	public void draw(final Graphics g) {
		//Create the correct rotation
		AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(rotation));
		((Graphics2D)g).transform(rotate);
		if(bgcolor == null)
			g.drawImage(this, (int)hitbox.x, (int)hitbox.y, stretchWidth, stretchHeight, null);
		else
			g.drawImage(this, (int)hitbox.x, (int)hitbox.y, stretchWidth, stretchHeight, bgcolor, null);
		//Reset rotation
		((Graphics2D)g).transform(new AffineTransform());
	}
	
	/**
	 * Updates the state of the sprite
	 * @param deltaTime How many milliseconds have passed since the last update
	 */
	public void update(final float deltaTime) {
		this.applyVector(deltaTime);
	}
	
	/**
	 * Applies the vector to the position of the sprite.
	 */
	public void applyVector(final float deltaTime) {
		hitbox.x += v.x * deltaTime;
		hitbox.y += v.y * deltaTime;
	}
	
	/**
	 * Ensures the Sprite uses a compatible image type with the renderer
	 * @param image The image loaded from memory
	 * @return The image converted into a compatible type
	 */
	public static BufferedImage toCompatibleImage(final BufferedImage image) {
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
