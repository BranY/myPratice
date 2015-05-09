package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Search in Rotated Sorted Array 
 *         Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *         (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *         You are given a target value to search. If found in the array return its index, otherwise return -1.
 *         You may assume no duplicate exists in the array.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-9
 */
public class Solution33
{
	public int search(int[] nums, int target) {
		if (nums.length == 0)
			return -1;
		int i = 0, j = nums.length - 1;
		while (i <= j)
		{
			int m = (i + j) / 2;
			if (nums[m] == target)
				return m;
			if (nums[m] >= nums[i])
			{
				if (nums[i] <= target && target <= nums[m])
					j = m - 1;
				else
					i = m + 1;
			}
			else
			{
				if (nums[m] >= target || target>= nums[i])
					j = m - 1;
				else
					i = m + 1;
			}
		}
		return -1;
    }
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int target = cin.nextInt();
			String[] str = cin.next().split(",");			
			int[] nums = new int[str.length];
			for (int i = 0; i < str.length; i++)
				nums[i] = Integer.parseInt(str[i]);
			Solution33 find = new Solution33();
			System.out.println(find.search(nums, target));
		}
		cin.close();
	}
}