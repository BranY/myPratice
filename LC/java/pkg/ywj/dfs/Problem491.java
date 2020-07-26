package pkg.ywj.dfs;

import java.util.*;

/**
 * Description link: https://leetcode-cn.com/problems/increasing-subsequences/
 * Tag: dfs
 * 2020/7/26_17:40  BranY
 *  给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *  输入: [4, 6, 7, 7] （可能是重复的）
 *  输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 */
public class Problem491 {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        Problem491 su = new Problem491();
        List<List<Integer>> res = su.findSubsequences(nums);
        for (List<Integer> list : res) {
            list.forEach(x -> System.out.print(x + ","));
            System.out.println();
        }
    }

    /**
     * 1. 当前元素进入的条件：当前元素大于等于 list 中的最后一个元素
     * 2. 当前元素无法进入的条件：
     *    （1）如果 list 不为空，且当前元素和 list 中的最后一个元素相等，我们不考虑不将当前元素加入 list 中这一分支。
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0 || nums == null) return res;
//        dfs(nums, new ArrayList<>(), 0, res);
        dfs2(nums, new ArrayList<>(), 0, Integer.MIN_VALUE, res);
        return res;
    }

    private void dfs(int[] nums, List<Integer> list, int index, List<List<Integer>> res) {
        if (index >= nums.length) {
            if (list.size() >= 2) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        if (list.isEmpty() || nums[index] >= list.get(list.size() - 1 )) {
            list.add(nums[index]);
            dfs(nums, list, index + 1, res);
            list.remove(list.size() - 1);
        }
        if (index > 0 && !list.isEmpty() && nums[index] == list.get(list.size() - 1 )) {
            return;
        }
        // 不把第 index 个元素加进 list 中
        dfs(nums, list, index + 1, res);
    }

    private void dfs2(int[] nums, List<Integer> list, int index, int pre, List<List<Integer>> res) {
        if (list.size() >= 2) {
            res.add(new ArrayList<>(list));
        }

        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (pre > nums[i] || set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            list.add(nums[i]);
            dfs2(nums, list,i + 1, nums[i], res);
            list.remove(list.size() - 1);
        }
    }
}

