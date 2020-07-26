package src.java.lintcode;

import java.util.Stack;

public class Solution11 {
    /**
     * 找出一个序列中乘积最大的连续子序列（至少包含一个数）
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     *
     */


    public int maxProduct(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minPre = nums[0], maxPre = nums[0];
        int max = nums[0], min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            max = Math.max(nums[i], Math.max(maxPre * nums[i], minPre * nums[i]));
            min = Math.min(nums[i], Math.min(maxPre * nums[i], minPre * nums[i]));
            res = Math.max(res, max);
            maxPre = max;
            minPre = min;
        }
        Stack<Integer> st = new Stack<>();

        return res;
    }
}
