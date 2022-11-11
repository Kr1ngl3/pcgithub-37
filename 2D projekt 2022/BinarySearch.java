/*
** Kenneth Johansen
** 2D Projekt sortering
*/


import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class BinarySearch
{
	//input a list and a number to find
	public static int search(ArrayList<Integer> list, int n)
	{
		//l for left and r for right
		int l = 0;
		int r = list.size() - 1;
		// while l is still to the left of r or while there is still more to search
		while (l <= r)
		{
			//checks middle and either goes up or down depending on if int at index m was bigger or smaller than n
			int m = (l + r) / 2;
			if (list.get(m) < n)
			{
				l = m + 1;
			}
			else if (list.get(m) > n)
			{
				r = m - 1;
			}
			else
			{
				return m;
			}
		}
		//returns -1 if the number wasn't found
		return -1;
	}
}