package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * Program :
 *         Spiral Matrix
 *         Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *         For example,
 *         Given the following matrix:
 *         [
 *           [ 1, 2, 3 ],
 *           [ 4, 5, 6 ],
 *           [ 7, 8, 9 ]
 *         ]
 *         You should return [1,2,3,6,9,8,7,4,5].
 *   History:
 *   @author wuyan  version 1 
 *   2015-6-2
 */
public class Solution54
{
	/*
	 * 上
	 * 右
	 * 下
	 * 左
	 * 48
	 * 
	 */
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> list = new ArrayList<Integer>();
	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
	        return list;
	    int min = Math.min(matrix.length, matrix[0].length);
	    int layer = min / 2;
	    for (int i = 0; i < layer; i++)
	    {
	    	for (int top = i; top < matrix[0].length - i - 1; top++)
	    		list.add(matrix[i][top]);
	    	
	    	for (int right = i; right < matrix.length - i - 1; right++)
	    		list.add(matrix[right][matrix[0].length-i-1]);
	    	
	    	for (int bottem = matrix[0].length - i - 1; bottem > i; bottem--)
	    		list.add(matrix[matrix.length - i -1][bottem]);
	    	
	    	for (int left = matrix.length - i -1; left > i; left--)
	    		list.add(matrix[left][i]);
	    }
	    
	    if(min % 2 == 1)
        {
            if(matrix.length < matrix[0].length)
            {
                for(int i=layer; i<matrix[0].length-layer;i++)
                {
                    list.add(matrix[layer][i]);
                }
            }
            else
            {
                for(int i = layer; i < matrix.length - layer;i++)
                {
                    list.add(matrix[i][layer]);
                }
            }
        }
	    return list;
    }
    public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int size = cin.nextInt();
			ArrayList<String[]> str = new ArrayList<String[]>();
			int k = 0;
			while (k < size)
			{
				String[] tmp = cin.next().split(",");
				str.add(tmp);
				k++;
			}
			int col = str.get(0).length;
			int [][] matrix = new int[size][col];
			for (int i = 0; i < size; i++)
			{
				String[] a = str.get(i);
				for (int j = 0; j < col; j++)
					matrix[i][j] = Integer.parseInt(a[j]);
			}
			Solution54 spiral = new Solution54();
			List<Integer> list = spiral.spiralOrder(matrix);
			for (Integer b: list)
				System.out.print(b + " ");
			System.out.println();
		}
		cin.close();
	}
}