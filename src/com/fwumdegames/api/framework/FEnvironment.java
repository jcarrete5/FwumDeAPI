package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Stores and manages a set of FEntity objects
 * @author Ryan Goldstein
 */
public class FEnvironment extends JPanel implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	ArrayList<FEntity> entities;
	private FGame parent;
	
	/**
	 * Creates an FEnvironment
	 * @param parent The FGame it is contained in
	 */
	public FEnvironment(FGame parent)
	{
		entities = new ArrayList<FEntity>();
		this.parent = parent;
	}
	
	@Override
	/**
	 * Updates all entities in the environment
	 */
	public void update(long deltaTime)
	{
		for(FEntity entity : entities)
			entity.update(deltaTime);
	}
	
	@Override
	/**
	 * Draws all entities in the environment
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(FEntity entity : entities)
			entity.draw(g);
	}
	
	/**
	 * Moves on to the next FGame screen
	 */
	public void nextScreen()
	{
		parent.nextScreen();
	}
	
	/**
	 * Goes back to the previous FGame screen
	 */
	public void previousScreen()
	{
		parent.previousScreen();
	}
}
