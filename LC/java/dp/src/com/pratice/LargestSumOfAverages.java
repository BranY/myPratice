package com.pratice;

public class LargestSumOfAverages {
    /**
     * dp[i][k]表示为数组A[0, i], 分割k份得到的最大平均值.
     *  最后一个分割点可以出现在任何部分（得出现在大于k-1个位置之后）
     *  所以dp[i][k]=max(dp[j][k-1]+sum(j~i)/(i-j))
     *  其中j在j之前的地方j<i,因此可以把这个递推式理解为重新选择最后一个分割点的最大处。
     */
    public double largestSumOfAverages(int[] A, int K) {
        int len_A = A.length;
        double[] sums = new double[len_A + 1];
        sums[0] = 0.0;
        for (int i = 0; i < len_A; ++i)
            sums[i + 1] = sums[i] + A[i];

        if (K == 1)
            return sums[len_A] / len_A;
        if (K == len_A)
            return sums[len_A];

        double[][] dp = new double[len_A + 1][K + 1];
        for(int i = 1; i < len_A + 1; ++i)
            dp[i][1] = (sums[i] - sums[0]) / i;

        for (int k = 2; k < K + 1; ++k) {
            for (int i = k; i < len_A + 1; ++i) {
                double maxSum = -1;
                for (int j = 0; j < i; ++j) {
                    maxSum = Math.max(maxSum, dp[j][k-1] + (sums[i] - sums[j]) / (i - j));
                }
                dp[i][k] = maxSum;
            }
        }



        return dp[len_A][K];
    }

    public static void main(String[] args) {
        int[] A = {9, 1, 2, 3, 9};
        LargestSumOfAverages cls = new LargestSumOfAverages();
        double res = cls.largestSumOfAverages(A, 3);
        System.out.println(res);

        int cou = 21;
        String str =  Integer.toString(cou);
        for (char c :  str.toCharArray())
            System.out.println(c);
    }
}
