package com.fwumdegames.api.io;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

/**
 * Contains helper methods for retrieving resources from jar files or files from a directory.
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
	
	public static BufferedImage getImage(String imgPath) throws IOException
	{
		return ImageIO.read(new File(imgPath));
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
	 * Returns an InputStream from a file inside a jar file
	 * @param c The location of the class that is used to find the file
	 * @param path The path to the file relative to the class
	 * @return The input stream created from the file
	 */
	public static InputStream getStream(Class<?> c, String path) 
	{
		return c.getResourceAsStream(path);
	}
	
	/**
	 * Converts an image to a format compatible with the device display
	 * @param image The original image
	 * @return An image with the same content but a compatible format with the display
	 */
	public static BufferedImage toCompatibleImage(BufferedImage image)
	{
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return new_image; 
	}
}
