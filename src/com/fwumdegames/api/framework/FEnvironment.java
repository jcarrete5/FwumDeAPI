package com.fwumdegames.api.framework;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Stores and manages a set of FEntity objects
 * @author Ryan Goldstein
 */
public class FEnvironment extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	ArrayList<FEntity> entities;
	
	public FEnvironment()
	{
		entities = new ArrayList<FEntity>();
	}
	
	public void update(float deltaTime)
	{
		for(FEntity entity : entities)
			entity.update(deltaTime);
	}
}
