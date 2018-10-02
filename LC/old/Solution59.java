import java.util.Scanner;

/**
 * Spiral Matrix II
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * For example, Given n = 3,
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * Created by wuyan on 2015/12/20.
 */
public class Solution59 {
    /**
     * up     : row index not change, but column index changed
     * right  : row index changed, but column index changed
     * bottom : row index not change, but column index changed
     * left  :  row index changed, but column index changed
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int layer = n / 2 + 1;
        int result[][] = new int[n][n];
        int num = 1;
        for (int i = 0; i < layer; i++) {
            for (int j = i; j < n - i; j++)
                result[i][j] = num++;
            for (int j = i + 1; j < n - i; j++)
                result[j][n - i - 1] = num++;
            for (int j = n - i - 1; j > i; j--)
                result[n - i - 1][j - 1] = num++;

            for (int j = n - i - 1; j > i + 1; j--)
                result[j - 1][i] = num++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int n = cin.nextInt();
            Solution59 Spiral_matrix = new Solution59();
            int result[][] = Spiral_matrix.generateMatrix(n);
            for (int[] A : result) {
                for (int elem : A)
                    System.out.print(elem + "     ");
                System.out.println();
            }
        }
        cin.close();
    }
}
