package pkg.ywj.linklist;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 */

public class Problem287 {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = (high + low) / 2;
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0, t = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }

        while (true) {
            slow = nums[slow];
            t = nums[t];
            if (slow == t) break;
        }
        return slow;
    }
}
