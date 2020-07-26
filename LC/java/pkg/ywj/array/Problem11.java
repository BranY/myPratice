package pkg.ywj.array;

/**
 * Description link: https://leetcode-cn.com/problems/container-with-most-water/
 * Tag: Array, two pointer
 * 2020/2/3_22:25  BranY
 */
public class Problem11 {
    /**
     * https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode/
     */
    public int maxArea(int[] height) {
        int maxArea = 0, l =0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return maxArea;
    }

    public static void main(String[] args) {

    }
}
