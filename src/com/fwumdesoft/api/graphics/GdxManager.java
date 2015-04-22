package com.fwumdesoft.api.graphics;

import com.badlogic.gdx.Gdx;
import com.fwumdesoft.api.io.sound.AbstractSound;
import com.fwumdesoft.api.io.sound.GdxSound;

public class GdxManager extends ContentManager
{
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
