package com.fwumdesoft.api.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fwumdesoft.api.graphics.ContentManager;
import com.fwumdesoft.api.graphics.GdxManager;

public abstract class Screen extends Stage
{
	private ContentManager content;
	public final String ASSETS = "com/fwumdesoft/monitor/assets/";
	private boolean finished;
	
	
	public Screen()
	{
		super();
		content = new GdxManager();
		finished = false;
	}
	
	public void create()
	{
		Gdx.input.setInputProcessor(this);
	}
	
	/**
	 * Call when the game should move to the next screen
	 */
	protected void finish()
	{
		finished = true;
	}
	
	protected <T> T addContent(T obj)
	{
		return content.add(obj);
	}
	
	/**
	 * @return If the game should move on the next screen
	 */
	public boolean isFinished()
	{
		return finished;
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		content.dispose();
	}
}
