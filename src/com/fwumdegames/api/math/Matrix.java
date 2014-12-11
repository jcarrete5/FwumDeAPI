package com.fwumdegames.api.math;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a numerical matrix.
 * @author Jason Carrete
 */
public class Matrix implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Internal 2D matrix.
	 */
	public double[][] matrix;
	
	ArrayList<Integer> augmentPoints = new ArrayList<>();
	
	/**
	 * Instantiates a new Matrix object based on the specified array.
	 * @param array Matrix values.
	 */
	public Matrix(double[][] array)
	{
		matrix = array;
		augmentPoints.add(0);
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
		
		augmentPoints.add(0);
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
	
	/**
	 * Appends (not adds) the <tt>src</tt> Matrix onto the <tt>dest</tt> Matrix.
	 * @param src The augment.
	 * @param dest The base Matrix.
	 * @return A new Matrix with the <tt>src</tt> appended onto the <tt>dest</tt>.
	 */
	public static Matrix augment(Matrix src, Matrix dest)
	{
		if(src.numRows() != dest.numRows())
			throw new IllegalMatrixDimensionException("Cannot augment matricies with differing row numbers.");
		
		Matrix m;
		double[][] d = new double[dest.numRows()][dest.numCols() + src.numCols()];
		
		//fill d[][] with dest Matrix first
		for(int i = 0; i < dest.numRows(); i++)
			for(int j = 0; j < dest.numCols(); j++)
				d[i][j] = dest.matrix[i][j];
		
		//now add the src Matrix
		for(int i = 0; i < src.numRows(); i++)
			for(int j = 0; j < src.numCols(); j++)
				d[i][dest.numCols() + j] = src.matrix[i][j];
		
		m = new Matrix(d);
		m.augmentPoints.add(dest.numCols());
		return m;
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
