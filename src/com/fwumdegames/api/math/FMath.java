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
	 * Gets the sign (1 or -1) of an integer
	 * @param x The integer value
	 * @return 1 or -1
	 */
	public static int getSign(int x)
	{
		return Math.abs(x) / x;
	}
	
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
	
	/**
	 * Calculates the Largest Integer Factor (lif) of the specified integer that is less than the specified integer.
	 * @param n The number that is to be processed.
	 * @return The largest Integer Factor of the specified integer.
	 */
	public static int lif(int n)
	{
		int divisor = 2;
		while(n % divisor != 0)
			divisor++;
		
		return n / divisor;
	}
	
	/**
	 * Adds up all the digits in the specified number together.
	 * @param n The number whose digits should be added together.
	 * @return The sum of all digits in the specified number.
	 */
	public static int sumDigits(int n)
	{
		int sum = 0;
		while(n != 0)
		{
			sum += n % 10;
			n /= 10;
		}
		
		return sum;
	}
}
