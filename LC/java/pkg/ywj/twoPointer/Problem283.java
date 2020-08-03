package pkg.ywj.twoPointer;

/**
 * Description link:
 * Tag:
 * 2020/7/27_22:31  BranY
 */
public class Problem283 {
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    public static void main(String[] args) {

    }
}
