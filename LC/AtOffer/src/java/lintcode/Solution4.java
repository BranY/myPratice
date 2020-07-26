package src.java.lintcode;

public class Solution4 {
    // 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
    public int sqrt(int x) {
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }

    // Implement double sqrt(double x) and x >= 0.
    double esp = 1e-10;
    public double sqrt(double x) {
        // write your code here
        if(x == 0){
            return 0;
        }
        double start = 0;
        double end = x;
        if(x < 1){
            end = 1;
        }
        while(start + esp < end){
            double mid = (end + start)/2;
            //System.out.println(mid);
            if(mid * mid > x){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(end * end <= x){
            return end;
        }else{
            return start;
        }
    }
}
