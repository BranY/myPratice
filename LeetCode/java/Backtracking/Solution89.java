package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Program :
*         Gray Code
*         
*         The gray code is a binary numeral system where two successive values differ in only one bit.
*         
*         Given a non-negative integer n representing the total number of bits in the code, 
*         print the sequence of gray code. A gray code sequence must begin with 0.
*         
*         For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
*         00 - 0, 01 - 1, 11 - 3, 10 - 2
*         
*         Note:
*         For a given n, a gray code sequence is not uniquely defined.
*         For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
*         For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*   History:
*   @author wuyan  version 1
*   2015-4-21
*/
public class Solution89
{
	/*
	 * 可以看到第n位的格雷码由两部分构成，一部分是n-1位格雷码，再加上 1<<(n-1)和n-1位格雷码的逆序的和
	 * 247ms
	 * 1位格雷码有两个码字 
	 * (n+1)位格雷码中的前2n个码字等于n位格雷码的码字，按顺序书写，加前缀0 
	 * (n+1)位格雷码中的后2n个码字等于n位格雷码的码字，按逆序书写，加前缀1
	 */
	public List<Integer> grayCode(int n) {
		if(n == 0) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }
        
        List<Integer> tmp = grayCode(n - 1);
        int addNumber = 1 << (n - 1);
        ArrayList<Integer> result = new ArrayList<Integer>(tmp);
        for(int i = tmp.size() - 1;i >= 0; i--) {
            result.add(addNumber + tmp.get(i));
        }
        return result;
    }
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int num = cin.nextInt();
			Solution89 gray = new Solution89();
			List<Integer> list = gray.grayCode(num);
			for (Integer a: list)
				System.out.print(a + " ");
			System.out.println();
		}
		cin.close();
	}
}