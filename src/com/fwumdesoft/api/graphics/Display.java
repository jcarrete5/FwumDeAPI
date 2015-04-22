package com.fwumdesoft.api.graphics;

public abstract class Display implements Disposable
{
	private boolean fill = false;
	
	public void setFill(boolean fill)
	{
		this.fill = fill;
	}
	
	public boolean isFill()
	{
		return fill;
	}
	
	public void draw(FTexture texture, float x, float y)
	{
		draw(texture, x, y, 1, 1, false, false, 0);
	}
	
	public abstract void draw(FTexture texture, float x, float y, float scaleX, float scaleY, 
			boolean flipX, boolean flipY, int rotation);
	
	public abstract void drawRect(float x, float y, float width, float height);
	
	public abstract void drawEllipse(float x, float y, float width, float height);
	
	public abstract void setColor(float r, float b, float g, float a);
	
	public abstract void setColor(java.awt.Color c);
	
	public abstract void setColor(com.badlogic.gdx.graphics.Color c);
	
}
