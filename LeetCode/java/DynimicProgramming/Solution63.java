package LeetCode;

import java.util.Scanner;

/*
 * Program :
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 *   History:
 *   @author wuyan
 *   2015-4-4
 */
public class Solution63{
	
	 /* 1. 状态表达式:  D[i][j]: 代表从起点到这一点的所有的路径数目
     *  2. 递推公式:   D[i][j] = D[i - 1][j] + D[i][j - 1] | if(obstacleGrid[i][j] ==  1) D[i][j] = 0
     *  3. 初始化:    D[0][0] = 1 原点只有一种方法到达
     * run : 270
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 
		          || obstacleGrid[0].length == 0) {
			return 0;      
		}
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		        
		int[][] D = new int[rows][cols];    
		        
		for (int i = 0; i < rows; i++) {        
			for (int j = 0; j < cols; j++) {
				if (obstacleGrid[i][j] == 1) {
					D[i][j] = 0;
					continue;
				}
				if (i == 0 && j == 0) {
					D[i][j] = 1;    
				} else if (i == 0) {     
					D[i][j] = D[i][j - 1];  
				} else if (j == 0) {        
					D[i][j] = D[i - 1][j];       
				} else {    
					D[i][j] = D[i - 1][j] + D[i][j - 1];     
				}    
			}        
		}
		return D[rows - 1][cols - 1];
    }
	
	/*
	 *res[i][j]=res[i-1][j]+res[i][j-1]
	 * 可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息
	 * res[j] = res[j] + res[j-1]--->上一行当前列 (res[j]) + 当前行前一列 (res[j - 1])
	 * run:257
	 */
	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 
		          || obstacleGrid[0].length == 0) {
			return 0;      
		}
		int[] res = new int[obstacleGrid[0].length];
		res[0] = 1;
		for(int i = 0;i < obstacleGrid.length;i++)
		{
			for(int j = 0;j < obstacleGrid[0].length;j++){
				if(obstacleGrid[i][j]==1)
					res[j]=0;
				else{
					if(j > 0)
						res[j] += res[j-1];
				}    
			} 
		}
		return res[obstacleGrid[0].length-1];
	}
	
	/*
	 * run:195
	 */
	public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 
		          || obstacleGrid[0].length == 0) {
			return 0;      
		}
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
	    int[][] path = new int[m][n];
	    for(int i = 0; i < n; i++){
	    	if(obstacleGrid[0][i] == 0){
	    		path[0][i] = 1;
	    	}else{
	    		path[0][i] = 0;
	    		break;
	    	}
	    }
	    for(int i = 0; i < m; i++){
	    	if(obstacleGrid[i][0] == 0){
	    		path[i][0] = 1;
	    	}else{
	    		path[i][0] = 0;
	    		break;
	    	}
	    }
	    for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if(obstacleGrid[i][j] == 0){
					path[i][j] = path[i-1][j] + path[i][j-1];
				}
			}
		}
		return path[m-1][n-1];
	}
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		while (cin.hasNext())
		{
			int row = cin.nextInt();
			int column = cin.nextInt();
			int[][] obstacleGrid = new int[row][column];
			for(int i = 0; i < row; i++)
				for(int j = 0; j < column; j++)
					obstacleGrid[i][j] = cin.nextInt();
			Solution63 uniquePath2 = new Solution63();
			System.out.println(uniquePath2.uniquePathsWithObstacles(obstacleGrid));
		}
		cin.close();
	}
}