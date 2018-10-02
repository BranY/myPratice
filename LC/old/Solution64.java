import java.util.Scanner;

/**
 * Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 * Created by wuyan on 2015/12/20.
 */
public class Solution64 {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int[] res = new int[grid[0].length];
        res[0] = grid[0][0];
        for(int i = 1; i < grid[0].length; i++)
        {
            res[i] = res[i - 1] + grid[0][i];
        }
        for(int i = 1;i < grid.length;i++)
        {
            for(int j = 0;j < grid[0].length;j++)
            {
                if(j == 0)
                    res[j] += grid[i][j];
                else
                    res[j] = Math.min(res[j - 1], res[j]) + grid[i][j];
            }
        }
        return res[grid[0].length - 1];
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int row = cin.nextInt();
            int col = cin.nextInt();
            int[][] grid = new int[row][col];
            for (int i = 0; i< row; i++) {
                String str[] = cin.nextLine().split(",");
                for (int j = 0; j < col; j++)
                    grid[i][j] = Integer.parseInt(str[j]);
            }
            Solution64 MPS = new Solution64();
            int result = MPS.minPathSum(grid);
            System.out.println(result);
        }
        cin.close();
    }
}
