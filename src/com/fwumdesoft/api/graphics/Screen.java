package com.fwumdesoft.api.graphics;

public abstract class Screen
{
	protected final ContentManager content;
	
	public Screen(ContentManager content)
	{
		this.content = content;
	}
	
	public abstract void create();
	public abstract void render(JavaDisplay display);
	public abstract void update(float milliseconds);
	public void dispose()
	{
		content.dispose();
	}
}
