package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Best Time to Buy and Sell Stock II 
 *         Say you have an array for which the ith element is the price of a given stock on day i.
 *         Design an algorithm to find the maximum profit.
 *         You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 *         However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *   History:
 *   @author wuyan
 *   2015-4-25
 */
public class Solution122
{
	/*
	 * 贪心法，本题和前面的Best Time to Buy and Sell Stock 不同在于，本题可以多次买卖股票，  
	 * 从而累积赚取所有的价格差。因此用贪心法，基本思想是锁定一个低价，然后在价格升到局部最高点  
	 * （即下一天的价钱就下降了）时候，抛出股票，然后把下一天较低的价钱作为买入，接着计算。  
	 * 要注意最后要处理最后一次的利润  
	 * 364ms
	 */
    public int maxProfit(int[] prices) {
    	if (prices.length == 0)
    		return 0;
    	int totalProfit = 0;
        int startIndex = 0;
        int i;
        for(i = 1; i < prices.length; i++){
        	if(prices[i] < prices[i - 1]){
        		totalProfit += prices[i - 1] - prices[startIndex];
        		startIndex = i;
        	}
        }
        if(prices[i - 1] > prices[startIndex]){
        	totalProfit += prices[i - 1] - prices[startIndex];
        }
        
        return totalProfit;
    }
    /*
     * 累计和
     * 374ms
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if(len == 0) {
            return 0;
        }
        
        int max = 0;
        for(int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            if(diff > 0) {
                max += diff;
            }
        }
        return max;
    }
    /*
     * 345ms
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if(len <= 1)return 0;
        int i = 0;
        int res = 0;
        while(i < len - 1)
        {
            int buy, sell;
            while(i + 1 < len && prices[i + 1] < prices[i])
            	i++;
            buy = i++;
            while(i < len && prices[i] >= prices[i - 1])
            	i++;
            sell = i-1;
            res += prices[sell] - prices[buy];
        }
        return res;
    }
    public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			int size = cin.nextInt();
			int[] prices = new int[size];
			
			for(int i = 0; i < size; i++)
				prices[i] = cin.nextInt();
			Solution122 BTBSS = new Solution122();
			System.out.println(BTBSS.maxProfit(prices));
		}
		cin.close();
	}
}