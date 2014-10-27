package com.fwumdegames.api.math;

/**
 * Represents a numerical matrix.
 * @author Jason Carrete
 */
public class Matrix
{
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
	
	public int getRows()
	{
		return matrix.length;
	}
	
	public int getCols()
	{
		return matrix[0].length;
	}
	
	public Matrix inverse()
	{
		//TODO
		return this;
	}
	
	/**
	 * Multiplies this matrix by the specified scalar.<br>
	 * <b>Note:</b> This method mutates this object.
	 * @param scalar The number that multiplies each number in the matrix.
	 * @return The matrix after being multiplied by the scalar.
	 */
	public Matrix times(double scalar)
	{
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[0].length; j++)
				matrix[i][j] *= scalar;
		
		return this;
	}
	
	/**
	 * Multiplies this matrix by the specified matrix and stores the result in this matrix.
	 * @param other Matrix to be multiplied by this matrix.
	 * @return This matrix after being multiplied by the specified matrix.
	 */
	public Matrix times(Matrix other)
	{
		if(getCols() != other.getRows())
		{
			throw new IllegalMatrixDimensionException("Cannot mulitply matricies with invalid dimensions: " +
				getCols() + " ≠ " + getRows());
		}
		
		double[][] m = new double[getRows()][other.getCols()];
		
		int k = 0;
		for(int i = 0; i < getRows(); i++)
		{
			for(int j = 0; j < getCols(); j++)
				m[i][k] += this.matrix[i][j] * other.matrix[j][i];
			k++;
		}
		
		return new Matrix(m);
	}
	
	public Matrix plus(Matrix other)
	{
		
		return this;
	}
	
	public Matrix minus(Matrix other)
	{
		
		return this;
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
