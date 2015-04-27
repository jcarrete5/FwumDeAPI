package com.fwumdesoft.api.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class GdxDisplay extends Display
{
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private boolean fill;
	public Vector2 window_size;
	
	private GdxDisplay(int window_width, int window_height)
	{
		window_size = new Vector2(window_width, window_height);
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
	}
	
	
	public void begin()
	{
		batch.begin();
		renderer.begin(fill ? ShapeType.Filled : ShapeType.Line);
	}
	
	public void setFill(boolean fill)
	{
		super.setFill(fill);
		renderer.set(fill ? ShapeType.Filled : ShapeType.Line);
	}
	
	public void draw(FTexture texture, float x, float y)
	{
		draw(texture, x, y, 1, 1, false, false, 0);
	}
		
	public void draw(FTexture texture, float x, float y, float scaleX, float scaleY, boolean flipX, boolean flipY, int rotation)
	{
		if(!texture.getBackend().equals(Backend.Libgdx))
			throw new IllegalArgumentException("FTexture must have the same backend as the Display that draws it.");
		batch.draw(texture.texture, x, (int)window_size.y - y - texture.getHeight(),
				0, 0, texture.getWidth(), texture.getHeight(), scaleX, scaleY, 
				rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
	}
	
	public void drawRect(float x, float y, float width, float height)
	{
		renderer.rect(x, (int)window_size.y - y - height, width, height);
	}
	
	public void drawEllipse(float x, float y, float width, float height)
	{
		renderer.ellipse(x, window_size.y - y - height, width, height);
	}
	
	public void setColor(float r, float g, float b, float a)
	{
		renderer.setColor(r, g, b, a);
	}
	
	public void setColor(java.awt.Color c)
	{
		setColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
	public void setColor(com.badlogic.gdx.graphics.Color c)
	{
		setColor(c.r, c.g, c.b, c.a);
	}
	
	public void end()
	{
		renderer.end();
		batch.end();
	}

	public void dispose()
	{
		renderer.dispose();
		batch.dispose();
	}
}