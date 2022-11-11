/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class QuickSort 
{
	public static ArrayList<Integer> sort(ArrayList<Integer> list)
	{
		//starts recursion with lowest index of 0 and higest of size - 1
		return sort(list, 0, list.size() - 1);
	}
	
	public static ArrayList<Integer> sort(ArrayList<Integer> list, int lo, int hi)
	{
		//while higest index is still bigger than smallest 
		if (hi >=0 && lo >= 0 && hi > lo)
		{
			int p = partition(list, lo, hi);
			sort(list, lo, p);
			sort(list, p + 1, hi);
		}
		return list;
	}
	
	public static int partition(ArrayList<Integer> list, int lo, int hi)
	{
		//set pivot to number in the middle of the array	
		int pivot = (lo + hi) / 2;
		
		//index to rigth and left
		int l = lo;
		int r = hi;
		
		while (true)
		{
			while (list.get(l) < list.get(pivot))
				l++;
			
			while (list.get(r) > list.get(pivot))
				r--;
			
			if (l >= r)
				return r;
			
			//swap int in left and right index
			Integer temp = list.get(l);
			list.set(l, list.get(r));
			list.set(r, temp);
			l++;
			r--;
		}
	}
}