package com.fwumdegames.api.math;

/**
 * Represents a numerical matrix.
 * @author Jason Carrete
 */
public class Matrix
{
	private double[][] matrix;
	
	public Matrix(double[][] array)
	{
		matrix = array;
	}
	
	public Matrix(int rows, int cols, double... vals)
	{
		if(vals.length != rows * cols)
			throw new IllegalArgumentException("Number of values must equal rows * cols");
		
		matrix = new double[rows][cols];
		
		int i = 0;
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++, i++)
				matrix[r][c] = vals[i];
	}
	
	public void inverse()
	{
		
	}
	
	public Matrix times(Matrix other)
	{
		return null;//TODO
	}
	
	public Matrix plus(Matrix other)
	{
		return null;//TODO
	}
	
	public Matrix minus(Matrix other)
	{
		return null;//TODO
	}
	
	@Override
	public String toString()
	{
		String s = "[";
		
		for(int r = 0; r < matrix.length; r++)
		{
			for(int c = 0; c < matrix[0].length; c++)
			{
				s += Double.toString(matrix[r][c]) + " ";
			}
			s = s.substring(0, s.length() - 1) + "]\n[";
		}
		
		return s.substring(0, s.length() - 2);
	}
}
