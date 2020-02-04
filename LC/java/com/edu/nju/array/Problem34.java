package com.edu.nju.array;

/**
 * Description link: https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Tag:
 * 2020/2/4_21:56  BranY
 */
public class Problem34 {
    // leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/

    public int[] searchRange(int[] nums, int target) {
        int left = left_bound(nums, target);
        int right = right_bound(nums, target);
        return new int[]{left, right};
    }

    int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }

        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

    int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }

        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }

    public static void main(String[] args) {

    }
}
