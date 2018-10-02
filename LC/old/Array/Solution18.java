package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
* Program :
*         4Sum
*         Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
*         Find all unique quadruplets in the array which gives the sum of target.
*         
*         Note:
*         Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
*         The solution set must not contain duplicate quadruplets.
*         
*         For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
*         A solution set is:
*         (-1,  0, 0, 1) (-2, -1, 1, 2) (-2,  0, 0, 2)
*   History:
*   @author wuyan  version 1
*   2015-4-19
*/
public class Solution18{
	 /*
	  * 403ms
	  */
	 public List<List<Integer>> fourSum(int[] num, int target) {
		 List<List<Integer>> list = new ArrayList<>();
		 if(num.length < 4 || num == null)
			 return list;
		 Arrays.sort(num);
		 for(int i=num.length - 1;i > 2;i--)
		    {
		        if(i==num.length - 1 || num[i] != num[i+1])
		        {
		            ArrayList<ArrayList<Integer>> curRes = threeSum(num,i-1,target-num[i]);
		            for(int j=0;j<curRes.size();j++)
		            {
		                curRes.get(j).add(num[i]);
		            }
		            list.addAll(curRes);
		        }
		    }
		 return list;
	 }
	 private ArrayList<ArrayList<Integer>> threeSum(int[] num, int end, int target) {

		 ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		    for(int i = end;i > 1;i--)
		    {
		        if(i == end || num[i] != num[i+1])
		        {
		            ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,target-num[i]);
		            for(int j = 0;j < curRes.size();j++)
		            {
		                curRes.get(j).add(num[i]);
		            }
		            res.addAll(curRes);
		        }
		    }
		    return res;
		}
	 
	private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target)
	{
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
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
		        while(l < r&&num[l] == num[l-1])
		        	l++;
		        while(l < r&&num[r] == num[r+1])
		        	r--;
		    }
			else if(num[l] + num[r] > target)
		    {
				r--;
		    }
			else{
				l++;
			}
		}  
		return res;
	}
		
	/*
	 * 413ms
	 */
	public List<List<Integer>> fourSum2(int[] num, int target) {
		 List<List<Integer>> list = new ArrayList<>();
		 HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
		 if(num.length < 4 || num == null)
			 return list;

		 Arrays.sort(num);
		 for (int i = 0; i < num.length; i++)
		 {
			 for (int j = i + 1; j < num.length; j++)
			 {
				 int l = j + 1;
				 int r = num.length - 1;
				 while ( l < r) {
					 int sum = num[i] + num[j] + num[l] + num[r];
					 if (sum > target)
						 r--;
					 else if(sum < target)
						 l++;
					 else if(sum == target)
					 {
						 ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[l]);
						temp.add(num[r]);
						if (!hashSet.contains(temp)) {
							hashSet.add(temp);
							list.add(temp);
						}
						l++;
						r--;
					 }
				 }
			 }
		 }
		 return list;
	 }
	
	public static void main(String[] args){
		Solution18 sum_4 = new Solution18();
		int[] num = {1,0,-1,0,-2,2};
		int target = 0;
		
		List<List<Integer>> list = sum_4.fourSum(num, target);
		for (int i = 0; i < list.size(); i++){
			for(int j = 0; j < list.get(i).size(); j++)
				System.out.print(list.get(i).get(j) + " ");
			System.out.println();
		}
		System.out.println("Done");
		int[] num1 = {-493,-470,-464,-453,-451,-446,-445,-407,-406,-393,-328,-312,-307,-303,-259,-253,-252,-243,-221,-193,-126,
				-126,-122,-117,-106,-105,-101,-71,-20,-12,3,4,20,20,54,84,98,111,148,149,152,171,175,176,211,218,227,331,352,389,
				410,420,448,485};
		int target1 = 1057;
		List<List<Integer>> list1 = sum_4.fourSum(num1, target1);
		for (int i = 0; i < list1.size(); i++){
			for(int j = 0; j < list1.get(i).size(); j++)
				System.out.print(list1.get(i).get(j) + " ");
			System.out.println();
		}
	}
}