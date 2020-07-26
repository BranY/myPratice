package pkg.ywj.array;

/**
 * Description link: https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * Tag:
 * 2020/2/4_21:56  BranY
 */
public class Problem33 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return  -1;
        int l = 0, r = nums.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;

            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid -1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return  -1;
    }
    public static void main(String[] args) {

    }
}
