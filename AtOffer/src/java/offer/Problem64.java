package offer;

/**
 * Created by Yang on 2017/4/23.
 * 题目：求1 + 2 + ···+ n，
 * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A ? B : C）
 */
public class Problem64 {
    int res = 0;

    public int sum_Solution(int n) {
        res = 0;
        add(n);
        return res;
    }

    /**
     * 使用逻辑与运算的短路特性终止递归
     * @param n
     * @return
     */
    private boolean add(int n) {
        res += n;
        return n!= 0 && add(n-1);
    }

    public static void main(String[] args) {
        Problem64 accumulate = new Problem64();

        System.out.println(accumulate.sum_Solution(10));
        System.out.println(accumulate.sum_Solution(100));
        System.out.println(accumulate.sum_Solution(1000));
    }
}
