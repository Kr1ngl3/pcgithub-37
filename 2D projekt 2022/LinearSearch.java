/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class LinearSearch
{
	//input list and number to find
	public static int search(ArrayList<Integer> list, int n)
	{
		for (int i = 0; i < list.size(); i++)
		{
			//checks each number until correct number is found
			if (list.get(i) == n)
				return i;
		}
		return -1;
	}
}