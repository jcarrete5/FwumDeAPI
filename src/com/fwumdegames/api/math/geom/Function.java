package com.fwumdegames.api.math.geom;

import java.awt.geom.Point2D;

/**
 * Represents a graphable function
 * @author Ryan Goldstein
 */
public interface Function
{
	/**
	 * Returns if the paramterized point is a valid pair for the function
	 * @param point The Point2D to pass
	 * @return If the point is contained
	 */
	public abstract boolean contains(Point2D.Double point);
	/**
	 * Sets if the equation is =, <, >, <=, >=
	 * Uses the enum State
	 * @param state The new state
	 */
	public abstract void setState(State state);
	
	/**
	 * Gets the sign of the equation
	 * @param state The state of the object (=, <. >, <=, >=)
	 */
	public abstract void getState(State state);
	
	public enum State
	{
		EQUALS, LESS, GREATER, EQUAL_LESS, EQUAL_GREATER;
	}
}
