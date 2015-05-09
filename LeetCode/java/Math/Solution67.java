package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Add Binary  
 *         Given two binary strings, return their sum (also a binary string).
 *         For example,
 *         a = "11"
 *         b = "1"
 *         Return "100".
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution67
{
    public String addBinary(String a, String b) {
    	if (a == null && b == null)
    		return a;
    	int cin = 0;
    	StringBuilder str = new StringBuilder();
    	int i = a.length() - 1;
    	int j = b.length() - 1;
    	while (i >= 0 && j >= 0)
    	{
    		int cout = (Integer.parseInt(a.substring(i, i+1)) + Integer.parseInt(b.substring(j, j+1)) + cin) % 2;
    		cin = (Integer.parseInt(a.substring(i, i+1)) + Integer.parseInt(b.substring(j, j+1)) + cin) / 2;
    		str.append(cout);
    		i--;
    		j--;
    	}
    	if (i < 0 && j < 0)
    	{
    		if (cin != 0)
    			str.append(cin);
    	}
    	else if (i >= 0)
    	{
    		while (i >= 0)
    		{
    			int cout = (Integer.parseInt(a.substring(i, i + 1))  + cin) % 2;
        		cin = (Integer.parseInt(a.substring(i, i + 1)) + cin) / 2;
        		str.append(cout);
        		i--;
    		}
    		if (cin != 0)
    			str.append(cin);
    	}
    	else if (j >= 0)
    	{
    		while (j >= 0)
    		{
    			int cout = (Integer.parseInt(b.substring(j, j + 1))  + cin) % 2;
        		cin = (Integer.parseInt(b.substring(j, j + 1)) + cin) / 2;
        		str.append(cout);
        		j--;
    		}
    		if (cin != 0)
    			str.append(cin);
    	}
        return str.reverse().toString();
    }
    
    /*
     * 581ms
     */
    public String addBinary2(String a, String b) {
    	if (a == null && b == null)
    		return a;
    	int cin = 0;
    	String str = new String();
    	int m = a.length();
    	int n = b.length();
    	int max = Math.max(m, n);
    	for (int i = 0; i < max; i++) {
    		int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
            int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
            int tmp = p + q + cin;
            cin = tmp / 2;
            str = tmp % 2 + str;
    	}
    	return (cin == 0) ? str : "1" + str;
    }
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{   
    		String a = cin.next();
    		String b = cin.next();
    		Solution67 plus = new Solution67();
    		System.out.println(plus.addBinary(a, b));
    	}
    	cin.close();
    }
}