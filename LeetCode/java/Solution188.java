package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Best Time to Buy and Sell Stock IV
 *         Say you have an array for which the ith element is the price of a given stock on day i.
 *         Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *         Note:
 *         You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *         Credits:
 *         Special thanks to @Freezen for adding this problem and creating all test cases.
 *   History:
 *   @author wuyan
 *   2015-4-25
 */
public class Solution188
{
	/*
	 * 一个是当前到达第i天可以最多进行j次交易,最好的利润是多少（global[i][j]）, 另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）
	 * global[i][j] = max(local[i][j],global[i-1][j])，
	 * 全局（到达第i天进行j次交易的最大收益） = max{局部（在第i天交易后，恰好满足j次交易），全局（到达第i-1天时已经满足j次交易）}
	 * 
	 * local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
	 * 也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），
	 * 第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，
	 * 而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）。
	 * 
	 * 局部（在第i天交易后，总共交易了j次） =  max{情况2，情况1}
	 * 情况1：在第i-1天时，恰好已经交易了j次（local[i-1][j]），那么如果i-1天到i天再交易一次：即在第i-1天买入，第i天卖出（diff），
	 *      则这不并不会增加交易次数！【例如我在第一天买入，第二天卖出；然后第二天又买入，第三天再卖出的行为  和   第一天买入，第三天卖出  的效果是一样的，
	 *      其实只进行了一次交易！因为有连续性】
	 * 情况2：第i-1天后，共交易了j-1次（global[i-1][j-1]），因此为了满足“第i天过后共进行了j次交易，且第i天必须进行交易”的条件：
	 *      我们可以选择1：在第i-1天买入，然后再第i天卖出（diff），或者选择在第i天买入，然后同样在第i天卖出（收益为0）。
	 *  343ms
	 */
    
    public int maxProfit(int k,int[] prices) {       // k: k times transactions
        int len = prices.length;
        if(len == 0) {
            return 0;
        }
        if (k >= prices.length) return solveMaxProfit(prices);
        int[] global = new int[k+1];
        int[] local = new int[k+1];
        for (int i = 0; i < prices.length - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; --j) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }
	   private int solveMaxProfit(int[] prices) {
		   int res = 0;
	        for (int i = 1; i < prices.length; ++i) {
	            if (prices[i] - prices[i - 1] > 0) {
	                res += prices[i] - prices[i - 1];
	            }
	        }
	        return res;
	}
	public static void main(String[] args)
		{
			Scanner cin = new Scanner(System.in);
			while (cin.hasNext()) {
				int k = cin.nextInt();
				String[] str = cin.next().split(",");
				int[] prices = new int[str.length];
				
				for(int i = 0; i < str.length; i++)
					prices[i] = Integer.parseInt(str[i]);
				Solution188 BTBSS = new Solution188();
				System.out.println(BTBSS.maxProfit(k, prices));
			}
			cin.close();
		}
}