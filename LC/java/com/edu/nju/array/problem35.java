package com.edu.nju.array;

/**
 * Description link: https://leetcode-cn.com/problems/search-insert-position/
 * Tag: Array 二分查找
 * 2020/2/4_21:57  BranY
 */
public class problem35 {
    // https://leetcode-cn.com/problems/search-insert-position/solution/hua-jie-suan-fa-35-sou-suo-cha-ru-wei-zhi-by-guanp/
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {

    }
}
