package com.fwumdegames.api.util;

/**
 * Various sorting algorithms, at your finger tips.
 * @author Jason Carrete
 */
public final class Sort
{
	/**
	 * Uses the selection sort algorithm to sort an array of longs in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of integers in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of shorts in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of bytes in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of doubles in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of floats in ascending order.
	 * @param a Array to be sorted.
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
	 * Uses the selection sort algorithm to sort an array of chars in ascending order.
	 * @param a Array to be sorted.
	 */
	public static void selection(char[] a)
	{
		for(int pos = 0; pos < a.length; pos++)
		{
			int min = pos;
			for(int target = pos + 1; target < a.length; target++)
				min = a[target] < a[min] ? target : min;
			
			//swap the values
			char temp = a[pos];
			a[pos] = a[min];
			a[min] = temp;
		}
	}
	
	/**
	 * Uses the selection sort algorithm to sort an array of Comparable objects in ascending order.
	 * @param a Array to be sorted.
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
	
	/**
	 * Uses the quick sort algorithm to sort an array of longs in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
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
	
	/**
	 * Uses the quick sort algorithm to sort an array of ints in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(int[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		int pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of shorts in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(short[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		short pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				short temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of bytes in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(byte[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		byte pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				byte temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of doubles in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(double[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		double pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				double temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of floats in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(float[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		float pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				float temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of chars in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(char[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		char pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j) //swap the values
			{
				char temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		quicksort(a, left, j);
		quicksort(a, i, right);
	}
	
	/**
	 * Uses the quick sort algorithm to sort an array of Comparable Objects in ascending order.
	 * @param a Array to be sorted.
	 * @param left Leftmost index of the array.
	 * @param right Rightmost index of the array.
	 */
	public static void quicksort(Comparable<Object>[] a, int left, int right)
	{
		if(left >= right)
			return;
		
		int i = left;
		int j = right;
		Comparable<Object> pivot = a[(left + right) / 2];
		while(i < j)
		{
			while(a[i].compareTo(pivot) < 0)
				i++;
			
			while(a[j].compareTo(pivot) > 0)
				j--;
			
			if(i <= j) //swap the values
			{
				Comparable<Object> temp = a[i];
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
