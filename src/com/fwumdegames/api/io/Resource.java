package com.fwumdegames.api.io;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Contains helper methods for retrieving resources from jar files
 * @author Jason Carrete
 * @since Oct 7, 2014
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
	public static BufferedImage getImageAsResource(Class<?> c, String imgPath) throws IOException
	{
		return ImageIO.read(c.getResource(imgPath));
	}
	
	/**
	 * Returns a File as a resource inside of a jar file.
	 * @param c The location of the class is used to find the File.
	 * @param path The name of the File to be found.
	 * @return A File in the jarfile.
	 */
	public static File getFile(Class<?> c, String path) throws URISyntaxException
	{
		return new File(c.getResource(path).toURI());
	}
	
	/**
	 * @deprecated
	 */
	public static Image getImage(Class<?> c, String imgPath)
	{
		return new ImageIcon(c.getResource(imgPath)).getImage();
	}
}
