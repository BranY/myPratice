package LeetCode;

import java.util.Scanner;

/*
* Program :
*          First Missing Positive  
*          Given an unsorted integer array, find the first missing positive integer.
*          For example,
*          Given [1,2,0] return 3,
*          and [3,4,-1,1] return 2.
*          
*          Your algorithm should run in O(n) time and uses constant space.
*   History:
*   @author wuyan  version 1
*   2015-5-26
*/
public class Solution41
{
    public int firstMissingPositive(int[] nums) {
        int i=0;
        for(; i<nums.length;){
            if (nums[i] <= 0 || nums[i] == i+1 || nums[i] > nums.length 
            		|| nums[i] == nums[nums[i] - 1])
            i++;
            else{
                nums[i]=(nums[i]+nums[nums[i]-1]) - (nums[nums[i]-1] = nums[i]);
            }
        }
        for(i=0;i<nums.length;i++)
        if (nums[i]!=i+1)
             break;
        return i+1;
    }
    
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] str = cin.next().split(",");
    		int[] nums = new int[str.length];
    		for (int i = 0; i < nums.length; i++)
    			nums[i] = Integer.parseInt(str[i]);
    		Solution41 missing = new Solution41();
    		System.out.println(missing.firstMissingPositive(nums));
    	}
    	cin.close();
    }
}