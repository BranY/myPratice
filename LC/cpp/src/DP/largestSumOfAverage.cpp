//
// Created by 杨文家 on 2018/10/2.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 *  dp[i][k]表示为数组A[0, i], 分割k份得到的最大平均值.
 *  最后一个分割点可以出现在任何部分（得出现在大于k-1个位置之后）
 *  所以dp[i][k]=max(dp[j][k-1]+sum(j~i)/(i-j))
 *  其中j在j之前的地方j<i,因此可以把这个递推式理解为重新选择最后一个分割点的最大处。
 * @param A array A
 * @param K k sub array
 * @return largestSumOfAverages
 */

double largestSumOfAverages(vector<int>& A, int K) {
    auto len_A = A.size();
    vector<double> sums;
    sums.push_back(0);

    for (auto val : A) {
        sums.push_back(sums.back() + val);
    }

    if (K == 1) {
        return sums.back() / len_A;
    }

    if (K == len_A) {
        return sums.back();
    }

    vector<vector<double>> dp(len_A + 1, vector<double>(K + 1, 0.0));

    for (auto i = 1; i < len_A + 1; ++i) {
        dp[i][1] = (sums[i] - sums[0]) / i;
    }

    for (auto k = 2; k < K + 1; ++k) {
        for (auto i = k; i < len_A + 1; ++i) {
            double maxSum = -1;
            for (auto j = 0; j < i; ++j) {
                maxSum = std::max(maxSum, dp[j][k-1] + (sums[i] - sums[j]) / (i -j));
            }
            dp[i][k] = maxSum;
        }
    }

    return dp[len_A][K];
}

// # 其实每次k迭代都是只与k-1有关，所以我们只需要从后往前遍历就不会影响前面的数据
double largestSumOfAverages2(vector<int>& A, int K) {
    auto len_A = A.size();
    vector<double> sums;
    sums.push_back(0.0);

    for (auto val : A) {
        sums.push_back(sums.back() + val);
    }

    if (K == 1) {
        return sums.back() / len_A;
    }

    if (K == len_A) {
        return sums.back();
    }

    vector<vector<double>> dp(len_A + 1, vector<double>(1, 0.0));

    for (int i = 1; i < len_A + 1; ++i) {
        dp[i][0] = (sums[i] - sums[0]) / i * 1.0;
    }

    for (int k = 0; k < K - 1; ++k) {
        for (auto i = len_A; i > 0; --i) {
            double maxSum = dp[i][0];
            for (auto j = 0; j < i; ++j) {
                maxSum = std::max(maxSum, dp[j][0] + (sums[i] - sums[j]) / (i -j));
            }
            dp[i][0] = maxSum;
        }
    }

    return dp[len_A][0];
}


int main(int argc, char * argv[]) {

    std::vector<int> A;
    A.push_back(9);
    A.push_back(1);
    A.push_back(2);
    A.push_back(3);
    A.push_back(9);

    std::cout <<  largestSumOfAverages(A, 1) << std::endl;
    std::cout <<  largestSumOfAverages(A, 2) << std::endl;
    std::cout <<  largestSumOfAverages(A, 4) << std::endl;

    return 0;
}
