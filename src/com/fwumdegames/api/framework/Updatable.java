package com.fwumdegames.api.framework;

/**
 * A game object that has logic actions
 * @author Ryan Goldstein
 */
public interface Updatable
{
	/**
	 * Updates the object's state
	 * @param deltaTime The nanoseconds since the last update
	 */
	public abstract void update(long deltaTime);
}
