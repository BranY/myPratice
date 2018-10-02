package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
* Program :
*          Permutations II
*          Given a collection of numbers that might contain duplicates, return all possible unique permutations.
*          For example,
*          [1,1,2] have the following unique permutations:
*          [1,1,2], [1,2,1], and [2,1,1].
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution47
{
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (nums.length == 0 || nums == null)
    		return list;
    	Arrays.sort(nums);
    	helper(nums, list, new ArrayList<Integer>(), new boolean[nums.length]);
    	return list;
    }
    
	private void helper(int[] nums, List<List<Integer>> list,
			ArrayList<Integer> item, boolean[] used) {
		if (item.size() == nums.length)
		{
			list.add(new ArrayList<Integer>(item));
			return ;
		}
		for (int i = 0; i < nums.length; i++)
		{
			if (i > 0 && !used[i-1] && nums[i] == nums[i-1])
				continue;
			if (!used[i])
			{
				used[i] = true;
				item.add(nums[i]);
				helper(nums, list, item, used);
				used[i] = false;
				item.remove(item.size() - 1);
			}
		}
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
    		Solution47 perm = new Solution47();
    		List<List<Integer>> list = perm.permuteUnique(nums);
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
    	}
    	cin.close();
    }
}