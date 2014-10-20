package com.fwumdegames.api.math;

/**
 * Represents a number as a fraction.
 * @author Jason Carrete
 * @since Oct 15, 2014
 */
public class Fraction extends Number implements Comparable<Fraction>
{
	private static final long serialVersionUID = 8510120980909025007L;
	
	private int numer, denom;
	
	public Fraction(int numer, int denom)
	{
		this.numer = numer;
		this.denom = denom;
		
		simplify();
	}
	
	public Fraction(double decimal)
	{
		denom = 1;
		
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
		int gcd = FMath.gcd(numer, denom);
		numer /= gcd;
		denom /= gcd;
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
	
	/**
	 * Returns a new Fraction that is the inverse of this one.
	 * @return The inverse of this fraction.
	 */
	public Fraction inverse()
	{
		return new Fraction(denom, numer);
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
		return numer / denom;
	}

	@Override
	public long longValue()
	{
		return (long)numer / denom;
	}

	@Override
	public float floatValue()
	{
		return (float)numer / denom;
	}

	@Override
	public double doubleValue()
	{
		return (double)numer / denom;
	}
}
