package com.fwumdesoft.api.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.fwumdesoft.api.io.Resource;

public class FTexture implements Disposable
{
	public BufferedImage image;
	public final Texture texture;
	private Backend backend;
	public FTexture(BufferedImage source)
	{
		image = source;
		texture = null;
		backend = Backend.Java2d;
	}
	
	public FTexture(Texture tex)
	{
		texture = tex;
		image = null;
		backend = Backend.Libgdx;
	}
	
	public FTexture(String classpath, Class<?> c)
	{
		backend = Backend.Java2d;
		texture = null;
		try
		{
			image = Resource.getImageAsResource(c, classpath);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public FTexture(String classpath)
	{
		texture = new Texture(Gdx.files.classpath(classpath));
		image = null;
		backend = Backend.Libgdx;
	}
	
	public int getWidth()
	{
		switch(backend)
		{
			case Libgdx:
				return texture.getWidth();
			case Java2d:
				return image.getWidth();
		}
		return 0;
	}
	
	public int getHeight()
	{
		switch(backend)
		{
			case Libgdx:
				return texture.getHeight();
			case Java2d:
				return image.getHeight();
		}
		return 0;
	}
	
	public Backend getBackend()
	{
		return backend;
	}
	
	public void dispose()
	{
		switch(backend)
		{
			case Libgdx:
				texture.dispose();
				break;
			case Java2d:
				image.flush();
				break;
		}
		System.gc();
	}
}
