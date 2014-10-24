package com.fwumdegames.api.framework;

/**
 * A game object that has logic actions
 * @author Ryan Goldstein
 */
public interface Updatable
{
	public abstract void update(long deltaTime);
}
