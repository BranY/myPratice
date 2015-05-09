package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Count and Say
 *         The count-and-say sequence is the sequence of integers beginning as follows:
 *         1, 11, 21, 1211, 111221, ...
 *         1 is read off as "one 1" or 11.
 *         11 is read off as "two 1s" or 21.
 *         21 is read off as "one 2, then one 1" or 1211.
 *         Given an integer n, generate the nth sequence.
 *         Note: The sequence of integers will be represented as a string.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-3
 */
public class Solution38
{
	/*
	 * Base case: n = 0 print "1" 
	 * for n = 1, look at previous string and write number of times a digit is seen and the digit itself.
	 *     In this case, digit 1 is seen 1 time in a row... so print "1 1" 
	 * for n = 2, digit 1 is seen two times in a row, so print "2 1" 
	 * for n = 3, digit 2 is seen 1 time and then digit 1 is seen 1 so print "1 2 1 1" 
	 * for n = 4 you will print "1 1 1 2 2 1" 
	 * 
	 * Consider the numbers as integers for simplicity. e.g. 
	 * if previous string is "10 1" then the next will be "1 10 1 1" and the next one will be "1 1 1 10 2 1"
	 */
    public String countAndSay(int n) {
    	if (n <= 0)
    		return "";
    	String prev = "1";
    	int i = 1;
    	while (i < n)
    	{
    		StringBuilder str = new StringBuilder();
    		int count = 1;
    		for (int j = 1; j < prev.length(); j++) {
    			if (prev.charAt(j) == prev.charAt(j - 1)) {
    				count++;
    			} else {
    				str.append(count);
    				str.append(prev.charAt(j - 1));
    				count = 1;
    			}
    		}
    		str.append(count);
    		str.append(prev.charAt(prev.length() - 1));
    		prev = str.toString();
    		i++;
    	}
        return prev;
    }
    
    public String countAndSay1(int n) {
        String s = "1";
        
        for(int i=1; i<n; i++) {
            int cnt = 1;
            StringBuilder tmp = new StringBuilder();
            for(int j=1; j<s.length(); j++) {
                if(s.charAt(j) == s.charAt(j-1)) {
                    cnt++;
                } else {
                    tmp.append(cnt).append(s.charAt(j-1));
                    cnt = 1;
                }
            }
            tmp.append(cnt).append(s.charAt(s.length()-1));
            s = tmp.toString();
        }
        
        return s;
    }
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{   
    		int n = cin.nextInt();
    		Solution38 count = new Solution38();
    		System.out.println(count.countAndSay(n) + " " + count.countAndSay1(n));
    	}
    	cin.close();
    }
}