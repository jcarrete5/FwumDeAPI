package com.fwumdesoft.api.math;

import java.io.Serializable;

/**
 * Represents a numerical matrix.
 * @author Jason Carrete
 */
public class Matrix implements Serializable, Cloneable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Internal 2D matrix.
	 */
	private double[][] matrix;
	
	/**
	 * Instantiates a new Matrix object based on the specified array.
	 * @param array Matrix values.
	 */
	public Matrix(double[][] array)
	{
		matrix = array;
	}
	
	/**
	 * Instantiates a new Matrix and fills it with zeroes.
	 */
	public Matrix(int rows, int cols)
	{
		matrix = new double[rows][cols];
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++)
				matrix[r][c] = 0;
	}
	
	/**
	 * Instantiates a new Matrix with the specified rows, columns, and values.
	 * @param rows Number of rows.
	 * @param cols Number of columns.
	 * @param vals Values the Matrix should be made from.
	 */
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
	 * Creates an identity Matrix with the specified size.
	 * @param size The number of rows and columns of the identity Matrix.
	 * @return An identity Matrix with the specified size.
	 */
	public static Matrix getIdentity(int size)
	{
		if(size < 1)
			throw new IllegalMatrixDimensionException("Cannot instantiate a matrix with dimensions less than 1");
		
		double[][] m = new double[size][size];
		for(int i = 0; i < size; i++)
			m[i][i] = 1.0;
		
		return new Matrix(m);
	}
	
	public double get(int row, int col)
	{
		return matrix[row][col];
	}
	
	public double set(double d, int row, int col)
	{
		double oldVal = matrix[row][col];
		matrix[row][col] = d;
		return oldVal;
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
	 * DOES NOT WORK YET!
	 * Calculates and returns the inverse of this Matrix as this Matrix.
	 * <b>Note:</b> This method mutates this object.
	 * @return This Matrix after its inverse has been calculated.
	 */
	public Matrix inverse()
	{
		return this;
	}
	
	/**
	 * Inverses a 2x2 matrix only.
	 * @return This Matrix after it has been inverted.
	 */
	public Matrix inverse2()
	{
		if(numRows() != 2 || numCols() != 2)
			throw new IllegalMatrixDimensionException("numRows() and numCols() must be 2");
		//[a b]
		//[c d]
		
		// 1 / (ad - bc)
		double det = 1 / (get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0));
		set(set(get(1, 1), 0, 0), 1, 1); //swap 'a' and 'd'
		
		//negate 'b' and 'c'
		set(-get(0, 1), 0, 1);
		set(-get(1, 0), 1, 0);
		
		times(det);
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
		for(int i = 0; i < numRows(); i++)
			for(int j = 0; j < other.numCols(); j++)
				for(int k = 0; k < other.numRows(); k++)
					m[i][j] += this.matrix[i][k] * other.matrix[k][j];
		
		return new Matrix(m);
	}
	
	/**
	 * Adds this Matrix to the specified Matrix.
	 * @param other Matrix to be added to this Matrix.
	 * @return A new Matrix that is this Matrix plus the specified Matrix.
	 */
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
	
	/**
	 * Subtracts the specified Matrix from this Matrix.
	 * @param other Matrix to be subtracted from this Matrix.
	 * @return A new Matrix that is this Matrix minus the specified Matrix.
	 */
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
	public boolean equals(Object o)
	{
		if(o instanceof Matrix)
		{
			Matrix m = (Matrix)o;
			if(this.numRows() == m.numRows() && this.numCols() == m.numCols())
			{
				for(int i = 0; i < numRows(); i++)
					for(int j = 0; j < numCols(); j++)
						if(this.matrix[i][j] != m.matrix[i][j])
							return false; //return false when if any number doesn't match.
				
				//assume for loop was completed successfully
				return true;
			}
			else
				return false;
		}
		
		return false;
	}
	
	@Override
	public Matrix clone()
	{
		Matrix m = new Matrix(numRows(), numCols());
		for(int i = 0; i < numRows(); i++)
			for(int j = 0; j < numCols(); j++)
				m.set(get(i, j), i, j);
		return m;
	}
	
	/**
	 * Exception used when a Matrix dimension is illegal.
	 * @author Jason Carrete
	 */
	static class IllegalMatrixDimensionException extends IllegalArgumentException
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
