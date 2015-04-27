package com.fwumdesoft.api.graphics;

import com.badlogic.gdx.Gdx;
import com.fwumdesoft.api.sound.AbstractSound;
import com.fwumdesoft.api.sound.GdxSound;
/**
 * A LibGDX implementation of ContentManager
 * @author Ryan Goldstein
 */
public class GdxManager extends ContentManager
{
	/**
	 * Creates an empty LibGDX manager
	 */
	public GdxManager()
	{
		super();
	}
	
	public FTexture loadTexture(String path)
	{
		FTexture tex = new FTexture(path);
		add(tex);
		return tex;
	}
	
	public AbstractSound loadSound(String path)
	{
		AbstractSound sound = null;
		sound = new GdxSound(Gdx.audio.newSound(Gdx.files.classpath(path)));
		add(sound);
		return sound;
	}
}
