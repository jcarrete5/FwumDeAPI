package com.fwumdesoft.api.graphics;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.fwumdesoft.api.io.Resource;
import com.fwumdesoft.api.io.sound.AbstractSound;
import com.fwumdesoft.api.io.sound.GdxSound;

public class ContentManager implements Disposable
{
	private LinkedList<Disposable> content;
	private Backend backend;
	private Class<?> relative;
	
	private ContentManager()
	{
		content = new LinkedList<Disposable>();
		backend = Backend.Libgdx;
	}
	
	private ContentManager(Class<?> relative)
	{
		this();
		this.relative = relative;
		backend = Backend.Java2d;
	}
	
	public static ContentManager newLibgdxManager()
	{
		return new ContentManager();
	}
	
	public static ContentManager newJava2dManager(Class<?> relative)
	{
		return new ContentManager(relative);
	}
	
	public void add(Disposable obj)
	{
		content.add(obj);
	}
	
	public FTexture loadTexture(String path)
	{
		FTexture tex = null;
		switch(backend)
		{
			case Libgdx:
				tex = new FTexture(path);
				break;
			case Java2d:
				tex = new FTexture(path, relative);
				break;
		}
		add(tex);
		return tex;
	}
	
	public AbstractSound loadSound(String path)
	{
		AbstractSound sound = null;
		switch(backend)
		{
			case Libgdx:
				sound = new GdxSound(Gdx.audio.newSound(Gdx.files.classpath(path)));
				break;
			case Java2d:
				try
				{
					sound = Resource.getSound(relative, path);
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | URISyntaxException e)
				{
					e.printStackTrace();
				}
				break;
		}
		add(sound);
		return sound;
	}
	
	public void dispose()
	{
		for(Disposable obj : content)
			obj.dispose();
	}
}
