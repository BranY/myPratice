package src.java.lintcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution6 {
    // 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率。


    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Write your code here
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair.
        List<Map.Entry<Integer, Double>> results =
                new ArrayList<>();

        double[][] f = new double[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; ++i)
            f[1][i] = 1.0 / 6;

        for (int i = 2; i <= n; ++i)
            for (int j = i; j <= 6 * n; ++j) {
                for (int k = 1; k <= 6; ++k)
                    if (j > k)
                        f[i][j] += f[i - 1][j - k];

                f[i][j] /= 6.0;
            }

        for (int i = n; i <= 6 * n; ++i)
            results.add(new AbstractMap.SimpleEntry<>(i, f[n][i]));

        return results;
    }

}
