package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*         Combination Sum
*         Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C 
*         where the candidate numbers sums to T.
*         
*         The same repeated number may be chosen from C unlimited number of times.
*         Note:
*         All numbers (including target) will be positive integers.
*         Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
*         The solution set must not contain duplicate combinations.
*         For example, given candidate set 2,3,6,7 and target 7, 
*         A solution set is: 
*         [7]-->[2, 2, 3] 
*   History:
*   @author wuyan  version 1
*   2015-5-24
*/
public class Solution39
{
	/*
	 * candidates 也有长度为1是满足的情况
	 * 具体思想是：每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题
	 */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (candidates.length < 1)
    		return list;
    	Arrays.sort(candidates);
    	helper(candidates, target, 0, new ArrayList<Integer>(), list);
        return list;
    }
    /*
     * @param candidates：候选数组
     * @param target: 目标数
     * @param item:当前的列表
     * @param list 总的列表
     */
    private void helper(int[] candidates, int target, int start,
			ArrayList<Integer> item, List<List<Integer>> list) {
		if (target < 0)
			return;
		if(target == 0)
	    {
	        list.add(new ArrayList<Integer>(item));
	        return;
	    }
	    for(int i = start;i < candidates.length; i++)
	    {
	        if(i > 0 && candidates[i] == candidates[i - 1])
	            continue;
	        item.add(candidates[i]);
	        helper(candidates, target - candidates[i], i, item, list);
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
    		Solution39 combination = new Solution39();
    		List<List<Integer>> list= combination.combinationSum(candidates, target);
    		
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
    	}
    	cin.close();
    }
}