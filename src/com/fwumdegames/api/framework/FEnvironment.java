package com.fwumdegames.api.framework;

import java.util.ArrayList;

/**
 * Stores and manages a set of FEntity objects
 * @author Ryan Goldstein
 */
public class FEnvironment
{
	ArrayList<FEntity> entities;
	public void update(float deltaTime)
	{
		for(FEntity entity : entities)
			entity.update(deltaTime);
	}
}
