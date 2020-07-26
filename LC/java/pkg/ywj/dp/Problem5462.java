package pkg.ywj.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description link: https://leetcode-cn.com/problems/string-compression-ii/
 * Tag: str, dp
 * 2020/7/26_20:01  BranY
 *
 * 行程长度编码 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。
 * 例如，用此方法压缩字符串 "aabccc" ，将 "aa" 替换为 "a2" ，"ccc" 替换为` "c3" 。因此压缩后的字符串变为 "a2bc3" 。
 *
 * 注意，本问题中，压缩时没有在单个字符后附加计数 '1' 。
 * 给你一个字符串 s 和一个整数 k 。你需要从字符串 s 中删除最多 k 个字符，以使 s 的行程长度编码长度最小。
 *
 * 请你返回删除最多 k 个字符后，s 行程长度编码的最小长度 。
 * 输入：s = "aaabcccd", k = 2
 * 输出：4
 * 解释：在不删除任何内容的情况下，压缩后的字符串是 "a3bc3d" ，长度为 6 。最优的方案是删除 'b' 和 'd'，这样一来，压缩后的字符串为 "a3c3" ，长度是 4
 *
 */
public class Problem5462 {

    int calc(int x) {
        return (x <= 1)? x : ((x <= 9)? 2 : ((x <= 99)? 3 : 4));
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        int T = s.length() - k;
        int[][] dp = new int[s.length() + 1][T + 1];
        dp[s.length()][T] = 0;

        for (int p = s.length() - 1; p >=0; --p) {
            for(int cnt = 0; cnt <= T; ++cnt) {
                // 1. 从此开始选择连续的字符
                for(int j = p, same = 0; j < s.length(); ++j) {
                    if (s.charAt(j) == s.charAt(p))
                        same += 1;
                    if(same + cnt > T)
                        break;
                    dp[p][cnt] = Math.min(dp[p][cnt], calc(same) + dp[j+1][cnt + same]);
                }
                // 2. 跳过该字符
                dp[p][cnt] = Math.min(dp[p][cnt], dp[p+1][cnt]);
            }
        }

        return dp[0][0];
    }

    // dp[i][j]: 子串[i,n-1]最多删除j个的最短压缩长度


    public int getLengthOfOptimalCompression2(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(s, k, 0, n,  dp);
        return dp[0][k];
    }

    // 有点小bug
    private int[] dfs(String s, int k, int st, int size, int[][] dp) {
        if (st == size) return new int[] {0};

        int[] r = new int[] {dp[st][k]};
        if (r[0] != -1) return r;

        // case1: 删除当前位置字符
        if (k > 0) {
            r = dfs(s, k - 1, st + 1, size, dp);
            //dp[st][k] = r[0];
        }
        dp[st][k] = r[0];

        // case2: 保留当前字符，并枚举该字符连续的长度（所以遇到不同字符必须删除，才能维护其连续性）
        int c = 1; // 记录当前字符的连续个数
        for (int i = st + 1; i <= size; i++) {
            int[] judge = dfs(s, k, i, size, dp);
            int t = judge[0] + 1 + (c >= 100 ? 3 : c >=10 ? 2 : c > 1 ? 1: 0);

            if (r[0] == -1 || r[0] > t) {
                r[0] = t;
                dp[st][k] = t;
            }
            if (i == size) break;
            if (s.charAt(i) == s.charAt(st)) c++; // 相同, 个数加1
            else if (k-- <= 0) break; // 否则删除不同的字符
        }

        return r;
    }

    public int getLengthOfOptimalCompression3(String s, int k) {
        int n = s.length();
        // 四个维度状态分别表示：当前正在处理的下标，之前一直在处理的字符，之前一直在处理的字符的累积长度，可删除数量
        Integer[][][][] dp = new Integer[n + 1][26][n + 1][k + 1];
        // 初始状态的c选择任意即可(这里我设置为第一个下标元素)，num设置为0
        return dfs2(dp, s, k, 0, s.charAt(0), 0);

    }
    private int dfs2(Integer[][][][] dp, String s, int k, int cur, char c, int num){
        int n = s.length();
        if(cur >= n){
            return num <= 1 ? num: 1 + (num >= 10 ? 2: 1);
        }
        if(dp[cur][c - 'a'][num][k] != null){
            return dp[cur][c - 'a'][num][k];
        }
        // 处理不删的情况
        // 1. 当前处理的下标元素和之前一直在处理的字符不相等
        if(s.charAt(cur) != c) {
            // 之前一直处理的字符进行“结账” + 处理后面的字符所需要的最小长度
            dp[cur][c - 'a'][num][k] = (num <= 1 ? num : 1 + (num >= 10 ? 2: 1)) + dfs2(dp, s, k, cur + 1, s.charAt(cur), 1);
        } else {
            // 2. 当前处理的下标元素和之前一直在处理的字符相等
            dp[cur][c - 'a'][num][k] = dfs2(dp, s, k, cur + 1, c, num + 1);
        }

        // 处理删的情况
        if(k > 0){
            dp[cur][c - 'a'][num][k] = Math.min(dp[cur][c - 'a'][num][k], dfs2(dp, s, k -1, cur + 1, c, num));
        }
        return dp[cur][c - 'a'][num][k];
    }

    public static void main(String[] args) {
        Problem5462 su = new Problem5462();
        System.out.println(su.getLengthOfOptimalCompression3("aaabcccd", 2)); // 4
        System.out.println(su.getLengthOfOptimalCompression2("aaaaaaaaaaa", 0)); // 3
        System.out.println(su.getLengthOfOptimalCompression2("aabbaa", 2)); // 2
        System.out.println(su.getLengthOfOptimalCompression2("aabaa", 2)); // 2
    }
}
