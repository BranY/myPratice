package LeetCode;

import java.util.Arrays;
import java.util.Scanner;
/*
* Program :
*         3Sum-Closet
*         Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
*        Return the sum of the three integers. You may assume that each input would have exactly one solution.
*        
*        For example, given array S = {-1 2 1 -4}, and target = 1.
*        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*   History:
*   @author wuyan  version 1
*   2015-4-19
*/
public class Solution16{
	
	/*
	 * 299ms
	 */
    public int threeSumClosest(int[] num, int target) {
    	if(num == null || num.length <= 2)
	        return Integer.MIN_VALUE;
    	Arrays.sort(num);
    	int closest = num[0] + num[1] + num[2] - target;  
    	for(int i = 0;i < num.length - 2;i++)
        {
            int cur = twoSum(num,target - num[i], i + 1); 
            if(Math.abs(cur) < Math.abs(closest))
                closest = cur; 
        } 
        return target + closest;
    }
    private int twoSum(int[]num, int target,int start)
    {
    	int closest = num[start] + num[start + 1] - target;
        int l = start;
        int r = num.length-1;
        while(l < r)
        {
            if(num[l] + num[r] == target)
                return 0;
            int diff = num[l] + num[r] - target;
            if(Math.abs(diff) < Math.abs(closest))
                closest = diff;    
            if(num[l] + num[r] > target)
            {
                r--;
            }
            else
            {
                l++;
            }
        }
        return closest;
    }
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int size = cin.nextInt();
			int[] num = new int[size];
			for(int i = 0; i < num.length; i++)
				num[i] = cin.nextInt();
			int target = cin.nextInt();
			Solution16 sum_3 = new Solution16();
			System.out.println(sum_3.threeSumClosest(num, target));
		}
		cin.close();
	}
}