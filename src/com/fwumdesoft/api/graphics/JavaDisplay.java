package com.fwumdesoft.api.graphics;

import java.awt.Graphics2D;

public class JavaDisplay extends Display
{
	private Graphics2D graphics;
	private boolean fill;
	
	private JavaDisplay()
	{
		fill = false;
	}
	
	public void begin(Graphics2D graphics)
	{
		this.graphics = graphics;
	}
		
	//TODO: Tranformations in JavaDisplay
	public void draw(FTexture texture, float x, float y, float scaleX, float scaleY, boolean flipX, boolean flipY, int rotation)
	{
		if(!texture.getBackend().equals(Backend.Java2d))
			throw new IllegalArgumentException("JavaDisplay may only draw Java2d FTextures");
		graphics.drawImage(texture.image, (int)x, (int)y, null);
	}
	
	public void drawRect(float x, float y, float width, float height)
	{
		if(fill)
			graphics.fillRect((int)x, (int)y, (int)width, (int)height);
		else
			graphics.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	
	public void drawEllipse(float x, float y, float width, float height)
	{
		if(fill)
			graphics.fillOval((int)x, (int)y, (int)width, (int)height);
		else
			graphics.drawOval((int)x, (int)y, (int)width, (int)height);
	}
	
	@Override
	public void setColor(float r, float g, float b, float a)
	{
		graphics.setColor(new java.awt.Color(r, g, b, a));
	}
	
	@Override
	public void setColor(java.awt.Color c)
	{
		setColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
	@Override
	public void setColor(com.badlogic.gdx.graphics.Color c)
	{
		setColor(c.r, c.g, c.b, c.a);
	}

	public void dispose()
	{
		graphics.dispose();
	}
}
