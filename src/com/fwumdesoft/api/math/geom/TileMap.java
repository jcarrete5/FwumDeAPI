package com.fwumdesoft.api.math.geom;


public class TileMap
{
	private final boolean[][] map;
	public final int TILE, WIDTH, HEIGHT;
	
	public TileMap(int tileSize, int widthInTiles, int heightInTiles)
	{
		map = new boolean[widthInTiles][heightInTiles];
		TILE = tileSize;
		WIDTH = TILE * widthInTiles;
		HEIGHT = TILE * heightInTiles;
	}
	
	public boolean validPoint(int x, int y)
	{
		return x >= 0 && y >= 0 && x / TILE < WIDTH && y / TILE < HEIGHT;
	}
	
	public boolean setValue(int x, int y, boolean newVal)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		boolean value = map[x][y];
		map[x][y] = newVal;
		return value;
	}
	
	public boolean getValue(int x, int y)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		boolean value = map[x][y];
		return value;
	}
}
