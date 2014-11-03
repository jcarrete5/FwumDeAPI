package com.fwumdegames.api.math;

import java.util.Random;

/**
 * FwumDeGames commonly used math functions that aren't in java.Math.
 * @author Jason Carrete
 * @since Oct 18, 2014
 */
public final class FMath
{
	private static final Random RAND = new Random();
	
	/**
	 * Can't be instantiated.
	 */
	private FMath() {}
	
	/**
	 * A call to this method will calculate the greatest common divisor (gcd) of two numbers.
	 * @param a Integer 1.
	 * @param b Integer 2.
	 * @return A number that is the gcd of \"a\" and \"b\".
	 */
	public static int gcd(int a, int b)
	{
		//follow Euclidean algorithm
		while(b != 0)
		{
			int t = b;
			b = a % b;
			a = t;
		}
		
		return a;
	}
	
	/**
	 * Generates a random number between 0 and the offset (exclusive).
	 * @param offset One above the maximum value that can be generated.
	 * @return An integer between 0 and <tt>offset</tt> (exclusive).
	 */
	public static int random(int offset)
	{
		return RAND.nextInt(offset);
	}
}
