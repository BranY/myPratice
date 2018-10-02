package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*          N-Queens 
*   History:
*   @author wuyan  version 1
*   2015-5-25
*/
public class Solution51
{
    public List<String[]> solveNQueens(int n) {
    	List<String[]> list = new ArrayList<String[]>();
    	if (n < 1)
    		return list;
    	helper(n, 0, new int[n], list); 
        return list;
    }
    private void helper(int n, int row, int[] columnForRow, List<String[]> list)
    {
        if(row == n)
        {
            String[] item = new String[n];
            for(int i = 0;i < n;i++)
            {
                StringBuilder strRow = new StringBuilder();
                for(int j = 0;j < n;j++)
                {
                    if(columnForRow[i] == j)
                        strRow.append('Q');
                    else
                        strRow.append('.');
                }
                item[i] = strRow.toString();
            }
            list.add(item);
            return;
        }
        for(int i = 0;i < n;i++)
        {
            columnForRow[row] = i;
            if(check(row, columnForRow))
            {
                helper(n, row + 1, columnForRow, list);
            }
        }
    }
    private boolean check(int row, int[] columnForRow)
    {
        for(int i = 0;i < row;i++)
        {
            if(columnForRow[row] == columnForRow[i] || Math.abs(columnForRow[row] - columnForRow[i]) == Math.abs(row - i))
                return false;
            //Math.abs(row - i)
        }
        return true;
    }
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int n = cin.nextInt();
    		Solution51 NQueens = new Solution51();
    		List<String[]> list = NQueens.solveNQueens(n);
    		for (String[] str : list)
    		{
    			for (String s: str)
    			{
    				System.out.println(s);
    			}
    			System.out.println();
    		}
    	}
    	cin.close();
    }
}