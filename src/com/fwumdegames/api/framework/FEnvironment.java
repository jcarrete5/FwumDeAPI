package com.fwumdegames.api.framework;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.fwumdegames.api.graphics.Drawable;

/**
 * Stores and manages a set of FEntity objects
 * @author Ryan Goldstein
 */
public class FEnvironment extends JPanel implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;
	ArrayList<FEntity> entities;
	
	public FEnvironment()
	{
		entities = new ArrayList<FEntity>();
	}
	
	@Override
	/**
	 * Updates all entities in the environment
	 */
	public void update(float deltaTime)
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
			if(entity instanceof Drawable)
				((Drawable) entity).draw(g);
	}
}
