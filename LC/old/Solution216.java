package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*         Combination Sum III
*         Find all possible combinations of k numbers that add up to a number n, 
*         given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
*         Ensure that numbers within the set are sorted in ascending order.
*        
*        Example 1:
*        Input: k = 3, n = 7
*        Output:  [[1,2,4]]
*        Example 2:
*        Input: k = 3, n = 9
*        Output:  [[1,2,6], [1,3,5], [2,3,4]]
*   History:
*   @author wuyan  version 1
*   2015-5-24
*/
public class Solution216
{
    public List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (k <= 0 || n < 0 )
    		return list;
    	helper(k, n, new ArrayList<Integer>(), list, 0);
        return list;
    }
    private void helper(int k, int n, ArrayList<Integer> item,
			List<List<Integer>> list,int start) {
    	for (int i = start + 1; i <= 9; i++)
    	{
    		if (k == 1)
    		{
    			if (i == n)
    			{
    				item.add(i);
    				list.add(new ArrayList<Integer>(item));
    				item.remove(item.size() - 1);
    				break;
    			}
    			else if(i > n)
    				break;
    		}
    		else
    		{
    			if (2 * i < n)
    			{
    				item.add(i);
                    helper(k - 1, n - i, item, list, i);
                    item.remove(item.size()-1);
    			}
    			else
    				break;
    		}
    	}
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int k = cin.nextInt();
    		int n = cin.nextInt();
    		Solution216 combination = new Solution216();
    		List<List<Integer>> list= combination.combinationSum3(k, n);
    		
			for (int i = 0; i < list.size(); i++){
				for(int j = 0; j < list.get(i).size(); j++)
					System.out.print(list.get(i).get(j) + " ");
				System.out.println();
			}
    	}
    	cin.close();
    }
}