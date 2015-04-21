package LeetCode;

import java.util.ArrayList;
import java.util.Scanner;

/*
* Program :
*         Integer to Roman 
*         Given an integer, convert it to a roman numeral.
*         Input is guaranteed to be within the range from 1 to 3999.
*   History:
*   @author wuyan  version 1
*   2015-4-21
*/
public class Solution12
{
	/*
	 * 1.相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
	 * 2.小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
	 * 3.小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
	 * 4.正常使用时，连续的数字重复不得超过三次
	 * 5.在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）
	 */
	public String intToRoman(int num) {
		String str = new String();
		if (num <= 0)
			return str;
		int nums[]      = {1000, 900,  500, 400,  100, 90,   50,  40,   10,   9,    5,   4,    1};
	    String[] romans = {"M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		StringBuilder sb = new StringBuilder();
		int i = 0;
        while (i < nums.length) {
            if (num >= nums[i]) {
                sb.append(romans[i]);
                num -= nums[i];
            } else {
                i++;
            }
        }
        str = sb.toString();
		return str;
    }
	
	/*
	 * 1， 5 10， 50， 100， 500， 1000
	 * I， V， X， L，     C，      D，        M
	 */
	public String intToRoman2(int num) {
		if(num <1 || num > 3999)
	        return "";
	    int digit = 1000;
	    ArrayList<Integer> digits = new ArrayList<Integer>();
	    while(digit > 0)
	    {
	        digits.add(num /digit);
	        num %= digit;
	        digit /= 10;
	    }
	    StringBuilder res = new StringBuilder();
	    res.append(convert(digits.get(0),'M',' ', ' '));
	    res.append(convert(digits.get(1),'C','D', 'M'));
	    res.append(convert(digits.get(2),'X','L', 'C'));
	    res.append(convert(digits.get(3),'I','V', 'X'));
	    return res.toString();
	}
	public String convert(int digit, char one, char five, char ten)
	{
	    StringBuilder res = new StringBuilder();
	    switch(digit)
	    {
	        case 9:
	            res.append(one);
	            res.append(ten);
	            break;
	        case 8:
	        case 7:
	        case 6:
	        case 5:
	            res.append(five);
	            for(int i = 5;i < digit;i++)
	                res.append(one);
	            break;
	        case 4:
	            res.append(one);
	            res.append(five);
	            break;   
	        case 3:
	        case 2:
	        case 1:
	            for(int i = 0;i < digit;i++)
	                res.append(one);
	            break;   
	        default:
	            break;
	    }
	    return res.toString();
	}
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			
			int test = cin.nextInt();
			Solution12 Roman = new Solution12();
			System.out.println(Roman.intToRoman2(test));
		}
		cin.close();
	}
}