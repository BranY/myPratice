package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*         Combination Sum2
*         Given a collection of candidate numbers (C) and a target number (T), 
*         find all unique combinations in C where the candidate numbers sums to T.
*         Each number in C may only be used once in the combination.
*         Note:
*         All numbers (including target) will be positive integers.
*         Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
*         The solution set must not contain duplicate combinations.
*         For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
*         A solution set is: 
*         [1, 7] 
*         [1, 2, 5] 
*         [2, 6] 
*         [1, 1, 6] 
*   History:
*   @author wuyan  version 1
*   2015-5-24
*/
public class Solution40
{
	/*
	 * 仿39，这里加入下标检查和去重
	 * 676ms
	 */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (candidates.length < 1)
    		return list;
    	Arrays.sort(candidates);
    	ArrayList<Integer> index = new ArrayList<>();
    	helper(candidates, target, 0, new ArrayList<Integer>(), list, index);
        return list;
    }

	private void helper(int[] candidates, int target, int start,
			ArrayList<Integer> item, List<List<Integer>> list, ArrayList<Integer> index) {
		if (target < 0)
			return;
		if (target == 0)
		{
			if (!list.contains(new ArrayList<Integer>(item)))
				list.add(new ArrayList<Integer>(item));
	        return;
		}
		for (int i = start; i < candidates.length; i++)
		{
			if (!index.contains(i))
				index.add(i);
			else
				continue;
	        item.add(candidates[i]);
	        helper(candidates, target - candidates[i], i, item, list, index);
	        index = new ArrayList<>();
	        item.remove(item.size() - 1);
		}
	}
	
	/*
	 * 332ms
	 */
	public ArrayList<ArrayList<Integer>> combinationSum2_2(int[] candidates, int target) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    if(candidates == null || candidates.length==0)
	        return res;
	    Arrays.sort(candidates);
	    helper_2(candidates, target, 0, new ArrayList<Integer>(), res);
	    return res;
	}
	private void helper_2(int[] candidates, int target, int start, ArrayList<Integer> item,
			ArrayList<ArrayList<Integer>> res)
	{
	    if(target == 0)
	    {
	        res.add(new ArrayList<Integer>(item));
	        return;
	    }
	    if(target < 0 || start >= candidates.length)
	        return;
	    for(int i = start;i < candidates.length;i++)
	    {
	        if(i > start && candidates[i] == candidates[i-1]) 
	        	continue;
	        item.add(candidates[i]);
	        helper_2(candidates,target - candidates[i], i  + 1, item, res);
	        item.remove(item.size() - 1);
	    }
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int target = cin.nextInt();
    		String[] str = cin.next().split(",");
    		int[] candidates = new int[str.length];
    		for (int i = 0; i < str.length; i++)
    			candidates[i] = Integer.parseInt(str[i]);
    		Solution40 combination = new Solution40();
    		List<List<Integer>> list= combination.combinationSum2(candidates, target);
    		
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
    	}
    	cin.close();
    }
}