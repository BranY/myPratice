package LeetCode;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Program :
 *         Next Permutation
 *         
 *         Implement next permutation, which rearranges numbers into the lexicographically next greater permutation 
 *         of numbers.
 *         If such arrangement is not possible, it must rearrange it as the lowest possible order 
 *         (ie, sorted in ascending order).
 *         The replacement must be in-place, do not allocate extra memory.
 *         Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *         1,2,3 → 1,3,2
 *         3,2,1 → 1,2,3
 *         1,1,5 → 1,5,1
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-10
 */
public class Solution31
{
	/*
	 * 所谓一个排列的下一个排列的意思就是 这一个排列与下一个排列之间没有其他的排列。这就要求这一个排列与下一个排列有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
	 * 7 8 6 9 8 7 2
	 * 7 8 7 2 6 8 9
	 * 
	 * (2,3,6,5,4,1)
	 * (2,4,1,3,5,6)
	 * 1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
	 * 2) 接下来分两种情况：
	 *   (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
	 *   (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数小的数字，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。
	 *       最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
	 */
	public void nextPermutation(int[] num) {
	    if(num==null || num.length==0)
	        return;
	    int i = num.length - 2;
	    while(i >= 0 && num[i] >= num[i + 1])
	    {
	        i--;
	    }
	    if(i >= 0)
	    {
	        int j = i + 1;
	        while(j < num.length && num[j] > num[i])
	        {
	            j++;
	        }
	        j--;
	        int temp = num[i];
	        num[i] = num[j];
	        num[j] = temp;
	    }
	    reverse(num, i + 1);
	}
	private void reverse(int[] num, int index)
	{
	    int l = index;
	    int r = num.length - 1;
	    while(l < r)
	    {
	        int temp = num[l];
	        num[l] = num[r];
	        num[r] = temp;
	        l++;
	        r--;
	    }
	}
	/*
	 * (2,3,6,5,4,1)
	 * (2,4,1,3,5,6)
	 */
	public void nextPermutation2(int[] num)
	{
		if(num.length <= 1)
			return ;
		for(int i = num.length - 2; i >= 0; i--)
		{
			if(num[i] < num[i + 1])
			{
				int j;
				for(j = num.length - 1; j >= i; j--)
					if(num[i] < num[j])
						break;
				num[i] = num[i] ^ num[j];
				num[j] = num[i] ^ num[j];
				num[i] = num[i] ^ num[j];
				
				Arrays.sort(num, i + 1, num.length);
				return ;
			}
		}
		
		for(int i = 0; i < num.length / 2; i++)
		{
			int tmp = num[i];
			num[i] = num[num.length - i - 1];
			num[num.length - i - 1] = tmp;
		}
		return ;
	}
    public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String[] words = cin.next().split(",");
			int[] num = new int[words.length];
			for (int i = 0; i < num.length; i++)
				num[i] = Integer.parseInt(words[i]);
			Solution31 nextPerm = new Solution31();
			nextPerm.nextPermutation2(num);
			for (int a: num)
				System.out.print(a + " ");
			System.out.println();
		}
		cin.close();
	}
}