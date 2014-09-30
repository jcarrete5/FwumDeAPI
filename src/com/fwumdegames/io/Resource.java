package com.fwumdegames.io;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Contains helper methods for retrieving resources from jar files
 * @author Jason Carrete
 * @since Aug 7, 2014
 */
public final class Resource
{
	/**
	 * Can't be instantiated
	 */
	private Resource() {}
	
	/**
	 * Returns a BufferedImage that sub-images can be made from.
	 * @param c The class that is in the same package as the sprite sheet.
	 * @param imgPath Path of the sprite sheet.
	 * @return A BufferedImage that represents a sprite sheet.
	 * @throws IOException
	 */
	public static BufferedImage getSpriteSheet(Class<?> c, String imgPath) throws IOException
	{
		return ImageIO.read(c.getResource(imgPath));
	}
	
	public static Image getImage(Class<?> c, String imgPath)
	{
		return new ImageIcon(c.getResource(imgPath)).getImage();
	}
}
