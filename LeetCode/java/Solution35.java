package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Search Insert Position 
 *         Given a sorted array and a target value, return the index if the target is found.
 *         If not, return the index where it would be if it were inserted in order.
 *         You may assume no duplicates in the array.
 *         Here are few examples.
 *         [1,3,5,6], 5 → 2
 *         [1,3,5,6], 2 → 1
 *         [1,3,5,6], 7 → 4
 *         [1,3,5,6], 0 → 0
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-11
 */
public class Solution35
{
    public int searchInsert(int[] nums, int target) {
    	
    	int l = 0, r = nums.length - 1;
    	while (l <= r)
    	{
    		int m = (l + r) / 2;
    		if (nums[m] == target)
    			return m;

    		if (nums[m] < target)
    			l = m + 1;
    		else
    			r = m - 1;
    	}
        return l;
    }
    
    public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int target = cin.nextInt();
			String[] str = cin.next().split(",");
			int[] nums = new int[str.length];
			for (int i = 0; i < nums.length; i++)
				nums[i] = Integer.parseInt(str[i]);
			Solution35 search = new Solution35();
			System.out.println(search.searchInsert(nums, target));
		}
		cin.close();
	}
}