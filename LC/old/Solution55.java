package LeetCode;

import java.util.Scanner;

/*
* Program :
*          Jump Game 
*          Given an array of non-negative integers, you are initially positioned at the first index of the array.
*          Each element in the array represents your maximum jump length at that position.
*          Determine if you are able to reach the last index.
*          For example:
*          A = [2,3,1,1,4], return true.
*          A = [3,2,1,0,4], return false.
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution55 {
    public boolean canJump(int[] nums) {
        
        if(nums.length==0||nums.length==1)
           return true;
        int step=nums[0];
        for(int i=1; i < nums.length; i++){
            if (step==0)  return false;
            step--;
            if (step < nums[i])
                 step = nums[i];
            if (step+i>=nums.length -1)
                return true;
        }
        return false;
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
    		Solution55 jump = new Solution55();
    		if (jump.canJump(nums))
    			System.out.println("Yes");
    		else
    			System.out.println("No");
    	}
    	cin.close();
    }
}