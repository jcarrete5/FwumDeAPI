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
	
	public boolean validPoint(float x, float y)
	{
		return x >= 0 && y >= 0 && x / TILE < map.length && y / TILE < map[(int)x / TILE].length;
	}
	
	public boolean setValue(float x, float y, boolean newVal)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		boolean value = map[(int)x][(int)y];
		map[(int)x][(int)y] = newVal;
		return value;
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
	
	public boolean free(float x, float y)
	{
		return validPoint(x, y) && !map[(int)x / TILE][(int)y / TILE];
	}
	
	public boolean free(float x, float y, float width, float height)
	{
		width -= 1;
		height -= 1;
		return free(x, y) && free(x + width, y) && free(x, y + height) && free(x + width, y + height);
	}
}
