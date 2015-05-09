package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Sqrt(x)
 *         
 *         Implement int sqrt(int x).
 *         Compute and return the square root of x.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-30
 */
public class Solution69
{
	/*
	 * Binary search
	 */
    public int mySqrt(int x) {
    	if (x == 0 || x == 1)
    		return x;
    	long l = 0;
    	long r = x/2 + 1;
    	while (l <= r)
    	{
    		long mid = (l + r)/2;
    		long sq = mid * mid;
    		if (sq == x)
    			return (int) mid;
    		else if(sq < x)
    			l = mid + 1;
    		else
    			r = mid - 1;
    	}
    	return (int) r;
    }
    /*
     * Newton iteration
     * Let F(z) = x^2 - n;
     * F(z) = 0;  d(F(z))/dx = 2*x;
     * x = 0.5 * (xi + n/xi)
     */
    public int mySqrt2(int x) {
    	if (x == 0 || x == 1)
    		return x;
    	double xi = 0;
    	double xj = 1;
    	while (xj != xi)
    	{
    		xi = xj;
    		xj = 0.5 * (xi + x/xi);
    	}
    	return (int)xj;
    }
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	
    	while (cin.hasNext())
    	{
    		int x = cin.nextInt();
    		Solution69 sq = new Solution69();
    		System.out.println(sq.mySqrt(x));
    	}
    	cin.close();
    }
}