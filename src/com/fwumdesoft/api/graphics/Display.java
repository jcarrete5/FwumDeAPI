package com.fwumdesoft.api.graphics;

import java.awt.Graphics2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Display implements Disposable
{
	private Backend backend;
	private Graphics2D graphics;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private boolean fill;
	private Vector2 size;
	
	private Display(Backend backend)
	{
		this.backend = backend;
		switch(backend)
		{
			case Libgdx:
				batch = new SpriteBatch();
				renderer = new ShapeRenderer();
			break;
			case Java2d:
				break;
		}
	}
	
	public static Display newLibgdxDisplay(int window_width, int window_height)
	{
		Display disp = new Display(Backend.Libgdx);
		disp.size = new Vector2(window_width, window_height);
		return disp;
	}
	
	public static Display newJava2dDisplay()
	{
		return new Display(Backend.Java2d);
	}
	
	public void begin()
	{
		if(backend.equals(Backend.Libgdx))
		{
			batch.begin();
			renderer.begin();
		}
		else
			throw new RuntimeException("begin() should only be called with the Libgdx backend.");
	}
	
	public void begin(Graphics2D graphics)
	{
		if(backend.equals(Backend.Java2d))
			this.graphics = graphics;
		else
			throw new RuntimeException("begin(Graphics2D) should only be called with the Java2d backend.");
	}
	
	public void setFill(boolean fill)
	{
		this.fill = fill;
		renderer.set(fill ? ShapeType.Filled : ShapeType.Line);
	}
	
	public boolean isFill()
	{
		return fill;
	}
	
	public void draw(FTexture texture, int x, int y)
	{
		if(!texture.getBackend().equals(backend))
			throw new IllegalArgumentException("FTexture must have the same backend as the Display that draws it.");
		if(texture.isDisposed())
			throw new IllegalArgumentException("FTexture must not be disposed at draw time.");
		switch(backend)
		{
			case Libgdx:
				batch.draw(texture.texture, x, (int)size.y - y);
				break;
			case Java2d:
				graphics.drawImage(texture.image, x, y, null);
				break;
		}
	}
	
	public void drawRect(int x, int y, int width, int height)
	{
		switch(backend)
		{
			case Libgdx:
				renderer.rect(x, (int)size.y - y, width, height);
				break;
			case Java2d:
				if(fill)
					graphics.fillRect(x, y, width, height);
				else
					graphics.drawRect(x, y, width, height);
				break;
		}
	}
	
	public void drawEllipse(int x, int y, int width, int height)
	{
		switch(backend)
		{
			case Libgdx:
				renderer.ellipse(x, (int)size.y - y, width, height);
				break;
			case Java2d:
				if(fill)
					graphics.fillOval(x, y, width, height);
				else
					graphics.drawOval(x, y, width, height);
				break;
		}
	}
	
	public void setColor(float r, float g, float b, float a)
	{
		switch(backend)
		{
			case Libgdx:
				renderer.setColor(r, g, b, a);
				break;
			case Java2d:
				graphics.setColor(new java.awt.Color(r, g, b, a));
				break;
		}
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
		switch(backend)
		{
			case Libgdx:
				renderer.end();
				batch.end();
				break;
			case Java2d:
				throw new RuntimeException("Do not call end() with Java2d backend.");
		}
	}

	@Override
	public void dispose()
	{
		switch(backend)
		{
			case Libgdx:
				renderer.dispose();
				batch.dispose();
				break;
			case Java2d:
				graphics.dispose();
				break;
		}
	}
}
