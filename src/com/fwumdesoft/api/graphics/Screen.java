package com.fwumdesoft.api.graphics;

public abstract class Screen
{
	protected final ContentManager content;
	
	public Screen()
	{
		content = ContentManager.newLibgdxManager();
	}
	
	public Screen(Class<?> relative)
	{
		content = ContentManager.newJava2dManager(relative);
	}
	
	public abstract void create();
	public abstract void render(Display display);
	public abstract void update(float milliseconds);
	public void dispose()
	{
		content.dispose();
	}
}
