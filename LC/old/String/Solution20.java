package LeetCode;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
/*
 * Program :
 *         Valid Parentheses 
 *         Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 *         determine if the input string is valid.
 *         
 *         The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-24
 */
public class Solution20
{
	/*
	 * 235ms
	 */
	  public boolean isValid(String s) {
		  if (s.length() == 0)
			  return true;
		  char[] ch = new char[s.length()];
		  int top = 0 , i  = 0;
		  while (i < s.length())
		  {
			  if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
			  {
				  ch[top] = s.charAt(i);
				  top++;
			  }
			  else
			  {
				  if (top == 0)
					  return false;
				  --top;
				  if ((ch[top] == '(' && s.charAt(i) != ')') 
						  ||(ch[top] == '[' && s.charAt(i) != ']')
						  || (ch[top] == '{' && s.charAt(i) != '}'))
					  return false;
			  }
			  i++;
		  }
		  if (top == 0)
			  return true;
		  else
			  return false;
	  }
	  /*
	   * 264ms
	   */
	  public boolean isValid2(String s) {
		  HashMap<Character, Character> map = new HashMap<Character, Character>();
		  map.put('(', ')');
		  map.put('[', ']');
		  map.put('{', '}');
		  
		  Stack<Character> stack = new Stack<Character>();
		  for (int i = 0; i < s.length(); i++) {
			  char curr = s.charAt(i);
			  
			  if (map.keySet().contains(curr)) {
				  stack.push(curr);
				
			  } else if (map.values().contains(curr)) {
				  if (!stack.empty() && map.get(stack.peek()) == curr) {					
					  stack.pop();				
				  } else {						
					  return false;					
				  }				
			  }
		   }
		  return stack.empty();
	  }
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String str = cin.next();
			Solution20 vp = new Solution20();
			if (vp.isValid2(str))
				System.out.println("Yes");
			else
				System.out.println("No");
		}
		cin.close();
	}
}