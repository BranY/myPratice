#!/usr/bin/env python
# -*- coding: UTF-8 -*-
# Created by YWJ on 2018/10/2

class Solution:

    """
    dp[i][k]表示为数组A[0, i], 分割k份得到的最大平均值.

    最后一个分割点可以出现在任何部分（得出现在大于k-1个位置之后）
    所以dp[i][k]=max(dp[j][k-1]+sum(j~i)/(i-j))
    其中j在j之前的地方j<i,因此可以把这个递推式理解为重新选择最后一个分割点的最大处。
    """
    @staticmethod
    def largestSumOfAverages(A, K):
        """
        :type A: List[int]
        :type K: int
        :rtype: float
        """
        sums = [0]

        for a in A:
            # print(sums[-1])
            sums.append(sums[-1] + a)

        def gap_sum(i, j):
            return sums[j] - sums[i]

        dp = [[0 for x in range(K+1)] for y in range(len(A) + 1)]
        for i in range(1, len(A) + 1):
            dp[i][1] = gap_sum(0, i) / i

        for k in range(2, K + 1):
            for i in range(k, len(A) + 1):
                dp[i][k] = max([dp[j][k-1] + gap_sum(j,i)/(i-j) for j in range(i)])

        return dp[len(A)][K]

    @staticmethod
    def largestSumOfAverages2(A, K):

        sums = [0]
        for a in A:
            sums.append(sums[-1] + a)

        def gap_sum(i, j):
            return sums[j] - sums[i]

        # 其实每次k迭代都是只与k-1有关，所以我们只需要从后往前遍历就不会影响前面的数据
        dp = [[0] for y in range(len(A) + 1)]
        for i in range(1, len(A) + 1):
            print(i)
            dp[i][0] = gap_sum(0, i) / i

        for k in range(K - 1):
            for i in range(len(A), 0, -1):
                for j in range(i):
                    # print(j)
                    dp[i][0] = max(dp[j][0], dp[j][0] + gap_sum(j, i) / (i - j))

        return dp[-1][0]

if __name__ == '__main__':

    sums = [0]
    # print(sums[-1])
    A = [9, 1, 2, 3, 9]
    # for a in A:
    #     print("sum[-1]: %s, a: %s" % (sums[-1], a))
    #     sums.append(sums[-1] + a)
    #
    #
    # for i in range(len(A), 0, -1):
    #     print(i)
    #
    # print (sums)

    cls = Solution()

    A1 = [1, 2,3, 4]
    res = cls.largestSumOfAverages2(A, 2)

    print(res)