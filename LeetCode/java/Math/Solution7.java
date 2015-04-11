package LeetCode;
/*
* Program :
*         Reverse digits of an integer.
*         
*         Example1: x = 123, return 321
*         Example2: x = -123, return -321
*         
*         Have you thought about this?
*         Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
*         If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
*         
*         Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
*         then the reverse of 1000000003 overflows. How should you handle such cases?
*         For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*   History:
*   @author wuyan  version 1
*   2015-4-11
*/
import java.util.Scanner;

public class Solution7{
	/*
	 * 308
	 */
	public int reverse(int x) {
		if (x == 0 || x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE)
			return 0;
		int sign = x > 0 ? 1 : -1;
		int abs = Math.abs(x);
		if (abs % 10 == 0){
			int res = abs;
			while(res % 10 == 0)
			{
				res = res / 10;
			}
			String str = Integer.toString(res);
			StringBuilder strb = new StringBuilder();
			for (int i = str.length() - 1; i >= 0; i--)
				strb.append(str.charAt(i));
			
			return sign * Integer.parseInt(strb.toString());
		}
		String str = Integer.toString(abs);
		StringBuilder strb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--)
			strb.append(str.charAt(i));
		Long result = sign * Long.parseLong(strb.toString());
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
			return 0;
		else
			return (sign) * Integer.parseInt(strb.toString());
    }
	/*
	 * 275
	 */
	public int reverse2(int x) {
		long ret = 0;
        while (x != 0) {
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int)ret;
	}
	/*
	 * 245
	 */
	public int reverse3(int x) {
		int y=0;
		int n; 
		while( x != 0){ 
			n = x % 10; 
			if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10){  
				return 0; 
			}
		     
			y = y * 10 + n;
			x /= 10;
		 }
		 return y;
	}
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int x = cin.nextInt();
			Solution7 rev = new Solution7();
			System.out.println(rev.reverse3(x));
		}
		cin.close();
	}
}