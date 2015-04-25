package LeetCode;
import java.util.Scanner;

/*
 * Program :
 *         Word Search 
 *         
 *         Given a 2D board and a word, find if the word exists in the grid.
 *         
 *         The word can be constructed from letters of sequentially adjacent cell, 
 *         where "adjacent" cells are those horizontally or vertically neighboring. 
 *         The same letter cell may not be used more than once.
 *         
 *         For example,
 *         Given board =
 *         [
 *           ["ABCE"],
 *           ["SFCS"],
 *           ["ADEE"]
 *          ]
 *         
 *         word = "ABCCED", -> returns true,
 *         word = "SEE", -> returns true,
 *         word = "ABCB", -> returns false.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-25
 */
public class Solution79
{
	/*
	 * 基本思路就是从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串。
	 * 这里注意每次从一个元素出发时要重置访问标记（也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。
	 * 480ms
	 */
	public boolean exist(char[][] board, String word) {
	    if(word == null || word.length() == 0)
	        return true;
	    if(board == null || board.length == 0 || board[0].length == 0)
	        return false;
	    boolean[][] used = new boolean[board.length][board[0].length];
	    for(int i = 0;i < board.length;i++)
	    {
	        for(int j = 0;j < board[0].length;j++)
	        {
	            if(search(board, word, 0, i, j, used))
	                return true;
	        }
	    }
	    return false;
	}
	private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)
	{
	    if(index == word.length())
	        return true;
	    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j] || board[i][j] != word.charAt(index))
	        return false;
	    used[i][j] = true;
	    boolean res =  search(board, word, index + 1, i - 1, j, used) 
	                || search(board, word, index + 1, i + 1, j, used)
	                || search(board, word, index + 1, i, j - 1, used) 
	                || search(board, word, index + 1, i, j + 1, used);
	    used[i][j] = false;
	    return res;
	}
	
	/*
	 * 332ms
	 */
	public boolean exist2(char[][] board, String word) {
	    if(word == null || word.length() == 0)
	        return true;
	    if(board == null || board.length == 0 || board[0].length == 0)
	        return false;
	    for(int i = 0;i < board.length;i++)
	    {
	        for(int j = 0;j < board[0].length;j++)
	        {
	        	if(board[i][j] == word.charAt(0) && dfs(i, j, word, 0, board))
	                return true;
	        }
	    }
	    return false;
	}
	
	private boolean dfs(int row, int col, String word, int index, char[][] board) {
		if(index == word.length() - 1)
			return true;  
		char ctmp = board[row][col];  
		board[row][col] = '#';
		if(row - 1 >= 0 && board[row - 1][col] == word.charAt(index + 1))
            if(dfs(row - 1, col, word, index + 1, board))
                return true;
		if(row + 1 < board.length && board[row+1][col] == word.charAt(index + 1))
            if(dfs(row + 1, col,word, index + 1, board))
                return true;
		if(col - 1 >=0 && board[row][col - 1] == word.charAt(index + 1))
            if(dfs(row, col - 1, word, index + 1, board))
                return true;
		if(col + 1 < board[0].length && board[row][col + 1] == word.charAt(index + 1))
            if(dfs(row, col + 1, word, index + 1, board))
                return true;
		board[row][col] = ctmp;
		return false;
	}

	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	char[][] board ={
    			{'A','B','C','E'},
    			{'S','F','C','S'},
    			{'A', 'D', 'E', 'E'}
    			};
    	while (cin.hasNext())
    	{
    		String query = cin.next();
    		Solution79 ws =new Solution79();
    		if (ws.exist2(board, query))
    			System.out.println("Yes");
    		else
    			System.out.println("No");
    	}
    	cin.close();
    }
}