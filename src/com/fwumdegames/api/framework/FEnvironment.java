package com.fwumdegames.api.framework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
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
	public final int TILE_SIZE, WIDTH, HEIGHT;
	
	ArrayList<FEntity> entities;
	private FGame parent;
	private int[][] walls;
	
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
	
	/**
	 * Creates an FEnvironment
	 * @param parent The FGame it is contained in
	 * @param walls The tile values
	 * @param tileSize The pixel size of each tile
	 */
	public FEnvironment(FGame parent, int[][] walls, int tileSize)
	{
		this(walls, tileSize);
		this.setParent(parent);
	}
	
	public FEnvironment(int[][] walls, int tileSize)
	{
		entities = new ArrayList<FEntity>();
		this.TILE_SIZE = tileSize;
		this.walls = walls;
		this.WIDTH = walls.length * TILE_SIZE;
		this.HEIGHT = walls[0].length * TILE_SIZE;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	
	/**
	 * Updates all entities in the environment
	 */
	@Override
	public void update(float deltaTime)
	{
		for(FEntity entity : entities)
			entity.update(deltaTime);
	}
	
	/**
	 * Draws all entities in the environment
	 */
	@Override
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
	public int getTile(double x, double y)
	{
		x /= TILE_SIZE;
		y /= TILE_SIZE;
		return walls[(int)x][(int)y];
	}
	
	/**
	 * Sets the FGame parent
	 * @param game
	 */
	public void setParent(FGame game)
	{
		this.parent = game;
	}
	
	/**
	 * Gets if a place is free
	 * @param hitbox The area to check
	 * @return If there are no tiles there
	 */
	public boolean placeFree(Rectangle2D.Double hitbox)
	{
		return getTile(hitbox.x, hitbox.y) != 0 && getTile(hitbox.x + hitbox.width, hitbox.y) != 0 &&
				getTile(hitbox.x, hitbox.y + hitbox.width) != 0 && getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height) != 0;
	}
}
