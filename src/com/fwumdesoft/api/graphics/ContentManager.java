package com.fwumdesoft.api.graphics;

import java.lang.reflect.Method;
import java.util.LinkedList;

import com.fwumdesoft.api.sound.AbstractSound;

public abstract class ContentManager implements Disposable
{
	private LinkedList<Object> content;
	
	protected ContentManager()
	{
		content = new LinkedList<>();
	}
	
	public <T> T add(T obj)
	{
		if(isDisposable(obj))
			content.add(obj);
		return obj;
	}
	
	public abstract FTexture loadTexture(String path);
	
	
	public abstract AbstractSound loadSound(String path);
	
	
	private boolean isDisposable(Object obj)
	{
		Method[] methods = obj.getClass().getMethods();
		for(Method m : methods)
			if(m.getName().equals("dispose") && m.getParameterCount() == 0)
				return true;
		return false;
	}
	
	public void dispose()
	{
		for(Object obj : content)
			((Disposable)obj).dispose();
	}
}
