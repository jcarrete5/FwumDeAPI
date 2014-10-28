package com.fwumdegames.api.math;

import java.io.Serializable;

/**
 * Represents a numerical matrix.
 * @author Jason Carrete
 */
public class Matrix implements Cloneable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	public double[][] matrix;
	
	public Matrix(double[][] array)
	{
		matrix = array;
	}
	
	public Matrix(int rows, int cols, double... vals)
	{
		if(vals.length != rows * cols)
			throw new IllegalMatrixDimensionException("Number of values must equal rows * cols");
		
		matrix = new double[rows][cols];
		
		int i = 0;
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++, i++)
				matrix[r][c] = vals[i];
	}
	
	/**
	 * @return The number of rows in this Matrix.
	 */
	public int numRows()
	{
		return matrix.length;
	}
	
	/**
	 * @return The number of columns in this Matrix.
	 */
	public int numCols()
	{
		return matrix[0].length;
	}
	
	/**
	 * Calculates and returns the inverse of this Matrix as this Matrix.
	 * <b>Note:</b> This method mutates this object.
	 * @return This Matrix after its inverse has been calculated.
	 */
	public Matrix inverse()
	{
		//FIXME make this method work.
		return this;
	}
	
	/**
	 * Multiplies this Matrix by the specified scalar.<br>
	 * <b>Note:</b> This method mutates this object.
	 * @param scalar The number that multiplies each number in this Matrix.
	 * @return This Matrix after being multiplied by the scalar.
	 */
	public Matrix times(double scalar)
	{
		for(int i = 0; i < numRows(); i++)
			for(int j = 0; j < numCols(); j++)
				matrix[i][j] *= scalar;
		
		return this;
	}
	
	/**
	 * Multiplies this Matrix by the specified Matrix.
	 * @param other Matrix to be multiplied by this Matrix.
	 * @return A new Matrix that is this Matrix multiplied by the specified Matrix.
	 */
	public Matrix times(Matrix other)
	{
		if(numCols() != other.numRows())
		{
			throw new IllegalMatrixDimensionException("Cannot mulitply matricies with invalid dimensions: " +
				numCols() + " != " + other.numRows());
		}
		
		double[][] m = new double[numRows()][other.numCols()];
		for(int j = 0; j < numRows(); j++)
			for(int k = 0; k < other.numCols(); k++)
				for(int l = 0; l < other.numRows(); l++)
					m[j][k] += this.matrix[j][l] * other.matrix[l][k];
		
		return new Matrix(m);
	}
	
	public Matrix plus(Matrix other)
	{
		if(numRows() != other.numRows() || numCols() != other.numCols())
			throw new IllegalMatrixDimensionException("Cannot add matricies of different dimensions.");
		
		double[][] m = new double[numRows()][numCols()];
		for(int i = 0; i < numRows(); i++)
			for(int j = 0; j < numCols(); j++)
				m[i][j] = matrix[i][j] + other.matrix[i][j];
		
		return new Matrix(m);
	}
	
	public Matrix minus(Matrix other)
	{
		if(numRows() != other.numRows() || numCols() != other.numCols())
			throw new IllegalMatrixDimensionException("Cannot add matricies of different dimensions.");
		
		double[][] m = new double[numRows()][numCols()];
		for(int i = 0; i < numRows(); i++)
			for(int j = 0; j < numCols(); j++)
				m[i][j] = matrix[i][j] - other.matrix[i][j];
		
		return new Matrix(m);
	}
	
	@Override
	public String toString()
	{
		String s = "[";
		
		for(int r = 0; r < numRows(); r++)
		{
			for(int c = 0; c < numCols(); c++)
			{
				s += Double.toString(matrix[r][c]) + "\t";
			}
			s = s.substring(0, s.length() - 1) + "]\n[";
		}
		
		return s.substring(0, s.length() - 2);
	}
	
	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			//no problem since we are Cloneable
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Exception used when a Matrix dimension is illegal.
	 * @author Jason Carrete
	 */
	public class IllegalMatrixDimensionException extends RuntimeException
	{
		private static final long serialVersionUID = 1L;
		
		public IllegalMatrixDimensionException()
		{
			super();
		}
		
		public IllegalMatrixDimensionException(String message)
		{
			super(message);
		}
		
		public IllegalMatrixDimensionException(String message, Throwable cause)
		{
	        super(message, cause);
	    }
		
		public IllegalMatrixDimensionException(Throwable cause)
		{
	        super(cause);
	    }
	}
}
