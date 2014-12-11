package com.fwumdegames.api.math;

/**
 * Represents any rational number as a fraction.
 * @author Jason Carrete
 */
public class Fraction extends Number implements Comparable<Fraction>, Cloneable
{
	private static final long serialVersionUID = 8510120980909025007L;
	
	private int numer, denom;
	
	/**
	 * Instantiates a Fraction object with a numer of 0 and a denom of 1.
	 */
	public Fraction()
	{
		this(0, 1, false);
	}
	
	/**
	 * Instantiates a Fraction object.
	 * @param numer Numerator for the fraction.
	 * @param denom Denominator for the fraction.
	 * @param simplify If true, then the constructed fraction will be simplified.
	 */
	public Fraction(int numer, int denom, boolean simplify)
	{
		this.numer = numer;
		this.denom = denom;
		
		if(simplify) simplify();
	}
	
	/**
	 * Instantiates a Fraction object.
	 * @param decimal Double to be converted to a fraction.
	 * @param simplify If true, then the constructed fraction will be simplified.
	 */
	public Fraction(double decimal, boolean simplify)
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
				numer = (int)decimal;
				
				if(simplify) simplify();
			}
		}
	}
	
	public void simplify()
	{
		int gcd = FMath.gcd(numer, denom);
		numer /= gcd;
		denom /= gcd;
	}
	
	/**
	 * Creates a new Fraction that is the combined value of this Fraction and the specified Fraction.
	 * @param f The specified Fraction.
	 * @return A new Fraction object that is the combined total of this and the specified Fraction.
	 */
	public Fraction plus(Fraction f)
	{
		int newNumer = numer * f.getDenom() + denom * f.getNumer();
		int newDenom = denom * f.getDenom();
		return new Fraction(newNumer, newDenom, true);
	}
	
	public int getNumer()
	{
		return numer;
	}
	
	public int getDenom()
	{
		return denom;
	}
	
	public void setNumer(int numer)
	{
		this.numer = numer;
		simplify();
	}
	
	public void setDenom(int denom)
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
	public Fraction reciprocal()
	{
		return new Fraction(denom, numer, false);
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
	
	@Override
	public Fraction clone()
	{
		return new Fraction(numer, denom, false);
	}
}
