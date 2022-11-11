/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import java.lang.StringBuilder;
import java.util.List;
import java.text.DecimalFormat;

class Sorting
{
	//creating static StringBuilder and iteration variable
	static StringBuilder sb;
	static int iter = 1000;
	
	
	//function that calls the sorting and times it
	public static double timer(ArrayList<Integer> list, int i)
	{
		long start, stop;
		double time = 0;
		switch (i)
		{ 
			case 0:
				sb.append("MergeSort with " + list.size() + " elements: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					MergeSort.sort(list);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
			case 1:
				sb.append("QuickSort with " + list.size() + " elements: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					QuickSort.sort(list);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
			case 2:
				sb.append("SelectionSort with " + list.size() + " elements: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					SelectionSort.sort(list);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
			case 3:
				sb.append("InsertionSort with " + list.size() + " elements: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					InsertionSort.sort(list);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
			case 4:
				sb.append("\nLinearSearch in sorted list with 2000: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					LinearSearch.search(list, 1069);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
			case 5:
				sb.append("BinarySearch in sorted list with 2000: ");
				for (int k = 0; k < iter; k++)
				{
					start = System.nanoTime();
					BinarySearch.search(list, 1069);
					stop = System.nanoTime();
					time += (double)(stop - start) / 1e+6;
				}
				break;
		}
		return time / iter;
	}
	
	public static void main(String args[])
	{
		//the list to be sorted
		ArrayList<Integer> sorted;
		
		//seed
		int seed = 7;
		
		//creating the random object
		Random gen = new Random(seed);
		
		//start of tringBuilder
		sb = new StringBuilder("Seed: " + seed + "	Iterations: " + iter + "\n\n");
		
		//array for sizes of the lists to make
		int[] listSizes = {10, 50, 200, 500, 1000, 2000};
		
		//create empty lists 
		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < listSizes.length * 3; i++)
		{
			lists.add(new ArrayList<Integer>());
		}
		
		
		//make list with random numbers
		for (int i = 0; i < listSizes.length; i++)
		{
			for (int k = 0; k < listSizes[i]; k++)
			{
				lists.get(i).add(gen.nextInt());
			}
		}
		
		
		//make already sorted from highest to lowest
		for (int i = listSizes.length; i < listSizes.length * 2; i++)
		{
			for (int k = 0; k < listSizes[i - listSizes.length]; k++)
			{
				lists.get(i).add(k);
			}
		}
		
		//make from lowest to highest
		for (int i = listSizes.length * 2; i < listSizes.length * 3; i++)
		{
			for (int k = listSizes[i - listSizes.length * 2] - 1; k >= 0; k--)
			{
				lists.get(i).add(k);
			}
		}
		
		//make decimal formatter
		DecimalFormat df = new DecimalFormat("#.######");
		//add to stringBuilder, sort and time
		for (int i = 0; i < lists.size(); i++)
		{
			if (i == 0)
			{
				sb.append("\nRandom\n");
			}
			else if (i == listSizes.length)
			{
				sb.append("\nIncreasing\n");
			}
			else if (i == listSizes.length * 2)
			{
				sb.append("\nDecreasing\n");
			}
			for (int k = 0; k < 4; k++)
			{
				sorted = (ArrayList)lists.get(i).clone();
				sb.append(df.format(timer(sorted, k)) + "\n");
			}
		}
		
		sb.append(df.format(timer(lists.get(listSizes.length * 2 - 1), 4)) + "\n");
		sb.append(df.format(timer(lists.get(listSizes.length * 2 - 1), 5)));
		
		System.out.println(sb);
		
		
		
	}
}