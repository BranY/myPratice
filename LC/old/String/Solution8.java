package LeetCode;

/*
* Program :
*         String to Integer (atoi)
*         
*         Implement atoi to convert a string to an integer.
*         Hint: Carefully consider all possible input cases. If you want a challenge, 
*               please do not see below and ask yourself what are the possible input cases.
*         Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
*                You are responsible to gather all the input requirements up front.
*         
*         
*         Requirements for atoi:
*         The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
*         Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
*          and interprets them as a numerical value.
*          
*         The string can contain additional characters after those that form the integral number, 
*         which are ignored and have no effect on the behavior of this function.
*          
*         If the first sequence of non-whitespace characters in str is not a valid integral number,
*         or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
*           
*         If no valid conversion could be performed, a zero value is returned. 
*         If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*   History:
*   @author wuyan  version 1
*   2015-4-19
*/
import java.util.Scanner;

public class Solution8 {
	/*
	 * long  9223372036854775807(max) -9223372036854775808(min)
	 * input:9223372036854775809 ==> 2147483647
	 * input:18446744073709551617==> 2147483647
	 * input:-1123u3761867 ==> -1123
	 * run: 324ms
	 */
    public int myAtoi(String str) {
    	int i, sign;
    	long val = 0;
        i = 0;
        if (str.length() == 0)
        	return 0;
        while (str.charAt(i) == ' ')
        	i++;
        if (i == str.length())
        	return 0;
        StringBuilder arr = new StringBuilder();
        
        sign = (str.charAt(i) == '-')? -1 : 1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') i++;
        
        if (!Character.isDigit(str.charAt(i)))
        	return 0;
        
        while (i < str.length() && Character.isDigit(str.charAt(i)) ) {
        	val = val * 10 + (str.charAt(i) - '0');
        	arr.append(str.charAt(i));
            i++;
        }
        
        val = sign * val;
        if (arr.toString().length() > 11) {
        	if (sign == 1)
        		return 2147483647;
        	else
        		return -2147483648;
        }
        else
        {
        	if (val >= Integer.MAX_VALUE)
        		return 2147483647;
        	else if (val <= Integer.MIN_VALUE)
        		return -2147483648;
        	else
                return (int) val;
        }
    }
    
    /*
     * 235ms
     */
    public int myAtoi2(String str) {
        if(str==null)
        {
            return 0;
        }
        str = str.trim();
        if(str.length()==0)
            return 0;
        boolean isNeg = false;
        int i = 0;
        if(str.charAt(0) == '-' || str.charAt(0) == '+')
        {
            i++;
            if(str.charAt(0)=='-')
                isNeg = true;
        }
        int res = 0;
        while(i < str.length())
        {
            if(str.charAt(i) < '0'||str.charAt(i) > '9')
                break;
            int digit = (int)(str.charAt(i) - '0');
            if(isNeg && res > -((Integer.MIN_VALUE + digit) / 10))
                return Integer.MIN_VALUE;
            else if(!isNeg && res > (Integer.MAX_VALUE - digit) / 10)
                return Integer.MAX_VALUE;
            res = res * 10 + digit;
            i++;
        }
        return isNeg ? -res: res;
    }
    
	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String str = cin.next();
			Solution8 atoi = new Solution8();
			System.out.println(atoi.myAtoi2(str));
		}
		cin.close();
	}
}