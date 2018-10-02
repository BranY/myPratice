package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Sudoku Solver 
 *         Write a program to solve a Sudoku puzzle by filling the empty cells.
 *         Empty cells are indicated by the character '.'.
 *         You may assume that there will be only one unique solution. 
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-28
 */
public class Solution37
{
	/*
	 * example: 
	 *         5 3 . . 7 . . . .
	 *         6 . . 1 9 5 . . .
	 *         . 9 8 . . . . 6 .
	 *         8 . . . 6 . . . 3
	 *         4 . . 8 . 3 . . 1
	 *         7 . . . 2 . . . 6
	 *         . 6 . . . . 2 8 .
	 *         . . . 4 1 9 . . 5
	 *         . . . . 8 . . 7 9
	 * Answer:
	 *         5 3 4 8 7 8 9 1 2
	 *         6 7 2 1 9 5 3 4 8
	 *         1 9 8 3 4 2 5 6 7
	 *         8 5 9 7 6 1 4 2 3
	 *         4 2 6 8 5 3 7 9 1
	 *         7 1 3 9 2 4 8 5 6
	 *         9 6 1 5 3 7 2 8 4
	 *         2 8 7 4 1 9 6 3 5
	 *         3 4 5 2 8 6 1 7 9    
	 */
	public void solveSudoku(char[][] board) {
	    if(board == null || board.length != 9 || board[0].length !=9)
	        return;
	    helper(board, 0, 0);
	    for (int i = 0; i < board.length; i++)
	    {
	    	for (int j = 0; j < board[i].length; j++)
	    		System.out.print(board[i][j] + " ");
	    	System.out.println();
	    }
	}
	
	private boolean helper(char[][] board, int i, int j)
	{
	    if(j >= 9)
	        return helper(board,i + 1,0);
	    if(i == 9)
	    {
	        return true;
	    }
	    if(board[i][j] == '.')
	    {
	        for(int k = 1; k <= 9; k++)
	        {
	            board[i][j] = (char)(k + '0');
	            if(isValid(board, i, j))
	            {
	                if(helper(board, i, j+1))
	                    return true;
	            }
	            board[i][j] = '.';
	        }
	    }
	    else
	    {
	        return helper(board, i, j + 1);
	    }
	    return false;
	}
	private boolean isValid(char[][] board, int i, int j)
	{
	    for(int k = 0; k < 9; k++)
	    {
	        if(k != j && board[i][k] == board[i][j])
	            return false;
	    }
	    for(int k = 0; k < 9; k++)
	    {
	        if(k != i && board[k][j] ==     board[i][j])
	            return false;
	    }        
	    for(int row = i/3*3; row < i/3*3+3; row++)
	    {
	        for(int col = j/3*3; col < j/3*3+3; col++)
	        {
	            if((row != i || col!=j) && board[row][col] == board[i][j])
	                return false;
	        }
	    }
	    return true;
	}
    
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] str = cin.next().split("#");
    		char[][] board = new char[str.length][str.length];
    		for (int i = 0 ; i < str.length; i++){
    			for (int j = 0; j < str[i].length(); j++)
    				board[i][j] = str[i].charAt(j);
    		}
    		Solution37 solve = new Solution37();
    		solve.solveSudoku(board);
    	}
    	cin.close();
    }
}