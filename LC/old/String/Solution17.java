package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*         Letter Combinations of a Phone Number 
*         
*         Given a digit string, return all possible letter combinations that the number could represent.
*         A mapping of digit to letters (just like on the telephone buttons) is given below.
*         
*         Input:Digit string "23"
*         Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*         Note:Although the above answer is in lexicographical order, your answer could be in any order you want.
*   History:
*   @author wuyan  version 1
*   2015-4-19
*/
public class Solution17
{
	/*
	 * 250ms
	 */
	public ArrayList<String> letterCombinations(String digits) {
	    ArrayList<String> res = new ArrayList<String>();
	    if(digits == null || digits.length() == 0)
	        return res;
	    res.add("");
	    for(int i = 0; i < digits.length();i++)
	    {
	        String letters = getLetters(digits.charAt(i));
	        ArrayList<String> newRes = new ArrayList<String>();
	        for(int j = 0;j < res.size();j++)
	        {
	            for(int k = 0;k < letters.length();k++)
	            {
	            	newRes.add(res.get(j) + Character.toString(letters.charAt(k)));
	            }
	        }
	        res = newRes;
	    }
	    return res;
	}
	private String getLetters(char digit)
	{
	    switch(digit)
	    {
	        case '2':
	            return "abc";
	        case '3':
	            return "def";
	        case '4':
	            return "ghi";
	        case '5':
	            return "jkl";
	        case '6':
	            return "mno";
	        case '7':
	            return "pqrs";
	        case '8':
	            return "tuv";
	        case '9':
	            return "wxyz";
	        case '0':
	            return "!";
	        default:
	            return "";
	    }
	}
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			
			String str = cin.next();
			Solution17 LCP = new Solution17();
			List<String> list = LCP.letterCombinations(str);
			for(String s: list)
				System.out.print(s + ",");
			System.out.println();
			
			
		}
		cin.close();
	}
}