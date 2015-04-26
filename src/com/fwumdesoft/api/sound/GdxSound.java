package com.fwumdesoft.api.sound;


public class GdxSound extends AbstractSound
{
	private com.badlogic.gdx.audio.Sound sound;
	
	public GdxSound(com.badlogic.gdx.audio.Sound sound)
	{
		this.sound = sound;
	}
	
	@Override
	public void open() 
	{
		
	}

	@Override
	public void play()
	{
		sound.play();
	}

	@Override
	public void loop(int count) 
	{
		sound.loop();
	}

	@Override
	public void pause()
	{
		sound.pause();
	}

	@Override
	public void close()
	{
		sound.dispose();
	}

	@Override
	public void dispose()
	{
		close();
	}
}
