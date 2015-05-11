package LeetCode;

import java.util.LinkedList;
import java.util.Scanner;
/*
 * Program :
 *         Longest Valid Parentheses
 *         Given a string containing just the characters '(' and ')', 
 *         find the length of the longest valid (well-formed) parentheses substring.
 *         For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *         Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-10
 */
public class Solution32
{
	/*
	 * 基本思路就是维护一个栈，遇到左括号就进栈，遇到右括号则出栈，并且判断当前合法序列是否为最长序. 具体来说，主要问题就是遇到右括号时如何判断当前的合法序列的长度。
	 * 比较健壮的方式如下：
	 * (1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）；
	 * (2) 否则弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置i - start + 1；
	 *                 否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而且左括号出过栈了。
	 */
	public int longestValidParentheses(String s) {
	    if(s == null || s.length() == 0)
	        return 0;
	    LinkedList<Integer> stack = new LinkedList<Integer>();
	    int start = 0;
	    int max = 0;
	    for(int i = 0;i < s.length();i++)
	    {
	        if(s.charAt(i)=='(')
	        {
	            stack.push(i);
	        }
	        else
	        {
	            if(stack.isEmpty())
	            {
	                start = i + 1;
	            }
	            else
	            {
	                stack.pop();
	                max = stack.isEmpty() ? Math.max(max,i - start + 1): Math.max(max, i - stack.peek());
	            }
	        }
	    }
	    return max;
	}
	/*
	 * Dynamic Programming
	 * 设bool数组dp[i][j]表示子串s[i…j]是否可以完全匹配，那么动态规划方程如下:
	 * 初始化dp数组为false
	 * 如果dp[i+1][j-1] == true && s[i] == ‘(’&&s[j] == ‘)’
	 * 或者 存在k = i+1…j-1 使得dp[i][k] == true && dp[k+1][j] = true ，则dp[i][j] = true
	 */
	public int longestValidParentheses2(String s) {
		int len = s.length();
		boolean[][] dp  = new boolean[len][len];
		int res = 0;
		for (int i = 0; i< len - 1; i++)
		{
			if(s.charAt(i) == '(' && s.charAt(i + 1)== ')')
            {
                dp[i][i + 1] = true;
                res = 2;
            }
		}
		for(int k = 4; k <= len; k+=2)//k表示子串长度，只有长度为偶数的子串才可能是合法括号
            for(int i = 0; i <= len-k; i++)//i表示子串的起始位置
            {
                if(dp[i+1][i+k-2] && s.charAt(i) == '(' && s.charAt(i + k - 1) == ')')
                    dp[i][i+k-1] = true;
                else
                {
                    for(int j = i+1; j <= i+k-3; j++)
                        if(dp[i][j] && dp[j+1][i+k-1])
                            dp[i][i+k-1] = true;
                }
                if(dp[i][i+k-1])
                	res = k;
            }
        return res;
	}
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String str = cin.next();
			Solution32 longVP = new Solution32();
			System.out.println(longVP.longestValidParentheses2(str));
		}
		cin.close();
	}
}