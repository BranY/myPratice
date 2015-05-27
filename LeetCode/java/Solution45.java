package LeetCode;

import java.util.Scanner;

/*
* Program :
*          Jump Game II
*          Given an array of non-negative integers, you are initially positioned at the first index of the array.
*          Each element in the array represents your maximum jump length at that position.
*          For example:
*          Given array A = [2,3,1,1,4]
*          The minimum number of jumps to reach the last index is 2. 
*          (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution45 {
	/*
	 * 
	 */
    public int jump(int[] nums) {
    	if (nums == null || nums.length == 0)
    		return 0;
    	int ret = 0;
    	int last = 0;
    	int cur = 0;
    	for (int i = 0; i < nums.length; i++)
    	{
    		if (i > last) {
    			last = cur;
    			++ret;
    		}
    		cur = Math.max(cur, i + nums[i]);
    	}
        return ret;
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
    		Solution45 jump = new Solution45();
    		System.out.println(jump.jump(nums));
    	}
    	cin.close();
    }
}