package com.fwumdesoft.api.math.geom;

/**
 * A map of tiles with boolean values <br>
 * Each tile is either free or occupied
 * @author Ryan Goldstein
 */
public class TileMap
{
	private final boolean[][] map;
	/**
	 * The size of each tile in pixels <br>
	 * (Tiles are square)
	 */
	public final int TILE;
	/**
	 * The width of the map in pixels
	 */
	public final int WIDTH;
	/**
	 * The height of the map in pixels
	 */
	public final int HEIGHT;
	
	/**
	 * Create a new TileMap
	 * @param tileSize The size of each tile in pixels
	 * @param widthInTiles The number of tiles across horizontally
	 * @param heightInTiles The number of tiles across vertically
	 */
	public TileMap(int tileSize, int widthInTiles, int heightInTiles)
	{
		map = new boolean[widthInTiles][heightInTiles];
		TILE = tileSize;
		WIDTH = TILE * widthInTiles;
		HEIGHT = TILE * heightInTiles;
	}
	
	/**
	 * Checks to see if a point falls inside the map
	 * @param x The x coordinate of the point 
	 * @param y The y coordinate of the point
	 * @return If the point falls within the point
	 */
	public boolean validPoint(float x, float y)
	{
		return x >= 0 && y >= 0 && x / TILE < map.length && y / TILE < map[(int)x / TILE].length;
	}
	
	/**
	 * Sets the value of a tile
	 * @param x The x coordinate of the point
	 * @param y The y coordinate of the point
	 * @param newVal True if there should be a tile and false if it should be free
	 * @return If the point was valid
	 */
	public boolean setValue(float x, float y, boolean newVal)
	{
		if(!validPoint(x, y))
			return false;
		x /= TILE;
		y /= TILE;
		map[(int)x][(int)y] = newVal;
		return true;
	}
	
	@Deprecated
	public boolean getValue(int x, int y)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		boolean value = map[x][y];
		return value;
	}
	
	/**
	 * Checks if a point is free
	 * @param x The x position
	 * @param y The y position
	 * @return If there is no tile at the position (x, y)
	 */
	public boolean free(float x, float y)
	{
		return validPoint(x, y) && !map[(int)x / TILE][(int)y / TILE];
	}
	
	/**
	 * Checks if an area is free
	 * @param x The x position of the region
	 * @param y The y position of the region
	 * @param width The width of the region
	 * @param height The height of the region
	 * @return If there are no tiles within the region
	 */
	public boolean free(float x, float y, float width, float height)
	{
		width -= 1;
		height -= 1;
		return free(x, y) && free(x + width, y) && free(x, y + height) && free(x + width, y + height);
	}
}
