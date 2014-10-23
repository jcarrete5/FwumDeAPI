package com.fwumdegames.api.framework;

import java.util.Date;

import javax.swing.JPanel;

/**
 * Basic game framework
 * @author Ryan Goldstein
 */
public abstract class FGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public FGame()
	{
		new Thread(new UpdateThread()).start();
	}
	private float previousTime;
	public abstract void update(float deltaTime);
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
