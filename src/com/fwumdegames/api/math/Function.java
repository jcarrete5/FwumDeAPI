package com.fwumdegames.api.math;

import java.awt.geom.Point2D;

/**
 * Represents a graphable function.
 * @author Ryan Goldstein
 */
public abstract class Function
{
	private State state;
	
	/**
	 * Returns if the parameterized point is a valid pair for the function.
	 * @param point The Point2D to pass.
	 * @return If the point is contained.
	 */
	public boolean contains(Point2D.Double point)
	{
		boolean contains = false;
		
		//solve for y once instead of calling f(x) on every line
		double y = f(point.x);
		if(state.value <= 2) //if state is EQUALS or EQUAL_GREATER or EQUAL_LESS
			contains = contains || point.y == y;
		
		if(state.value % 2 == 0 && state.value != 0) //if state is EQUAL_GREATER of GREATER
			contains = contains || point.y > y;
		
		if(state.value % 2 == 1 && state.value != 0) //if state is EQUAL_LESS or LESS
			contains = contains || point.y < y;
		
		return contains;
	}
	
	/**
	 * Sets if the equation is (=, <, >, <=, >=).<br>
	 * Uses the enum State.
	 * @param state The new state.
	 */
	public void setState(State state)
	{
		this.state = state;
	}
	
	/**
	 * Gets the sign of the equation.
	 * @returns state The state of the object (=, <, >, <=, >=).
	 */
	public State getState()
	{
		return state;
	}
	
	protected abstract double f(double x);
	
	public enum State
	{
		EQUALS(0), EQUAL_LESS(1), EQUAL_GREATER(2), LESS(3), GREATER(4);
		
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
