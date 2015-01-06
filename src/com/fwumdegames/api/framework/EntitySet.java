package com.fwumdegames.api.framework;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds a set of entities
 * @author Ryan Goldstein
 */
public class EntitySet<T extends Entity> implements Iterable<T>
{
	private List<T> entities;
	
	public EntitySet()
	{
		entities = new LinkedList<T>();
	}
	
	public void add(T obj)
	{
		entities.add(obj);
	}
	
	public boolean remove(T obj)
	{
		return entities.remove(obj);
	}
	
	public boolean contains(T obj)
	{
		return entities.contains(obj);
	}
	
	public boolean free(double x, double y)
	{
		boolean free = true;
		for(T obj : entities)
			if(obj.contains(x, y))
				free = false;
		return free;
	}
	
	public boolean free(double x, double y, double width, double height)
	{
		return free(x, y) && free(x + width, y) && free(x, y + height) && free(x + width, y + height);
	}

	@Override
	public Iterator<T> iterator()
	{
		return entities.iterator();
	}
}
