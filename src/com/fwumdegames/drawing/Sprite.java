package com.fwumdegames.drawing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Represents an image with an x and y location
 * @author Ryan Goldstein
 */
public class Sprite extends JComponent
{
	private static final long serialVersionUID = 461741156337833990L;
	
	private BufferedImage source;
	private Rectangle hitbox;
	private double angle, originX, originY;
	
	public Sprite(String path) throws IOException
	{
		source = ImageIO.read(new File(path));
		setRectangle();
	}
	
	public Sprite(File source) throws IOException
	{
		this.source = ImageIO.read(source);
		setRectangle();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		AffineTransform tx = AffineTransform.getRotateInstance(angle,originX, originY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		super.paintComponent(g);
		g.drawImage(op.filter(source, null),this.getX(), this.getY(), null);
	}
	
	public Rectangle getRectangle()
	{
		return hitbox;
	}
	
	public void dispose()
	{
		source.flush();
		System.gc();
	}
	
	private void setRectangle()
	{
		hitbox = new Rectangle(this.getX(),this.getY(), source.getWidth(), source.getHeight());
	}
}
