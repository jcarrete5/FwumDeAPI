package com.fwumdegames.api.framework;

import java.awt.Dimension;
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
	private int[][] walls;
	private final int TILE_SIZE;
	
	/**
	 * Creates an FEnvironment
	 * @param parent The FGame it is contained in
	 * @param width The width of the environment in pixels
	 * @param height The height of the environment in pixels
	 * @param tileSize The size of each game tile
	 */
	public FEnvironment(FGame parent, int width, int height, int tileSize)
	{
		this(parent, new int[width / tileSize][height / tileSize], tileSize);
	}
	
	public FEnvironment(FGame parent, int[][] walls, int tileSize)
	{
		entities = new ArrayList<FEntity>();
		this.parent = parent;
		this.TILE_SIZE = tileSize;
		this.walls = walls;
		this.setPreferredSize(new Dimension(walls.length * TILE_SIZE, walls[0].length * TILE_SIZE));
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
	
	/**
	 * Gets the value of the tile at a certain position
	 * @param x The x to check at
	 * @param y The y to check at
	 * @return The tile value
	 */
	public int getTile(int x, int y)
	{
		x /= TILE_SIZE;
		y /= TILE_SIZE;
		return walls[x][y];
	}
}
