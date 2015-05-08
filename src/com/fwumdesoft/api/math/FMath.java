package com.fwumdesoft.api.math;

import java.util.Random;

/**
 * fwumdesoft commonly used math functions that aren't in java.Math.
 * @author Jason Carrete
 * @author Ryan Goldstein
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
	 * Computes the factorial of <tt>a</tt>.
	 * @return The number after its factorial has been computed.
	 */
	public static long fact(long a)
	{
		if(a < 0)
			throw new ArithmeticException("Cannot factorial a number less than 0");
		if(a == 0)
			return 1;
		
		long b = a;
		for(long i = a - 1; i > 1; i--)
			b *= i;
		return b;
	}
	
	/**
	 * Gets the sign (1 or -1) of an integer
	 * @param x The integer value
	 * @return 1 or -1
	 */
	public static int getSign(double x)
	{
		return (int)(Math.abs(x) / x);
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
	/**
	 * Returns a bitstring with powers of 2 descending
	 * @param number An integer to convert
	 * @return A bitstring
	 */
	public static boolean[] toBitstring(int number)
	{
		boolean[] bitstring = new boolean[(int)(Math.log10(number) / Math.log10(2))];
		for(int i = bitstring.length - 1; i >= 0; i--)
		{
			int power = (int)Math.pow(2, i);
			if(bitstring[i] = number >= power)
			{
				number -= power;
			}
		}
		return bitstring;
	}
	
	/**
	 * Creates an integer based on the bitstring <br>
	 *  <b>For example:</b> ([true, false, true] is equivalent to 0b101, 0b101 = 5
	 * @param bitstring The bitstring to convert
	 * @return An integer representation of the bitstring
	 */
	public static int toInt(boolean[] bitstring)
	{
		if(bitstring.length > 32)
			throw new IllegalArgumentException("Bitstring length must be less than 32 bits.");
		int result = 0;
		for(int i = 0; i < bitstring.length; i++)
			result += bitstring[i] ? (int)Math.pow(2, i) : 0;
		return result;
	}
	
	/**
	 * Creates an array ranging from 0 to length - 1 in a randomized order.
	 * @param length Length of the array to generate.<br>
	 * (1 larger than the highest possible number)
	 * @return A random integer array.
	 */
	public static int[] uniqueRandomIntArray(int length)
	{
		int[] array = new int[length];
		
		//fill array
		for(int i = 0; i < array.length; i++)
			array[i] = i;
		
		for(int i = array.length - 1; i >= 0; i--)
		{
			int randInt = random(i + 1);
			int tmp = array[i];
			array[i] = array[randInt];
			array[randInt] = tmp;
		}
		
		return array;
	}
}
