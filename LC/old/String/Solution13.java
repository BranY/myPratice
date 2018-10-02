package LeetCode;

import java.util.Scanner;

/*
* Program :
*         Roman to Integer 
*         Given a roman numeral, convert it to an integer.
*         
*         Input is guaranteed to be within the range from 1 to 3999.
*   History:
*   @author wuyan  version 1
*   2015-4-21
*/
public class Solution13
{
	/*
	 * 1.相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
	 * 2.小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
	 * 3.小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
	 * 4.正常使用时，连续的数字重复不得超过三次
	 * 5.在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）
	 */
	public int romanToInt(String s) {
		if (s.length() == 0) 
			return 0;
		int result = romanCharToInt(s.charAt(0));
		for (int i = 1; i < s.length(); i++){
			int prev = romanCharToInt(s.charAt(i - 1));
		    int curr = romanCharToInt(s.charAt(i));
		    if (prev < curr) {
		    	//result = result - prev + (curr - prev);
		    	result = result - 2 * prev + curr;
		    }else{
		    	result += curr;
		    }
		}
		return result;
    }
	int romanCharToInt(char ch){
	    int d = 0;
	    switch(ch){
	        case 'I':  
	            d = 1;  
	            break;  
	        case 'V':  
	            d = 5;  
	            break;  
	        case 'X':  
	            d = 10;  
	            break;  
	        case 'L':  
	            d = 50;  
	            break;  
	        case 'C':  
	            d = 100;  
	            break;  
	        case 'D':  
	            d = 500;  
	            break;  
	        case 'M':  
	            d = 1000;  
	            break;  
	    }
	    return d;
	}
	
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			
			String str = cin.next();
			Solution13 Roman = new Solution13();
			System.out.println(Roman.romanToInt(str));
		}
		cin.close();
	}
}