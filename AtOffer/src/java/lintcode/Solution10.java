package lintcode;

public class Solution10 {
    //给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成

    /**
     * For s1 = "aabcc", s2 = "dbbca"
     *
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     * 动态规划矩阵matched[l1][l2]表示s1取l1长度（最后一个字母的pos是l1-1)，s2取l2长度(最后一个字母的pos是l2-1)，是否能匹配s3的l1+12长度。
     */


    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean [][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            if(s3.charAt(i - 1) == s1.charAt(i - 1) && dp[i - 1][0])
                dp[i][0] = true;
        }

        for (int j = 1; j <= s2.length(); j++) {
            if(s3.charAt(j - 1) == s2.charAt(j - 1) && dp[0][j - 1])
                dp[0][j] = true;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]))
                        || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && dp[i][j - 1]))
                    dp[i][j] = true;
            }
        }

        return dp[s1.length()][s2.length()];
    }

}
