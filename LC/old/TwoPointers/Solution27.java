package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Remove Element 
 *         Given an array and a value, remove all instances of that value in place and return the new length.
 *         The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *   @author wuyan  version 1 
 *   2015-4-26
 */
/*
 * test case
 * 123,4
 * 22222,2
 * 123,2
 * 1232425,2
 * 
 */
public class Solution27
{
	 public int removeElement(int[] A, int elem) {
		 int cnt = 0;
		 int size = A.length;
		 for (int i = 0; i < A.length; i++)
		 {
			 if (A[i] == elem)
				 cnt++;
			 else if (cnt > 0)
			 {
				 A[i - cnt] = A[i];
			 }
		 }
		return size - cnt;   
	 }
	 public static void main(String[] args)
	 {
		 Scanner cin = new Scanner(System.in);
		 while (cin.hasNext())
		 {
			 int elem = cin.nextInt();
			 String[] str = cin.next().split(",");
			 int[] array = getData(str);
			 Solution27 re = new Solution27();
			 System.out.println(re.removeElement(array, elem));
			 for (Integer b: array)
				 System.out.print(b +",");
		 }
		 cin.close();
	 }
	private static int[] getData(String[] str) {
		int[] result = new int[str.length];
		for (int i = 0; i < str.length; i++)
			result[i] = Integer.parseInt(str[i]);
		return result;
	}
}