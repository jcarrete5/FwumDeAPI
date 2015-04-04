package com.fwumdesoft.api.math.geom;

@SuppressWarnings("unchecked")
public class TileMap<T>
{
	private final Object[][] map;
	public final int TILE, WIDTH, HEIGHT;
	
	public TileMap(int tileSize, int widthInTiles, int heightInTiles)
	{
		map = new Object[widthInTiles][heightInTiles];
		TILE = tileSize;
		WIDTH = TILE * widthInTiles;
		HEIGHT = TILE * heightInTiles;
	}
	
	public boolean validPoint(int x, int y)
	{
		return x >= 0 && y >= 0 && x / TILE < WIDTH && y / TILE < HEIGHT;
	}
	
	public T setValue(int x, int y, T newVal)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		T value = (T)map[x][y];
		map[x][y] = newVal;
		return value;
	}
	
	public T getValue(int x, int y)
	{
		if(!validPoint(x, y))
			throw new IllegalArgumentException("X and Y coordinates are invalid.");
		x /= TILE;
		y /= TILE;
		T value = (T)map[x][y];
		return value;
	}
}
