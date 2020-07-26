#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
//
// Created by 杨文家 on 2020/7/26.
//
class Problem5142 {
public:
    int dp[105][105];
    unsigned long n;

    int getLengthOfOptimalCompression(string s, int k) {
        n = s.size();
        memset(dp, -1, sizeof dp);
        dfs(0, k, s);
        return dp[0][k];
    }

private:
    int dfs(int st, int k, string& s) {
        if (st == n) return 0;
        int& r = dp[st][k];
//        cout << r << std::endl;
        if (r != -1) return r;
        if (k) r = dfs(st + 1, k - 1, s); // case1: 删除当前位置字符
//        cout << r << std::endl;

        // case2: 保留当前字符，并枚举该字符连续的长度（所以遇到不同字符必须删除，才能维护其连续性）
        int c = 1; // 记录当前字符的连续个数
        for (int i = st + 1; i <= n; i++) {
            int suffix = c >= 100 ? 3 : c >= 10 ? 2 : c > 1 ? 1 : 0;
            int t = dfs(i, k, s) + 1 + suffix;
            if (r == -1 || r > t) r = t;
//            cout << r << std::endl;
            if (i == n) break;
            if (s[i] == s[st]) c++; // 相同, 个数加1
            else if (k-- <= 0) break; // 否则删除不同的字符
        }
        return r;
    }
};

int main(int argc, char * argv[]) {
    Problem5142 su = Problem5142();

    cout << su.getLengthOfOptimalCompression("aaabcccd", 2) << std::endl; // 4
   // cout << su.getLengthOfOptimalCompression("aabbaa", 2) << std::endl; // 2
   // cout << su.getLengthOfOptimalCompression("aaaaaaaaaaa", 0) << std::endl; // 3
    return 0;
}