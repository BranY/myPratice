package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *         The robot can only move either down or right at any point in time.
 *          The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *          How many possible unique paths are there?
 *   History:
 *   @author wuyan
 *   2015-4-4
 */
public class Solution62{
	/*
	 * res[i][j]=res[i-1][j]+res[i][j-1]
	 * 可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息
	 * res[j] = res[j] + res[j-1]--->上一行当前列 (res[j]) + 当前行前一列 (res[j - 1])
	 * run:223
	 */
	int uniquePaths1(int m, int n) {
		if(m <= 0 || n <= 0)
			return 0;
		int[] res = new int[n];
		res[0] = 1;
		for(int i = 0;i < m;i++)
		{
			for(int j = 1;j < n;j++){
				res[j] += res[j - 1];
			}
		}
		return res[n - 1];
    }
	/*
	 * res[i][j]=res[i-1][j]+res[i][j-1]
	 * run:202
	 */
	int uniquePaths2(int m, int n) {
		if(m <= 0 || n <= 0) return 0;  
        if(m == 1 || n == 1) return 1;  
         
       int[][] dp = new int[m][n];  
         
       //只有一行时，到终点每个格子只有一种走法  
       for (int i = 0; i < n; i++)  
           dp[0][i] = 1;  
         
       // 只有一列时，到终点每个格子只有一种走法
       for (int i = 0; i < m; i++)  
           dp[i][0] = 1;  
          
       for (int i = 1; i < m; i++){  
           for (int j = 1; j < n; j++){  
               dp[i][j] = dp[i - 1][j] + dp[i][j - 1];  
           }  
       }  
       return dp[m - 1][n - 1];  
	}
    /* 1. 状态表达式:  D[i][j]: 代表从起点到这一点的所有的路径数目
     * 2. 递推公式:   D[i][j] = D[i - 1][j] + D[i][j - 1]
     * 3. 初始化:    D[0][0] = 1 原点只有一种方法到达
     * run :186
	 */
	int uniquePaths4(int m, int n) {
		 if (m == 0 || n == 0)
			 return 0;
		 int[][] D = new int[m][n];
	        
		 for (int i = 0; i < m; i++) {
			 for (int j = 0; j < n; j++) {
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
		 return D[m - 1][n - 1];
	}
	/*
	 * 排列组合
	 * 机器人总共走m+n-2步，其中m-1步往下走，n-1步往右走
	 * C<(n-1), (m + n -2)> = C<(m-1), (m + n -2)>
	 * run:208
	 */
	int uniquePaths3(int m, int n) {
		if(m <= 0 || n <= 0)
			return 0;
	    double dom = 1;
	    double dedom = 1;
	    int small = m < n? m-1:n-1;
	    int big = m < n? n-1:m-1;
	    for(int i = 1;i <= small;i++)
	    {
	        dedom *= i;
	        dom *= small + big + 1 - i;
	    }
	    return (int)(dom / dedom);
	}

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		
		while(cin.hasNext()) {
			Solution62 uniquePath = new Solution62();
			int m = cin.nextInt();
			int n = cin.nextInt();
			System.out.println(uniquePath.uniquePaths3(m, n));
		}
		cin.close();
	}
}