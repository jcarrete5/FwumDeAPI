package com.fwumdegames.api.math;

/**
 * FwumDeGames commonly used math functions that aren't in java.Math.
 * @author Jason Carrete
 * @since Oct 18, 2014
 */
public final class FMath
{
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
}
