package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
* Program :
*         3Sum
*         Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
*         Find all unique triplets in the array which gives the sum of zero.
*         Note:
*             1. Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
*             2. The solution set must not contain duplicate triplets.
*          For example, given array S = {-1 0 1 2 -1 -4},
*          A solution set is: (-1, 0, 1)、(-1, -1, 2)
*   History:
*   @author wuyan  version 1
*   2015-4-19
*/

public class Solution15 {
	/*
	 * input: []
	 * output:[]
	 * 360
	 */
	public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> list = new ArrayList<>();
		if(num.length < 3 || num == null)
			return list;
		Arrays.sort(num);
		for(int i = num.length - 1;i >= 2;i--)
		{
			if(i < num.length - 1 && num[i] == num[i + 1])
	            continue;
	         List<List<Integer>> curRes = twoSum(num, i - 1, -num[i]);
	         for(int j = 0;j < curRes.size();j++)
	         {
	             curRes.get(j).add(num[i]);
	         }
	         list.addAll(curRes);
		}
		return list;
    }
	private List<List<Integer>> twoSum(int[] num, int end,int target)
	{
	    List<List<Integer>> res = new ArrayList<>();
	    if(num == null || num.length <= 1)
	        return res;
	    int l = 0;
	    int r = end;
	    while(l < r)
	    {
	        if(num[l] + num[r] == target)
	        {
	            ArrayList<Integer> item = new ArrayList<Integer>();
	            item.add(num[l]);
	            item.add(num[r]);
	            res.add(item);
	            l++;
	            r--;
	            while(l < r && num[l] == num[l - 1])
	                l++;
	            while(l < r && num[r] == num[r + 1])
	                r--;
	        }
	        else if(num[l] + num[r] > target)
	        {
	            r--;
	        }
	        else
	        {
	            l++;
	        }
	    }
	    return res;
	}
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int size = cin.nextInt();
			int[] num = new int[size];
			for(int i = 0; i < num.length; i++)
				num[i] = cin.nextInt();
			Solution15 sum_3 = new Solution15();
			List<List<Integer>> list = sum_3.threeSum(num);
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
		}
		cin.close();
	}
}