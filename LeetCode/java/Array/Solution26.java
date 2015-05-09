package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Remove Duplicates from Sorted Array 
 *         Given a sorted array, remove the duplicates in place such that each element appear only once and 
 *         return the new length.
 *         Do not allocate extra space for another array, you must do this in place with constant memory.
 *         For example,
 *         Given input array A = [1,1,2],
 *         Your function should return length = 2, and A is now [1,2].
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-26
 */
public class Solution26
{
	public int removeDuplicates(int[] A) {
		if (A.length == 0)
			return 0;
		int cnt =  1;
		for (int i = 1; i < A.length; i++)
		{
			if (A[i] == A[i - 1])
				continue;
			else{
				A[cnt++] = A[i];
			}
		}
		return cnt;
    }
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String[] str = cin.next().split(",");
			int[] array = getData(str);
			Solution26 rde = new Solution26();
			System.out.println(rde.removeDuplicates(array));
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