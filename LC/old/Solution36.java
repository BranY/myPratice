package LeetCode;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Program :
 *         Valid Sudoku 
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-17
 */
public class Solution36
{
	/*
	 * every row
	 * every column
	 * every grid(3*3)
	 */
	public boolean isValidSudoku(char[][] board) {
	    if(board == null || board.length != 9 || board[0].length != 9)
	        return false;
	    for(int i = 0;i < 9;i++)
	    {
	        boolean[] map = new boolean[9];
	        for(int j = 0;j < 9;j++)
	        {
	            if(board[i][j] != '.')
	            {
	                if(map[(int)(board[i][j]-'1')])
	                {
	                    return false;
	                }
	                map[(int)(board[i][j]-'1')] = true;
	            }
	        }
	    }
	    
	    for(int j = 0;j < 9;j++)
	    {
	        boolean[] map = new boolean[9];
	        for(int i = 0;i < 9;i++)
	        {
	            if(board[i][j] != '.')
	            {
	                if(map[(int)(board[i][j]-'1')])
	                {
	                    return false;
	                }
	                map[(int)(board[i][j]-'1')] = true;
	            }
	        }
	    }        
	    for(int block = 0;block < 9;block++)
	    {
	        boolean[] map = new boolean[9];
	        for(int i = block / 3 * 3;i < block / 3 * 3 + 3;i++)
	        {
	            for(int j = block % 3 * 3;j < block % 3 * 3 + 3;j++)
	            {
	                if(board[i][j] != '.')
	                {
	                   if(map[(int)(board[i][j]-'1')])
	                   {
	                      return false;
	                   }
	                   map[(int)(board[i][j]-'1')] = true;    
	                }
	            }
	        }
	    }
	    return true;
	}
	public boolean isValidSudoku1(char[][] board) {
	    if(board == null || board.length != 9 || board[0].length != 9)
	        return false;
	    int[] rowValid = new int[10];
	    int[] columnValid = new int[10];
	    int[] gridValid = new int[10];
	    for (int i = 0; i < 9; i++)
	    {
	    	Arrays.fill(rowValid, 0);
	    	Arrays.fill(columnValid, 0);
	    	Arrays.fill(gridValid, 0);
	    	for (int j = 0; j < 9; j++)
	    	{
	    		if(!checkValid(rowValid, board[i][j] - '0') ||
	                       !checkValid(columnValid, board[j][i]-'0') ||
	                       !checkValid(gridValid, board[3*(i/3) + j/3][3*(i%3) + j%3]-'0'))
	                       return false;
	    	}
	    }
	    return true;
	}
    private boolean checkValid(int[] str, int val) {
    	if (val < 0)
    		return false;
    	if (str[val] == 1)
    		return false;
    	str[val] = 1;
		return true;
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		
    	}
    	cin.close();
    }
}