package com.fwumdegames.api.util;

/**
 * Various sorting algorithms, at your finger tips.
 * @author Jason Carrete
 */
public final class Sort
{
	/**
	 * Uses the selection sort algorithm to sort an array of longs is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(long[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			long temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of integers is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(int[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			int temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of shorts is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(short[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			short temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of bytes is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(byte[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			byte temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of doubles is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(double[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			double temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of floats is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(float[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			float temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of Comparable objects is ascending order.
	 * @param a array to be sorted
	 */
	public static void selection(Comparable<Object>[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target].compareTo(a[min]) < 0 ? target : min;
			
			//swap the values
			Comparable<Object> temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	public static void quicksort(long[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		long pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				long temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
}
