package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Pow(x, n)
 *         
 *         Implement pow(x, n).
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-30
 */
public class Solution50
{
	/*
	 * 就是把n看成是以2为基的位构成的，因此每一位是对应x的一个幂数，然后迭代直到n到最高位。
	 * 比如说第一位对应x，第二位对应x*x,第三位对应x^4,...,第k位对应x^(2^(k-1)),
	 * 可以看出后面一位对应的数等于前面一位对应数的平方，所以可以进行迭代。因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)。
	 */
	public double myPow(double x, int n) {
    	if (n == 0)
    		return 1;
    	if (x != 0 && n == 0)
    		return 0;
    	if (x == 0 || x == 1) return x;
    	
    	double res = 1.0;
    	if (n < 0) {
    		if (x >= 1.0/Double.MAX_VALUE || x<= 1.0/-Double.MAX_VALUE)
    			x = 1.0/x;
    		else
    			return Double.MAX_VALUE;
    		if (n == Integer.MIN_VALUE)
    		{
    			res *= x;
    			n++;
    		}
    	}
    	
    	n = Math.abs(n);
    	boolean isNegative = false;
    	if (n % 2 == 1 && x < 0)
    		isNegative = true;
    	x = Math.abs(x);
    	while (n > 0)
    	{
    		if ((n & 1) == 1)
    		{
    			if (res > Double.MAX_VALUE)
    				return Double.MAX_VALUE;
    			res *= x;
    		}
    		x *= x;
    		n = n >> 1;
    	}
    	
    	return isNegative ? -res : res;
    }
	
	
	public double myPow2(double x, int n) {
    	if (n == 0)
    		return 1;
    	if (x != 0 && n == 0)
    		return 0;
    	if (x == 0 || x == 1) return x;
	    double half = myPow(x, n/2);
	    if (n % 2 == 0)
	    {
	        return half*half;
	    }
	    else if (n > 0)
	    {
	        return half * half * x;
	    }
	    else
	    {
	        return half / x * half;
	    }
	}
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	
    	while (cin.hasNext())
    	{
    		double x = cin.nextInt();
    		int n = cin.nextInt();
    		Solution50 pow = new Solution50();
    		System.out.println(pow.myPow(x, n));
    	}
    	cin.close();
    }
}