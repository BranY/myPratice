package LeetCode;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Program :
 *         Implement strStr() 
 *         Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *         
 *         The signature of the function had been updated to return the index instead of the pointer.
 *          If you still see your function signature returns a char * or String, please click the reload button 
 *           to reset your code definition.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution28
{
	/*
	 * it will not pass LeetCode, because Pattern
	 */
	public int strStr0(String haystack, String needle) {
		if (haystack.length() == 0 || needle.length() == 0)
			return -1;
		String regex = needle;
		Pattern p = Pattern.compile(regex);
		Matcher c = p.matcher(haystack);
		if (c.find())
			return c.start();
		else
			return -1; 
    }
	/*
	 * brute force
	 * Time Limit Exceeded
	 */
	public int strStr1(String haystack, String needle) {
		if (haystack.length() == 0 || needle.length() == 0 )
			return 0;
		if (haystack.length() < needle.length())
			return -1;
		int m = needle.length();
		int n = haystack.length();
		int i = 0, j , k;
		while (i < n)
		{
			k = i;
			for (j = 0; j < m && k < n; j++, k++)
			{
				if (haystack.charAt(k) != needle.charAt(j))
					break;
			}
			if (j == m){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int strStr(String haystack, String needle) {
		if (needle.length() == 0 )
			return 0;
		if (haystack.length() < needle.length())
			return -1;
		int i = 0, j = 0;
		int m = haystack.length();
		int n = needle.length();
		int[] next = getNext(needle);
		while (i < m && j < n)
		{
			if (j == -1 || haystack.charAt(i) == needle.charAt(j))
			{
				i++;
				j++;
			}
			else
			{
				j = next[j];
			}
		}
		if (j == n)
			return i - j;
		else
			return -1;
	}
	private int[] getNext(String needle) {
		int[] next = new int[needle.length()];
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < needle.length() - 1)
		{
			if (k == -1 || needle.charAt(j) == needle.charAt(k))
			{
				++k;
				++j;
				next[j] = k;
			}
			else
				k = next[k];
		}
		return next;
	}
	
	public int strStr2(String haystack, String needle) {
		if (needle.length() == 0 )
			return 0;
		if (haystack.length() < needle.length())
			return -1;
		int i = 0, j = 0;
		int m = haystack.length();
		int n = needle.length();
		int[] next = getNext1(needle);
		while (i < m && j < n)
		{
			if (j == -1 || haystack.charAt(i) == needle.charAt(j))
			{
				i++;
				j++;
			}
			else
			{
				j = next[j];
			}
		}
		if (j == n)
			return i - j;
		else
			return -1;
	}
	private int[] getNext1(String needle) {
		int[] next = new int[needle.length()];
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < needle.length() - 1)
		{
			if (k == -1 || needle.charAt(j) == needle.charAt(k))
			{
				++k;
				++j;
				if (needle.charAt(j) != needle.charAt(k))
					next[j] = k;
				else
					next[j] = next[k];
			}

			else
				k = next[k];
		}
		return next;
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{   
    		String hay = cin.nextLine();
    		String need = cin.nextLine();
    		Solution28 str = new Solution28();
    		System.out.println(str.strStr1(hay, need) + ", " + str.strStr(hay, need));
    	}
    	cin.close();
    }
}