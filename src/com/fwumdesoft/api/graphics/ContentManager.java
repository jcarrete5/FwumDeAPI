package com.fwumdesoft.api.graphics;

import java.lang.reflect.Method;
import java.util.LinkedList;

import com.fwumdesoft.api.sound.AbstractSound;
/**
 * Holds a set of disposable content, allowing for one-line cleanup <br>
 * It can hold any class with a 'dispose' method <br>
 * Implementing disposable is recommended but not required for any object added
 * @author Ryan Goldstein
 */
public abstract class ContentManager implements Disposable
{
	protected LinkedList<Object> content;
	
	/**
	 * Create an empty ContentManager
	 */
	protected ContentManager()
	{
		content = new LinkedList<>();
	}
	
	/**
	 * Add a new object
	 * @param obj An object to add <br>
	 * It <b>MUST</b> have a 'dispose' method to be added
	 * @return The added object for convenience
	 */
	public <T> T add(T obj)
	{
		if(isDisposable(obj))
			content.add(obj);
		else
			throw new IllegalArgumentException("Any object passed to ContentManager.add must have a dispose method that takes no parameters.");
		return obj;
	}
	
	/**
	 * Load and add an FTexture
	 * @param path The classspath to the texture
	 * @return The loaded FTexture
	 */
	public abstract FTexture loadTexture(String path);
	
	/**
	 * Load and add an AbstractSound
	 * @param path The classpath to the sound
	 * @return The loaded Sound
	 */
	public abstract AbstractSound loadSound(String path);
	
	
	private boolean isDisposable(Object obj)
	{
		Method[] methods = obj.getClass().getMethods();
		for(Method m : methods)
			if(m.getName().equals("dispose") && m.getParameterCount() == 0)
				return true;
		return false;
	}
	
	/**
	 * Dispose the content manager <br>
	 * Calls the dispose method for all added objects
	 */
	public abstract void dispose();
}
