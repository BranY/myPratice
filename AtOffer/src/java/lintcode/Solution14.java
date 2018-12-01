package lintcode;

import java.util.Comparator;

public class Solution14 {

    class Pair {
        public int x, y, val;
        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }


    class PairComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.val - b.val;
        }
    }



    /**
     * 在一个排序矩阵中找从小到大的第 k 个整数。
     *
     * 排序矩阵的定义为：每一行递增，每一列也递增。
     */

    class ResultType {
        public int num;
        public boolean exists;
        public ResultType(boolean e, int n) {
            exists = e;
            num = n;
        }
    }

    public ResultType check(int value, int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        boolean exists = false;
        int num = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] == value)
                exists = true;

            if (matrix[i][j] <= value) {
                num += i + 1;
                j += 1;
            } else {
                i -= 1;
            }
        }

        return new ResultType(exists, num);
    }

    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        int n = matrix.length;
        int m = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[n - 1][m - 1];

        // left + 1 < right
        while (left <= right) {
            int mid = left + (right - left) / 2;
            ResultType type = check(mid, matrix);
            if (type.exists && type.num == k) {
                return mid;
            } else if (type.num < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
