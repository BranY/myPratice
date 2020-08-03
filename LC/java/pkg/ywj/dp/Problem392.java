package pkg.ywj.dp;

/**
 * Description link: https://leetcode-cn.com/problems/is-subsequence/solution/
 * Tag: dp
 * 2020/7/27_21:10  BranY
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 */
public class Problem392 {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        for (char ch : s.toCharArray()) {
            // 找到 t 上第一个匹配的 ch
            while (i < t.length() && t.charAt(i) != ch) i++;
            // 如果 t 穷尽，跳出，注意始终对 i 加一
            if (i++ >= t.length()) break;
        }

        return i <= t.length();
    }

    // https://leetcode-cn.com/problems/is-subsequence/solution/javati-jie-he-hou-xu-tiao-zhan-by-lil-q/
    public boolean isSubsequence2(String s, String t) {
        t = " " + t; // 开头加一个空字符作为匹配入口
        int n = t.length();
        int[][] dp = new int[n][26]; // 记录每个位置的下一个ch的位置
        for (char ch = 0; ch < 26; ch++) {
            int p = -1;
            for (int i = n - 1; i >= 0; i--) { // 从后往前记录dp
                dp[i][ch] = p;
                if (t.charAt(i) == ch + 'a') p = i;
            }
        }
        // 匹配
        int i = 0;
        for (char ch : s.toCharArray()) { // 跳跃遍历
            i = dp[i][ch - 'a'];
            if (i == -1) return false;
        }
        return true;
    }

    // 双指指针
    public boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * s.charAt(i - 1) == t.charAt(j - 1)
     *    dp[i][j] = dp[i - 1][j - 1]
     *
     * s.charAt(i - 1) != t.charAt(j - 1)
     *    dp[i][j] = dp[i][j - 1];
     */

    public boolean isSubsequenceDP(String s, String t) {
        if (s.length() == 0)
            return true;
        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
        for (int i = 0; i < t.length(); i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }


    public static void main(String[] args) {
        Problem392 su = new Problem392();
        System.out.println(su.isSubsequence("abc", "ahbgdc"));
        System.out.println(su.isSubsequence("axc", "ahbgdc"));
    }
}
