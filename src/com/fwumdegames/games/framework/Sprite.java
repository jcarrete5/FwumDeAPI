package com.fwumdegames.games.framework;

import java.awt.Graphics;
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
	
	public Sprite(String path) throws IOException
	{
		source = ImageIO.read(new File(path));
	}
	
	public Sprite(File source) throws IOException
	{
		this.source = ImageIO.read(source);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(source,this.getX(), this.getY(), null);
	}
}
