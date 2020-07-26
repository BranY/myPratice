package pkg.ywj.array;

/**
 * Description link:
 * Tag:
 * 2020/2/4_21:53  BranY
 */
public class problem27 {
    // https://leetcode-cn.com/problems/remove-element/solution/hua-jie-suan-fa-27-yi-chu-yuan-su-by-guanpengchn/
    public int removeElement(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans;) {
            if (nums[i] == val) {
                nums[i] = nums[ans - 1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
