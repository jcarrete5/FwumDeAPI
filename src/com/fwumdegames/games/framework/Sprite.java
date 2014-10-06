package com.fwumdegames.games.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
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
	private static final long serialVersionUID = -2002162758916094223L;
	private BufferedImage source;
	private Rectangle rect;
	
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
		super.paintComponent(g);
		g.drawImage(source,this.getX(), this.getY(), null);
	}
	
	public Rectangle getRectangle()
	{
		return rect;
	}
	
	public void dispose()
	{
		source.flush();
		System.gc();
	}
	
	private void setRectangle()
	{
		rect = new Rectangle(this.getX(),this.getY(), source.getWidth(), source.getHeight());
	}
}
