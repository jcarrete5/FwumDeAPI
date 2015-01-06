package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import com.fwumdegames.api.io.Keyboard;
import com.fwumdegames.api.io.Mouse;

/**
 * Basic game framework
 * To use the FwumDeAPI framework:
 * Create a class that implements GameListener or extends GameAdapter
 * Create an FGame object
 * Add an instance of the above class to the FGame object
 * Use a JApplet or JFrame to display the FGame
 * @author Ryan Goldstein
 */
public class FGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	private List<GameListener> listeners;
	private boolean started;
 
	/**
	 * Sets up the game
	 * @param screens All of the menus and FEnvironments in the game
	 */
	public FGame()
	{
		listeners = new LinkedList<GameListener>();
		started = true;
		
		System.setProperty("sun.java2d.opengl","True");		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(Keyboard.getInstance());
		this.addMouseListener(Mouse.getInstance());
		this.addMouseMotionListener(Mouse.getInstance());
		
		new Thread(new UpdateThread(), "Update Thread").start();
	}
	
	public FGame(GameListener listener)
	{
		this();
		listeners.add(listener);
	}
	
	/**
	 * Runs the game logic
	 */
	protected void update()
	{
		for(GameListener l : listeners)
			l.update();
	}
	
	@Override
	/**
	 * Paints the game
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		for(GameListener l : listeners)
			l.draw((Graphics2D)g);
	}
	
	/**
	 * Adds a game listener to the game
	 * It will receive calls to its update and paint methods
	 * @param l
	 */
	public void addGameListener(GameListener l)
	{
		listeners.add(l);
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
			while(true)
			{
				if(started)
				{
					for(GameListener l : listeners)
						l.start();
					started = false;
				}
				update();
				repaint();
				try { Thread.sleep(1000 / 60); } catch(Exception e) { e.printStackTrace(); }
			}
		}
	}
}
