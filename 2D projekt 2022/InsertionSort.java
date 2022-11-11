/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class InsertionSort
{
	//input list
	public static ArrayList<Integer> sort(ArrayList<Integer> list)
	{
		ArrayList<Integer> sortedList = new ArrayList<Integer>(); 
		for (int i = 0; i < list.size(); i++)
		{
			//add first number to the sortedlist
			if (i == 0)
			{
				sortedList.add(list.get(0));
				continue;
			}
			
			for (int j = sortedList.size() - 1; j >= 0; j--)
			{
				//loop through all numbers in sorted list and adds number to correct spot
				if (list.get(i) > sortedList.get(j))
				{
					sortedList.add(j + 1, list.get(i));
					break;
				}
				//if number was smaller than evertything else add to start
				else if (j == 0)
				{
					sortedList.add(0, list.get(i));
					break;
				}
				//continue to next index in sortedlist
				else
				{
					continue;
				}
			}
		}
		return sortedList;
	}
}