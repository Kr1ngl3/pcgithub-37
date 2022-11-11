/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class SelectionSort
{
	// input list
	public static ArrayList<Integer> sort(ArrayList<Integer> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			int min = i;
			for (int j = i + 1; j < list.size(); j++)
			{
				//loops through list to find smallest number
				if (list.get(j) < list.get(min))
				{
					min = j;
				}
			}
			//if a number smaller than current smallest was found switch places
			if (min != i)
			{
				int temp = list.get(i);
				list.set(i, list.get(min));
				list.set(min, temp);
			}
		}
		return list;
	}
}