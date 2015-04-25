package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
* Program :
*         Generate Parentheses
*         Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
*         For example, given n = 3, a solution set is:
*         
*         "((()))", "(()())", "(())()", "()(())", "()()()"
*   History:
*   @author wuyan  version 1
*   2015-4-21
*/
public class Solution22
{
	/*
	 * 在每次递归函数中记录左括号和右括号的剩余数量，然后有两种选择，一个是放一个左括号，另一种是放一个右括号。
	 * 当然有一些否定条件，比如剩余的右括号不能比左括号少，或者左括号右括号数量都要大于0。
	 * 正常结束条件是左右括号数量都为0。算法的复杂度是O(结果的数量)，因为卡特兰数并不是一个多项式量级的数字，所以算法也不是多项式复杂度的。
	 * 310
	 */
	 public List<String> generateParenthesis(int n) {
		 List<String> list = new ArrayList<String>();
		 if (n <= 0)
			 return list;
		 help (n, n, new String(), list);
		return list;
	 }
	private void help(int l, int r, String string, List<String> list) {
		if (l > r)
			return;
		if (l == 0 && r == 0)
			list.add(string);
		if ( l > 0)
			help(l - 1, r, string + "(" , list);
		if ( r > 0)
			help(l, r - 1, string + ")", list);
	}
	
	
	/*
	 * 272
	 */
	 public List<String> generateParenthesis2(int n) {
		 List<String> list = new ArrayList<String>();
		 if (n == 0) return list;
		 generate(list, "", 0, 0, n);
		 return list;
	 }
	private void generate(List<String> list, String string, int l, int r, int n) {
		if (l == n){
			for (int i = 0; i < n - r;i++){
				string += ")";
			}   
			list.add(string); 
			return;
		}  
		generate(list, string + "(", l + 1, r, n);  
		if (r < l)	
			generate(list, string + ")", l, r + 1, n);
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			Solution22 gp = new Solution22();
			int n = cin.nextInt();
			List<String> list = gp.generateParenthesis2(n);
			for (String str : list)
				System.out.print(str + ", ");
			System.out.println();
		}
		cin.close();
	}
}