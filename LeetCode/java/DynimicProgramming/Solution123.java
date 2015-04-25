package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Best Time to Buy and Sell Stock III 
 *         Say you have an array for which the ith element is the price of a given stock on day i.
 *         Design an algorithm to find the maximum profit.
 *         You may complete as many transactions as you like (ie, buy one and sell one share of the stock at most two times).
 *         However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *   History:
 *   @author wuyan
 *   2015-4-25
 */
public class Solution123
{
	/*
	 * 这一题约束最多只能买卖两次股票，并且手上最多也只能持有一支股票。因为不能连续买入两次股票，所以买卖两次肯定分布在前后两个不同的区间。
	 * 设p(i) = 区间[0,1,2...i]的最大利润 + 区间[i,i+1,....n-1]的最大利润（式子中两个区间内分别只能有一次买卖，这就是第一道题的问题），
	 * 那么本题的最大利润 = max{p[0],p[1],p[2],...,p[n-1]}
	 *根据第一题的算法，我们可以求区间[0,1,2...i]的最大利润；同理可以从后往前扫描数组求区间[i,i+1,....n-1]的最大利润，其递归式如下：
	 *
	 *dp[i-1] = max{dp[i], maxprices - prices[i-1]}  ,maxprices是区间[i,i+1,...,n-1]内的最高价格。 
	 *329ms   
	 */
    public int maxProfit(int[] prices) {
    	if (prices.length == 0)
    		return 0;
    	int len = prices.length;
        int[] maxFromHead = new int[len];
        maxFromHead[0] = 0;
        
    	int minprice = prices[0], maxprofit = 0;
        for(int i = 1; i < len; i++)
        {
            minprice = Math.min(prices[i-1], minprice);
            if(maxprofit < prices[i] - minprice)
                maxprofit = prices[i] - minprice;
            maxFromHead[i] = maxprofit;
        }
        int maxprice = prices[len - 1];
        int res = maxFromHead[len - 1];
        maxprofit = 0;
        for(int i = len - 2; i >=0; i--)
        {
            maxprice = Math.max(maxprice, prices[i+1]);
            if(maxprofit < maxprice - prices[i])
                maxprofit = maxprice - prices[i];
            if(res < maxFromHead[i] + maxprofit)
                res = maxFromHead[i] + maxprofit;
        }
        return res;
    }
    /*
     * 找寻一个点j，将原来的price[0..n-1]分割为price[0..j]和price[j..n-1]，分别求两段的最大profit。
     * 对于点j+1，求price[0..j+1]的最大profit时，很多工作是重复的，在求price[0..j]的最大profit中已经做过了。
     * 类似于Best Time to Buy and Sell Stock，可以在O(1)的时间从price[0..j]推出price[0..j+1]的最大profit。
     * 但是如何从price[j..n-1]推出price[j+1..n-1]？反过来思考，我们可以用O(1)的时间由price[j+1..n-1]推出price[j..n-1]。
     * 最终算法：
     * 数组l[i]记录了price[0..i]的最大profit，
     * 数组r[i]记录了price[i..n]的最大profit。
     * 已知l[i]，求l[i+1]是简单的，同样已知r[i]，求r[i-1]也很容易。
     * 最后，我们再用O(n)的时间找出最大的l[i]+r[i]，即为题目所求。
     * 369ms
     */
    public int maxProfit2(int[] prices) {
    	if (prices.length == 0)
    		return 0;
    	int len = prices.length;
    	int max = 0;
    	int[] left = new int[len];
    	int[] right = new int[len];
    	
    	process(prices, left, right);
    	
    	for(int i = 0; i < prices.length; i++){  
            max = Math.max(max, left[i] + right[i]);  
        }
    	return max;
    }
    private void process(int[] prices, int[] left, int[] right) {
    	left[0] = 0;
		int min = prices[0]; 
		
		for(int i = 1; i < left.length; i++){
			left[i] = Math.max(left[i - 1], prices[i] - min);
			min = Math.min(min, prices[i]);		
		}
		
		right[right.length-1] = 0;
		int max = prices[right.length-1];
		for(int i=right.length-2; i>=0; i--){
            right[i] = Math.max(right[i+1], max-prices[i]);	
            max = Math.max(max, prices[i]);	
		}
	}
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			int size = cin.nextInt();
			int[] prices = new int[size];
			
			for(int i = 0; i < size; i++)
				prices[i] = cin.nextInt();
			Solution123 BTBSS = new Solution123();
			System.out.println(BTBSS.maxProfit(prices));
		}
		cin.close();
	}
}