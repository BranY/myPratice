package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Program :
*          Permutations 
*          Given a collection of numbers, return all possible permutations.
*          For example,
*          [1,2,3] have the following permutations:
*          [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution46
{
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (nums.length == 0 || nums == null)
    		return list;
    	helper(nums, list, new ArrayList<Integer>(), 0);
    	return list;
    }

	private void helper(int[] nums, List<List<Integer>> list,
			ArrayList<Integer> item, int index) {
		if (index == nums.length)
		{
			list.add(new ArrayList<Integer>(item));
			return ;
		}
		for (int i = 0; i < nums.length; i++)
		{
			if(item.contains(nums[i]))
				continue;
			item.add(nums[i]);
			helper(nums, list, item, index + 1);
			item.remove(item.size() - 1);
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
    		Solution46 perm = new Solution46();
    		List<List<Integer>> list = perm.permute(nums);
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
    	}
    	cin.close();
    }
}