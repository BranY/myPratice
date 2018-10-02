package LeetCode;
/*
 * Program :
 *         The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows
 *         like this: (you may want to display this pattern in a fixed font for better legibility)
 *         
 *         P   A   H   N
 *         A P L S I I G
 *         Y   I   R
 *         
 *         And then read line by line: "PAHNAPLSIIGYIR"
 *         Write the code that will take a string and make this conversion given a number of rows:
 *         string convert(string text, int nRows);
 *         convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 *   History:
 *   @author wuyan  version 1
 *   2015-4-11
 */
import java.util.Scanner;

public class Solution6{
	/*
	 * step:
	 *      0:  2 * ( n - 1), 2 * 0
	 *      1:  2 * ( n - 2), 2 * 1
	 *      2:  2 * ( n - 3), 2 * 2
	 *      ...
	 *    n-1:  2 * 0       , 2 * (n - 1)
	 *    ##############################
	 * for 0 row:
	 * step: 2 * (nRows - 1)
	 * for nRows -1 row:
	 * step: 2 * (nRows -1)
	 * for i = 1 to nRows - 2
	 *      left = 2 * (nRows - i - 1);
	        right  = 2 * i;
	 */
	 public String convert(String s, int nRows) {
		 StringBuilder str = new StringBuilder();
		 if (nRows >= s.length() || nRows == 1 || s.length() == 0)
		 {
			 return s;
		 }
		 else {
			 int right , left;  
	    	 for (int i = 0; i < nRows; i++)
	    	 {
	    		 left = 2 * (nRows - i - 1);
	    		 right  = 2 * i;
	    		 if (i == 0) {
	    			 int t = i;
	    			 while (t < s.length()) {
	    				 str.append(s.charAt(t));
	    				 t += left;
	    			 }
	    		 } else if(i == nRows -1) {
	    			 int t = i;
	    			 while (t < s.length()) {
	    				 str.append(s.charAt(t));
	    				 t += right;
	    			 }
	    		 }
	    		 else {
	    			 int t = i;
	    			 int times = 0;
	    			 while(t < s.length()) {
	    				 str.append(s.charAt(t));
	    				 t += (times % 2 == 0) ? left : right;
	    				 times++;
	    			 }
	    		 }
	    	 } 
		 }
	     return str.toString();
	 }
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		Solution6 Z = new Solution6();
		System.out.println(Z.convert("", 1));
		System.out.println("Done!");
		while (cin.hasNext())
		{
			int nRows = cin.nextInt();
			String str = cin.next();
			Solution6 ZigZag = new Solution6();
			System.out.println(ZigZag.convert(str, nRows));
			
		}
		cin.close();
	}
}