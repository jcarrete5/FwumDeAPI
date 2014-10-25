package com.fwumdegames.api.framework;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.fwumdegames.api.io.Keyboard;

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
	
	protected long clockSpd = 10;
	private boolean limitFrames = true;
 
	/**
	 * Sets up the game
	 * @param screens All of the menus and FEnvironments in the game
	 */
	public FGame(JPanel[] screens)
	{
		System.setProperty("sun.java2d.opengl","True");
		
		this.setLayout(new CardLayout());
		this.screens = screens;
		screenKeys = new String[screens.length];
		for(int i = 0; i < screens.length; i++)
		{
			screenKeys[i] = Integer.toString(i);
			this.add(screens[i], screenKeys[i]);
		}
		
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		this.requestFocus();
		
		new Thread(new UpdateThread(), "Update Thread").start();
	}
	
	/**
	 * Runs the game logic
	 * @param delta The milliseconds since the last update
	 */
	protected void update(float delta)
	{
		if(screens[currentScreen] instanceof Updatable)
		{
			((Updatable)screens[currentScreen]).update(delta);
		}
	}
	
	/**
	 * Moves on to the next screen
	 */
	public void nextScreen()
	{
		((CardLayout)this.getLayout()).show(this, screenKeys[++currentScreen]); //Maybe not work
	}
	
	/**
	 * Moves back to the previous screen
	 */
	public void previousScreen()
	{
		((CardLayout)this.getLayout()).show(this, screenKeys[--currentScreen]); //maybe not work
	}
	
	/**
	 * If the game is limiting frames, then there will be guaranteed delay between each update.
	 * <br>If the game isn't limiting frames, then the framerate could be infinitely high.
	 * <br>Limiting frames reduces strain on CPU and GPU, but often doesn't let high 
	 * perfomance systems perform at max capacity.
	 * @param limit If the frames should be limited
	 */
	public void limitFrames(boolean limit)
	{
		this.limitFrames = limit;
	}
	
	/**
	 * Updates and repaints the game
	 * @author Ryan Goldsten, Jason Carrete
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
				previousTime = System.nanoTime();
				long millis = (long)(10 - delta / 1000000f);
				int nanos = (int)delta % 1000000;
				
				//Update and redraw the game
				update(delta * (float)Math.pow(10, -6));
				repaint();
				
				if(limitFrames)
					try
					{
						Thread.sleep(millis, nanos);
					}
					catch(InterruptedException e)
					{
						System.out.println("Update Thread interruped");
					}
			}
		}
	}
}
