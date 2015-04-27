package com.fwumdesoft.api.graphics;

import com.fwumdesoft.api.io.Resource;
import com.fwumdesoft.api.sound.AbstractSound;
import com.fwumdesoft.api.sound.Sound;

/**
 * A Java2d content manager
 * @author Ryan Goldstein
 */
public class JavaManager extends ContentManager
{
	private Class<?> relative;
	
	/**
	 * Creates an empty Java2d content manager
	 * @param relative The class to base all classpaths off of for loading
	 */
	protected JavaManager(Class<?> relative)
	{
		super();
		this.relative = relative;
	}
	
	@Override
	public FTexture loadTexture(String path)
	{
		FTexture tex = new FTexture(path, relative);
		add(tex);
		return tex;
	}

	@Override
	public AbstractSound loadSound(String path)
	{
		try
		{
			Sound sound = Resource.getSound(relative, path);
			add(sound);
			return sound;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public void dispose()
	{
		for(Object o : content)
			((Disposable)o).dispose();
	}
}
