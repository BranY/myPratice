package LeetCode;
/*
* Program :
*         Determine whether an integer is a palindrome. Do this without extra space.
*         
*         Some hints:
*         Could negative integers be palindromes? (ie, -1)
*         If you are thinking of converting the integer to string, note the restriction of using extra space.
*         You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
*          you know that the reversed integer might overflow. How would you handle such case?
*          There is a more generic way of solving this problem.
*   History:
*   @author wuyan  version 1
*   2015-4-11
*/
import java.util.Scanner;

public class Solution9 {
	/*
	 * 393
	 */
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int p = x;
		int y = 0;
		int n; 
		while( x != 0){ 
			n = x % 10; 
			if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10){  
				return false; 
			}
		     
			y = y * 10 + n;
			x /= 10;
		}
		if ((p - y) == 0)
			return true;
		else
			return false;
    }
	
	/*
	 * 537
	 */
	public boolean isPalindrome2(int x) {
		if (x < 0)
			return false;
		
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}
 
		while (x != 0) {
			int left = x / div;
			int right = x % 10;
 
			if (left != right)
				return false;
 
			x = (x % div) / 10;
			div /= 100;
		}
 
		return true;
	}
	
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int x = cin.nextInt();
			Solution9 rev = new Solution9();
			if (rev.isPalindrome(x))
				System.out.println("yes");
			else
				System.out.println("no");
		}
		cin.close();
	}
	
}