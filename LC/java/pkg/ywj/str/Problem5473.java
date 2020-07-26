package pkg.ywj.str;

/**
 * Description link: https://leetcode-cn.com/problems/bulb-switcher-iv/
 * Tag: str
 * 灯泡开关 IV
 * 房间中有 n 个灯泡，编号从 0 到 n-1 ，自左向右排成一行。最开始的时候，所有的灯泡都是 关 着的。
 * 请你设法使得灯泡的开关状态和 target 描述的状态一致，其中 target[i] 等于 1 第 i 个灯泡是开着的，等于 0 意味着第 i 个灯是关着的。
 * 有一个开关可以用于翻转灯泡的状态，翻转操作定义如下：
 * 选择当前配置下的任意一个灯泡（下标为 i ）
 * 翻转下标从 i 到 n-1 的每个灯泡
 * 翻转时，如果灯泡的状态为 0 就变为 1，为 1 就变为 0 。
 * 返回达成 target 描述的状态所需的 最少 翻转次数。
 *
 * 输入：target = "10111"
 * 输出：3
 * 解释：初始配置 "00000".
 * 从第 3 个灯泡（下标为 2）开始翻转 "00000" -> "00111"
 * 从第 1 个灯泡（下标为 0）开始翻转 "00111" -> "11000"
 * 从第 2 个灯泡（下标为 1）开始翻转 "11000" -> "10111"
 * 至少需要翻转 3 次才能达成 target 描述的状态
 */
public class Problem5473 {

    // 模拟
    public int minFlips(String target) {
        int cur = 0;
        int sum = 0;
        for(int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != (cur + '0')) {
                sum++;
                cur = cur == 0 ? 1 : 0;
            }
        }
        return sum;
    }

    /**
     * 首先要记住一点，就是翻转一次那么状态就会从 0->1 || 1->0
     *
     * 如果翻转的是两次那么后面翻转的状态是不是就相当于没有变化呢，所以
     * 结论就是，只需要记住前面翻转的是奇数次还是偶数次就行。
     * 需要反过来思考，从全0变成target 和target变成全0是不是一回事呢
     * 深度解析判断条件：
     * target.charAt(i) != '0' && res%2 == 0 ：此时target[i]=='1' 并且翻转的是偶数次
     * 所以'1'没有变化，最终结果是把它变成'0'，所以需要翻转
     *
     * target.charAt(i) != '1' && res%2 != 0 ：此时target[i]=='0' 并且翻转的是奇数次，
     * 所以'0'已经被翻转成'1'了，最终结果是'0',所以需要翻转
     */
    public int minFlips2(String target) {
        int sum = 0;
        for(int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != '0' && sum % 2 == 0) {
                sum++;
            }

            if (target.charAt(i) != '1' && sum % 2 != 0) {
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Problem5473 su = new Problem5473();
        System.out.println(su.minFlips("10111")); //3
        System.out.println(su.minFlips("101")); //3
        System.out.println(su.minFlips("00000")); // 0
        System.out.println(su.minFlips2("001011101")); // 5
    }
}
