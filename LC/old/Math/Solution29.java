package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Divide Two Integers 
 *         
 *         Divide two integers without using multiplication, division and mod operator.
 *         If it is overflow, return MAX_INT.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-25
 */
public class Solution29
{
	/*
	 *  Time Limit Exceeded
	 *  Last executed input:	2147483647, 2
	 */
    public int divide1(int dividend, int divisor) {
    	if (divisor == 0)
    		return Integer.MAX_VALUE;
    	if (dividend == 0)
    		return 0;
    	int result = 0, y = 0;
    	int sign = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) ? 1 : -1;
    	dividend = Math.abs(dividend);
    	divisor = Math.abs(divisor);
    	for (int i = 0;  i < dividend; i++)
    	{
    		y += divisor;
    		if (y  <= dividend)
    			result++;
    		else
    			break;
    	}
		return sign * result;
    }
    /*
     * 我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。
     * 基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，
     * 直到基为0为止。
     */
    public int divide(int dividend, int divisor) {
        if(divisor == 0)
        {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = (dividend^divisor)>>>31 == 1;
        int res = 0;
        if(dividend == Integer.MIN_VALUE)
        {
            dividend += Math.abs(divisor);
            if(divisor == -1)
            {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        if(divisor == Integer.MIN_VALUE)
        {
            return res;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        while(divisor <= (dividend>>1))
        {
            divisor <<= 1;
            digit++;
        }
        while(digit >= 0) 
        {
            if(dividend >= divisor)
            {
                res += 1<<digit;
                dividend -= divisor;
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg? -res : res;
    }
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int dividend = cin.nextInt();
    		int divisor = cin.nextInt();
    		Solution29 di = new Solution29();
    		System.out.println(di.divide(dividend, divisor));
    	}
    	cin.close();
    }
}