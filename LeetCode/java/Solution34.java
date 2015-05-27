package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Search for a Range 
 *         Given a sorted array of integers, find the starting and ending position of a given target value.
 *         Your algorithm's runtime complexity must be in the order of O(log n).
 *         If the target is not found in the array, return [-1, -1].
 *         For example,
 *         Given [5, 7, 7, 8, 8, 10] and target value 8
 *         return [3, 4].
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-10
 */
public class Solution34
{
	/*
	 * not pass
	 */
    public int[] searchRange(int[] nums, int target) {
    	int[] range = {-1, -1};
    	if (nums.length == 0 || nums == null)
    		return range;
    	int l = 0, r = nums.length - 1;
    	while (l <= r)
    	{
    		int m = (l + r) / 2;
    		if (nums[m] < target) {
    			l = m + 1;
    			continue;
    		}
    		r = m;
    	}
    	int low = (nums[l] == target) ? l : -1;
    	if (low == -1)
    		return range;
    	int l1 = low, r1 = nums.length;
    	while (l1 < r1)
    	{
    		int m = (l1 + r1) / 2;
    		if (nums[m] > target)
    		{
    			r1 = m;
    			continue;
    		}
    		l1 = m + 1;
    	}
    	int high = l1 - 1;
    	range[0] = low;
    	range[1] = high;
    	return range;
    }
    
    public static int[] searchRange1(int[] nums, int target) {
        int[] ret = {-1, -1};
        
        if (nums == null || nums.length == 0) {
            return ret;
        }
        int left = 0; 
        int right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (nums[left] == target) {
            ret[0] = left;
        } else if (nums[right] == target) {
            ret[0] = right;
        } else {
            return ret;
        }
        
        left = 0; 
        right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                left = mid;
            } else if (target > nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (nums[right] == target) {
            ret[1] = right;
        } else if (nums[left] == target) {
            ret[1] = left;
        } else {
            return ret;
        }
        
        return ret;
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
			Solution34 search = new Solution34();
			int[] array = search.searchRange(nums, target);
			for (int a : array)
				System.out.print(a + ",");
			System.out.println();
		}
		cin.close();
	}
}