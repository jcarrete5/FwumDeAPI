package com.fwumdegames.api.framework;

import java.io.Serializable;

/**
 * Represents an entity within the game
 * @author Ryan Goldstein
 */
public abstract class FEntity implements Serializable, Updatable
{
	private static final long serialVersionUID = 1L;

	public abstract void update(float deltaTime);
}
