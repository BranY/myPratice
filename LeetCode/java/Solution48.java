package LeetCode;

import java.util.ArrayList;
import java.util.Scanner;

/*
* Program :
*          Rotate Image 
*          You are given an n x n 2D matrix representing an image.
*          Rotate the image by 90 degrees (clockwise).
*          Follow up:
*          Could you do this in-place?
*   History:
*   @author wuyan  version 1
*   2015-5-27
*/
public class Solution48
{
	/*
	 * 这道题虽然操作起来有一点繁琐，但是思路比较简单，就是考察一下数组的基本操作。
	 * 基本思路是把图片分为行数/2层，然后一层层进行旋转，每一层有上下左右四个列，
	 * 然后目标就是把上列放到右列，右列放到下列，下列放到左列，左列放回上列，中间保存一个临时变量即可。
	 * layer:表示旋转的层数。  0 - layerNum
	 * i: layer --> matrix.length - layer - 1 (表示该层的数目)
	 * matrix.length - layer - 1表示的随着层数的增加，在改行会占用layer+1个数字
	 *                     左上                                       右上
	 *                      
	 *                      
	 *                     左下                                      右下
	 * 左上 = 左下    左下 = 右下   右下 = 右上       右上 = 左上
	 * TopLeft:     layer                 --> i
	 * BottemLeft:  matrix.length-1-i     --> layer
	 * BottemRight: matrix.length-1-layer --> matrix.length-1-i
	 * TopRight:    i                     --> matrix.length-1-layer
	 */
	public void rotate(int[][] matrix) {
	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
	        return;
	    int layerNum = matrix.length / 2;
	    for(int layer = 0; layer < layerNum;layer++)
	    {
	        for(int i = layer; i < matrix.length - layer - 1; i++)
	        {
	            int temp = matrix[layer][i];
	            matrix[layer][i] = matrix[matrix.length-1-i][layer];
	            matrix[matrix.length-1-i][layer] = matrix[matrix.length-1-layer][matrix.length-1-i];
	            matrix[matrix.length-1-layer][matrix.length-1-i] = matrix[i][matrix.length-1-layer];
	            matrix[i][matrix.length-1-layer] = temp;
	        }
	    }
	    
	    for (int i = 0; i < matrix.length; i++)
	    {
	    	for (int j = 0; j < matrix[i].length ; j++)
	    		System.out.print(matrix[i][j] + " ");
	    	System.out.println();
	    }
	}
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int size = cin.nextInt();
			ArrayList<String[]> str = new ArrayList<String[]>();
			while (size > 0)
			{
				String[] tmp = cin.next().split(",");
				str.add(tmp);
				size--;
			}
			int [][] matrix = new int[size][size];
			for (int i = 0; i < size; i++)
			{
				String[] a = str.get(i);
				for (int j = 0; j < size; j++)
					matrix[i][j] = Integer.parseInt(a[j]);
			}
			Solution48 rotate = new Solution48();
			rotate.rotate(matrix);
		}
		cin.close();
	}
}