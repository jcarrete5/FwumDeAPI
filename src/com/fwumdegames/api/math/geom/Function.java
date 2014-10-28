package com.fwumdegames.api.math.geom;

import java.awt.geom.Point2D;

/**
 * Represents a graphable function
 * @author Ryan Goldstein
 */
public abstract class Function
{
	private State state;
	
	/**
	 * Returns if the paramterized point is a valid pair for the function
	 * @param point The Point2D to pass
	 * @return If the point is contained
	 */
	public boolean contains(Point2D.Double point)
	{
		boolean contains = false;
		if(state.value <= 2)
			contains = contains || point.y == f(point.x);
		if(state.value % 2 == 0 && state.value != 0)
			contains = contains || point.y > f(point.x);
		if(state.value % 2 == 1 && state.value != 0)
			contains = contains || point.y < f(point.x);
		return contains;
	}
	/**
	 * Sets if the equation is =, <, >, <=, >=
	 * Uses the enum State
	 * @param state The new state
	 */
	public void setState(State state)
	{
		this.state = state;
	}
	
	/**
	 * Gets the sign of the equation
	 * @param state The state of the object (=, <. >, <=, >=)
	 */
	public State getState(State state)
	{
		return state;
	}
	
	protected abstract double f(double x);
	
	
	public enum State
	{
		EQUALS(0), LESS(3), GREATER(4), EQUAL_LESS(1), EQUAL_GREATER(2);
		private int value;
		private State(int value)
		{
			this.value = value;
		}
		
		public int value()
		{
			return value;
		}
	};
}
