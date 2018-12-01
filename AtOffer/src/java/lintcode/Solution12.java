package lintcode;

public class Solution12 {
    /**
     * 有一个机器人的位于一个 m × n 个网格左上角。
     *
     * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
     *
     * 问有多少条不同的路径
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        int i, j;
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                }
                else {
                    f[i][j] = f[i-1][j] + f[i][j-1];
                }
            }
        }

        return f[m-1][n-1];
    }
}
