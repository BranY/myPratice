package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Multiply Strings
 *         
 *         Given two numbers represented as strings, return multiplication of the numbers as a string.
 *         Note: The numbers can be arbitrarily large and are non-negative.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution43
{	
	public String multiply(String num1, String num2) {
		
		String n1 = new StringBuilder(num1).reverse().toString();
		String n2 = new StringBuilder(num2).reverse().toString();
		
		int[] d = new int[n1.length() + n2.length()];
		for(int i = 0; i < n1.length(); i++){
			for(int j = 0; j < n2.length(); j++){
				d[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < d.length; i++){
			int digit = d [i] % 10;		
			int carry = d[i] / 10;
			if( i+1 < d.length){
				d[i + 1] += carry;
			}
			sb.insert(0, digit);
		}
		
		while(sb.charAt(0) == '0' && sb.length()>1){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{   
    		String num1 = cin.next();
    		String num2 = cin.next();
    		Solution43 multi = new Solution43();
    		System.out.println(multi.multiply(num1, num2));
    	}
    	cin.close();
    }
}