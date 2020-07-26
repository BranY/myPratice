package pkg.ywj.array;

/**
 * Description link: https://leetcode-cn.com/problems/next-permutation/
 * Tag: https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * 2020/2/4_21:55  BranY
 */
public class Problem31 {
    // leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode/
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void nextPermutation(int[] nums) {
        int size = nums.length;
        if (size <= 1) return;
        int i = size  - 2, j = size - 1, k = size - 1;

        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        if (i >= 0) {
            while (nums[i] >= nums[k]) k--;
            swap(nums, i, k);
        }
        reverse(nums, j);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

    }
}
