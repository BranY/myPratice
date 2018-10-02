package LeetCode;
/*
 * Program :
 *    Given a string, find the length of the longest substring without repeating characters. 
 *    For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
 *    which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 *    
 *   History:
 *   @author wuyan  version 1 
 *   2015-3-25
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution3 {
	public static int lengthOfLongestSubstring(String s) {
		int i, j, max_len, d;
		max_len = d = 0;
		Map<String, Integer> index = new HashMap<String, Integer>();
		 i = 0;
		 for (j = 0; j < s.length(); j++) {
			 String temp = s.substring(j, j+1);
			 if(index.containsKey(temp)) {
				 if (index.get(temp) >= i) {
					 if (index.get(temp) + 1 <= j)
						 i = index.get(temp) + 1;
					 else 
						 i += 1;
				 }
			 }		 
			 index.put(temp, j);
			 
			 d = j - i + 1;
			 if (max_len < d)
				 max_len = d;
		 }
		return max_len;
	}
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			String str = cin.next();
			System.out.println(lengthOfLongestSubstring(str));
		}
		cin.close();
	}
	public static int lengthOfLongestSubstring2(String s) {
		int i, j, max_len, d;
		max_len = d = 0;
		Map<String, Integer> index = new HashMap<String, Integer>();
		 i = 0;
		 for (j = 0; j < s.length(); j++) {
			 String temp = s.substring(j, j+1);
			 if(index.containsKey(temp)) {
				 if (index.get(temp) >= i) {
					 if (index.get(temp) + 1 <= j)
						 i = index.get(temp) + 1;
					 else 
						 i += 1;
				 }
			 }		 
			 index.put(temp, j);
			 
			 d = j - i + 1;
			 if (max_len < d)
				 max_len = d;
		 }
		return max_len;
	}
}