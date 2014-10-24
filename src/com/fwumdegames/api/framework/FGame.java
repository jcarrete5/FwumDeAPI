package com.fwumdegames.api.framework;

import java.awt.CardLayout;
import java.util.Date;

import javax.swing.JPanel;

/**
 * Basic game framework
 * @author Ryan Goldstein
 */
public abstract class FGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String[] screenKeys;
	private int currentScreen;
	private JPanel[] screens;
	
	public FGame(JPanel[] screens)
	{
		this.setLayout(new CardLayout());
		this.screens = screens;
		for(int i = 0; i < screens.length; i++)
		{
			screenKeys[i] = Integer.toString(i);
			this.add(screens[i], screenKeys[i]);
		}
		
		new Thread(new UpdateThread()).start();
	}
	private float previousTime;
	/**
	 * Runs the game logic
	 * @param deltaTime The milliseconds since the last update
	 */
	protected void update(float deltaTime)
	{
		if(screens[currentScreen] instanceof Updatable)
		{
			((Updatable)screens[currentScreen]).update(deltaTime);
		}
	}
	
	protected void nextScreen()
	{
		currentScreen++;
		((CardLayout)this.getLayout()).show(this, screenKeys[currentScreen]); 
	}
	
	private class UpdateThread implements Runnable
	{
		@Override
		public void run()
		{
			previousTime = (new Date()).getTime();
			float delta;
			while(!Thread.interrupted())
			{
				delta = (new Date()).getTime() - previousTime;
				update(delta);
				repaint();
				
				if(delta < 1)
				{
					try
					{
						Thread.sleep(1);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
		}
	}
}
