package pkg.ywj.weekContest.wc199;

/**
 * Description link: https://leetcode-cn.com/problems/shuffle-string/
 * Tag:
 * 2020/7/26_18:57  BranY
 *
 * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
 * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
 * 返回重新排列后的字符串。
 */
public class Problem5472 {
    public String restoreString(String s, int[] indices) {
        char[] result = new char[indices.length];
        for(int index = 0; index < indices.length; index++){
            result[indices[index]] = s.charAt(index);
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String str = "codeleet";
        int[] indices = {4,5,6,7,0,2,1,3};

        Problem5472 su = new Problem5472();
        String f = su.restoreString(str, indices);
        System.out.println(f);
    }
}
