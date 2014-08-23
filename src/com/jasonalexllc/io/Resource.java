package com.jasonalexllc.io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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
	
	public static BufferedImage getSpriteSheet(Class c, String imgPath) throws IOException
	{
		return ImageIO.read(c.getResource(imgPath));
	}
}
