package com.fwumdegames.api.framework;

import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * Basic game framework
 * @author Ryan Goldstein
 */
public class FGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String[] screenKeys;
	private int currentScreen;
	private JPanel[] screens;

	/**
	 * Sets up the game
	 * @param screens All of the menus and FEnvironments in the game
	 */
	public FGame(JPanel[] screens)
	{
		System.setProperty("sun.java2d.opengl","True");
		
		this.setLayout(new CardLayout());
		this.screens = screens;
		for(int i = 0; i < screens.length; i++)
		{
			screenKeys[i] = Integer.toString(i);
			this.add(screens[i], screenKeys[i]);
		}
		
		new Thread(new UpdateThread()).start();
	}
	
	/**
	 * Runs the game logic
	 * @param deltaTime The milliseconds since the last update
	 */
	protected void update(long deltaTime)
	{
		if(screens[currentScreen] instanceof Updatable)
		{
			((Updatable)screens[currentScreen]).update(deltaTime);
		}
	}
	
	/**
	 * Moves on to the next screen
	 */
	public void nextScreen()
	{
		currentScreen++;
		((CardLayout)this.getLayout()).show(this, screenKeys[currentScreen]); 
	}
	
	/**
	 * Moves back to the previous screen
	 */
	public void previousScreen()
	{
		currentScreen--;
		((CardLayout)this.getLayout()).show(this, screenKeys[currentScreen]); 
	}
	
	/**
	 * Updates and repaints the game
	 * @author Ryan Goldsten
	 */
	private class UpdateThread implements Runnable
	{
		@Override
		public void run()
		{
			long delta, previousTime = System.nanoTime();
			while(!Thread.interrupted())
			{
				//Find the delta time
				delta = (System.nanoTime() - previousTime);
				//Update and redraw the game
				update(delta);
				repaint();
				
				//Make sure there is at a minimum millisecond delay between frames
				if(delta < 5)
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
