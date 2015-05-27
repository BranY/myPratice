package LeetCode;

import java.util.Scanner;

/*
* Program :
*          Trapping Rain Water 
*          See Web site
*          Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*   History:
*   @author wuyan  version 1
*   2015-5-26
*/
public class Solution42
{
    public int trap(int[] height) {
    	int second = 0;
    	int l = 0;
    	int r = height.length - 1;
    	int area = 0;
    	while (l < r)
    	{
    		if (height[l] < height[r]) {
    			second = Math.max(height[l], second);
    			area += second - height[l];
    			l++;
    		}
    		else
    		{
    			second = Math.max(height[r], second);
    			area += second - height[r];
    			r--;
    		}
    	}
        return area;
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
    		Solution42 area = new Solution42();
    		System.out.println(area.trap(nums));
    	}
    	cin.close();
    }
}