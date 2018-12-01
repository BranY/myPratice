package lintcode;

public class Solution9 {


    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int i, j;
        int m = s1.length;
        int n = s2.length;

        int[][] f = new int[m + 1][n + 1];

        // commented part is for outputting solution
        // 'I', 'D', 'R', 'S'
        //char[][] pi = new char[m + 1][n + 1];
        for (i = 0; i <= m; ++i) {
            for (j = 0; j <= n; ++j) {
                if (i == 0) {
                    f[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    f[i][j] = i;
                    continue;
                }

                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }

        return f[m][n];
    }
}
