package LeetCode;

import java.util.Scanner;

/*
 * Program :
/*
 * Program :
 *         Say you have an array for which the ith element is the price of a given stock on day i.
 *         If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 *         design an algorithm to find the maximum profit.
 *   History:
 *   @author wuyan
 *   2015-4-4
 */
public class Solution121{
	/*
	 * run:261
	 */
	public int maxProfit(int[] prices) {
		if(prices.length == 0) 
			return 0;
		int low = prices[0];
        int ans = 0;
        for(int i = 1; i < prices.length; i++){
        	if(prices[i] < low) 
        		low = prices[i];
        	else if(prices[i] - low > ans) 
        		ans = prices[i] - low;
        }
        return ans;
   }
	/*
	 * run:243
	 */
	public int maxProfit2(int[] prices) {
		 int len = prices.length;
	     if(len <= 1)
	    	 return 0;
	     int i = 0;
	     int ibuy = 0, isell = 0, leastBuy = 0; //leastBuy为前面已经扫描过的最低价格
	     while(i < len - 1){
	    	 int buy, sell;
	    	 while(i + 1 < len && prices[i+1] < prices[i])i++;//递减区间
	         buy = i++;
	         while(i < len && prices[i] >= prices[i-1])i++;//递增区间
	         sell = i - 1;
	         //此时从prices[buy]~prices[sell]是递增区间

	         if(prices[buy] <= prices[ibuy])
	         {
	        	 if(prices[sell] - prices[buy] >= prices[isell] - prices[ibuy])
	        	 {
	        		 ibuy = buy;
	                 isell = sell;
	             }
	         }else{
	        	 if(prices[sell] > prices[isell])
	        		 isell = sell;
	         }
	         
	         if(prices[buy] > prices[leastBuy])
	        	 ibuy = leastBuy;
	         
	         if(prices[leastBuy] > prices[buy])
	        	 leastBuy = buy;
	        }
	        return prices[isell] - prices[ibuy];
	}
	/*
	 * Dynamic Programming
	 * run:233
	 */
	public int maxProfit3(int[] prices) {
        int len = prices.length;
        if(len <= 1)return 0;
        int res = prices[1] - prices[0], minprice = prices[0];
        for(int i = 2; i < len; i++)
        {
            minprice = prices[i-1] > minprice ? minprice : prices[i-1];
            if(res < prices[i] - minprice)
                res = prices[i] - minprice;
        }
        if(res < 0)
        	return 0;
        else 
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
			Solution121 BTBSS = new Solution121();
			System.out.println(BTBSS.maxProfit(prices));
		}
		cin.close();
	}
}