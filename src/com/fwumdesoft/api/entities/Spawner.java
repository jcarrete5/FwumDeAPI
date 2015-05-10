package com.fwumdesoft.api.entities;

/**
 * Spawn an Entity with similar properties
 * It is good form to define the update and draw functions in the spawn method, as well as the name
 * @author Ryan Goldstein
 */
@FunctionalInterface
public interface Spawner
{
	/**
	 * Create a new instance of the Entity
	 * @param x The x position to spawn at
	 * @param y The y position to spawn at
	 * @return The spawned Entity
	 */
	abstract public Entity spawn(float x, float y, float xSpeed, float ySpeed, Object userObject);
}