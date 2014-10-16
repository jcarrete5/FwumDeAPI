package com.fwumdegames.api.math;

import java.io.Serializable;

/**
 * Represents a number as a fraction.
 * @author Jason Carrete
 * @since Oct 15, 2014
 */
public class Fraction extends Number implements Serializable, Comparable<Fraction>
{
	private static final long serialVersionUID = -8442775016067593920L;
	
	private int numer, denom;
	
	public Fraction(int numer, int denom)
	{
		if(denom == 0)
			throw new IllegalArgumentException("Denominator cannot be \"0\"");
		
		this.numer = numer;
		this.denom = denom;
		
		simplify();
	}
	
	public Fraction(double decimal)
	{
		denom = 1;
		
		//FIXME probably more efficient way of doing this
		if(decimal == 0.0)
			numer = 0;
		else
		{
			if(decimal % 10 == 0)
				numer = (int)decimal;
			else
			{
				//multiply by "10" until the decimal becomes a whole number that is evenly divisible by "10"
				while(decimal % 10 != 0)
				{
					decimal *= 10;
					denom *= 10;
				}
				
				//divide by ten to remove the identifying "0"
				numer = (int)decimal / 10;
				denom /= 10;
			
				simplify();
			}
		}
	}
	
	/**
	 * Copy constructor.
	 */
	public Fraction(Fraction f)
	{
		this(f.getNumerator(), f.getDenominator());
	}
	
	private void simplify()
	{
		int gcd = gcd(numer, denom);
		numer /= gcd;
		denom /= gcd;
	}
	
	private int gcd(int a, int b)
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
	
	public int getNumerator()
	{
		return numer;
	}
	
	public int getDenominator()
	{
		return denom;
	}
	
	public void setNumerator(int numer)
	{
		this.numer = numer;
		simplify();
	}
	
	public void setDenominator(int denom)
	{
		if(denom == 0)
			throw new IllegalArgumentException("Denominator cannot be \"0\"");
		
		this.denom = denom;
		simplify();
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(numer) + "/" + Integer.toString(denom);
	}
	
	@Override
	public int compareTo(Fraction f)
	{
		return this.intValue() - f.intValue();
	}

	@Override
	public int intValue()
	{
		return (int)(numer / denom);
	}

	@Override
	public long longValue()
	{
		return (long)(numer / denom);
	}

	@Override
	public float floatValue()
	{
		return (float)(numer / denom);
	}

	@Override
	public double doubleValue()
	{
		return (double)numer / denom;
	}
}
