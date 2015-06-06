package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Maximum Subarray 
 *         Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 *         For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 *         the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *   History:
 *   @author wuyan  version 1 
 *   2015-6-2
 */
public class Solution53
{
    public int maxSubArray(int[] nums) {
        if (nums.length ==1)
           return nums[0];
        int sum=nums[0], max = nums[0];
        int i =1;
        while (i < nums.length){
            sum = sum < 0 ? nums[i] : nums[i]+ sum;
            if (max < sum)
                max= sum;
            i++;
        }
        return max;
    }
    
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] str = cin.next().split(",");
    		int[] nums = new int[str.length];
    		for (int i = 0; i < str.length; i++)
    			nums[i] = Integer.parseInt(str[i]);
    		Solution53 maxSub = new Solution53();
    		System.out.println(maxSub.maxSubArray(nums));
    	}
    	cin.close();
    }
}