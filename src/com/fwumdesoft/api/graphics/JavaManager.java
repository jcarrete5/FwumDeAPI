package com.fwumdesoft.api.graphics;

import com.fwumdesoft.api.io.Resource;
import com.fwumdesoft.api.io.sound.AbstractSound;
import com.fwumdesoft.api.io.sound.Sound;

public class JavaManager extends ContentManager
{
	private Class<?> relative;
	
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

}
