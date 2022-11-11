/*
** Kenneth Johansen
** 2D Projekt sortering
*/

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class MergeSort
{
	public static ArrayList<Integer> sort(ArrayList<Integer> list)
	{
		//test if list is 1 long and therefore has been sorted
		if (list.size() <= 1)
		{
			return list;
		}
		
		//make list for right and left side
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		
		for (int i = 0; i < list.size(); i++)
		{
			//add to left
			if (i < list.size() / 2)
			{
				leftList.add(list.get(i));
			}
			//add to right
			else
			{
				rightList.add(list.get(i));
			}
			
		}
			//calls sort recursively until sorted
			leftList = sort(leftList);
			rightList = sort(rightList);
			
			return merge(leftList, rightList);
	}
	
	//merges the 2 lists and sorts them
	public static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		while (left.size() != 0 && right.size() != 0)
		{
			//determines which number is biggest
			if (left.get(0) < right.get(0))
			{
				result.add(left.get(0));
				left.remove(0);
			}
			else
			{
				result.add(right.get(0));
				right.remove(0);
			}
		}
		
		//the rest of the list gets added to the other one
		while (left.size() != 0)
		{
			result.add(left.get(0));
			left.remove(0);
		}
		
		while (right.size() != 0)
		{
			result.add(right.get(0));
			right.remove(0);
		}
		//return the sorted list
		return result;
	}
}