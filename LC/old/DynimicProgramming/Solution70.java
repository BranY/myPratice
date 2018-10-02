package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Climbing Stairs  
 *         You are climbing a stair case. It takes n steps to reach to the top.
 *         
 *         Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution70 {
	/*
	 * 1. 降格思想; 即从1阶台阶向上递推；fibonacci 
	 * 2. 升格思想；
	 *         n-1 to n has one way;
	 *         n-2 to n has two way;
	 *   So, F(n) = F(n-1)+ F(n-2), F(1) = 1, F(2) = 2;
	 *   fibonacci 
	 * 3. 分格思想
	 *   (1) n step: all one-step, so 1 way;
	 *   (2) n - 1 step: Exist one two-steps, so has 1C(n-1);
	 *   (3) n - 2 step: Exist two two-steps, so has 2C(n-2);
	 *   ...
	 *   
	 */
    public int climbStairs(int n) {
    	if (n <= 0)
    		return 0;
    	if (n < 4)
    		return n;
    	int[] way = new int[n];
    	way[0] = 1;
    	way[1] = 2;
    	for (int i = 2; i < n; i ++)
    		way[i] = way[i - 1] + way[i - 2];
        return way[n - 1];
    }
    public int climbStairs0(int n) {
    	if (n <= 0)
    		return 0;
    	if (n < 4)
    		return n;
    	int a = 2, b = 3, c = 5;
    	for (int i = 5; i <= n; i++)  
        {  
            a = c;  
            c = b+c;  
            b = a;  
        }  
        return c;
    }
    /*
     * when n >= 24,it's wrong
     */
    public int climbStairs1(int n) {
    	if (n <= 0)
    		return 0;
    	if (n < 4)
    		return n;
    	int way = 0;
    	int size = 1;
    	way += 1;
    	--n;
    	while (n >= size)
    	{
    		way += getCombinationNumber(n, size);
    		n --;
    		size++;
    	}
    	return way;
    }
    public int getCombinationNumber(int n, int k)
    {
    	if (k > n)
    		return 0;
    	int a = 1, b = 1;
    	for (int i = 1; i <= n; i++)
    	{
    		if (i > (n - k))
    			a *= i;
    		if (i <= k)
    			b *= i;
    	}
    	return a / b;
    }
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int n = cin.nextInt();
    		Solution70 climb = new Solution70();
    		System.out.println(climb.climbStairs1(n) + " " + climb.climbStairs(n));
    	}
    	cin.close();
    }
}