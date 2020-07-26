package src.java.lintcode;

/**
 * 设计一个算法，计算出n阶乘中尾部零的个数。
 */
public class Solution1 {
    public long trailingZeros(long n) {
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }
}
