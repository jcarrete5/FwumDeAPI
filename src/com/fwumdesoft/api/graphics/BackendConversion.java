package com.fwumdesoft.api.graphics;

import java.awt.image.BufferedImage;

import com.badlogic.gdx.graphics.Pixmap;

/**
 * Converts LibGDX resources into Java2D resources, or vice-versa <br>
 * All conversion are extremely slow and ill-advised <br>
 * @author Ryan Goldstein
 */
public class BackendConversion
{
	public Pixmap toLibgdx(BufferedImage image)
	{
		Pixmap pix = new Pixmap(image.getWidth(), image.getHeight(), Pixmap.Format.RGBA8888);
		for(int i = 0; i < image.getWidth(); i++)
			for(int j = 0; j < image.getHeight(); j++)
				pix.drawPixel(i, j, image.getRGB(i, j));
		return pix;
	}
}
