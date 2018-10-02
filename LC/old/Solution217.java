package LeetCode;

import java.util.HashSet;
import java.util.Scanner;
/*
* Program :
*          Contains Duplicate 
*          Given an array of integers, find if the array contains any duplicates.
*          Your function should return true if any value appears at least twice in the array,
*          and it should return false if every element is distinct.
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution217
{
    public boolean containsDuplicate(int[] nums) {
    	if (nums.length <= 1)
    		return false;
    	HashSet<Integer> hs = new HashSet<Integer>();
    	for (int i = 0; i < nums.length; i++)
    	{
    		if (hs.contains(nums[i]))
    			return true;
    		hs.add(nums[i]);
    	}
    	return false;
    }
    /*
     * bitmap
     */
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] str = cin.next().split(",");
    		int[] nums = new int[str.length];
    		for (int i = 0; i < str.length; i++)
    			nums[i] = Integer.parseInt(str[i]);
    		Solution217 contains = new Solution217();
    		if (contains.containsDuplicate(nums))
    			System.out.println("Yes");
    		else
    			System.out.println("No");
    	}
    	cin.close();
    }
}