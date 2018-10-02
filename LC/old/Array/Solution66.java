package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Plus One 
 *         Given a non-negative number represented as an array of digits, plus one to the number.
 *         The digits are stored such that the most significant digit is at the head of the list.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution66
{
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length==0)
            return digits;
        int cin = 1;
        for(int i = digits.length - 1;i >= 0;i--)
        {
            int digit = (digits[i] + cin) % 10; 
            cin = (digits[i] + cin) / 10;
            digits[i] = digit;
            if(cin == 0)
                return digits;    
        }
        int [] res = new int[digits.length+1];    
        res[0] = 1;
        return res;
    }
    
    public int[] plusOne2(int[] digits) {
        if(digits == null || digits.length==0)
            return digits;
        int size = digits.length;
        for(int i = size - 1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                break;
            }else{
                digits[i] = 0;
            }
        }
        int[] newdigits;
        if(digits[0] == 0){
            newdigits = new int[digits.length + 1];
            newdigits[0] = 1;
            for(int i = 1;i < newdigits.length; i++){
                newdigits[i] = digits[i - 1];
            }
        }else{
            newdigits = new int[digits.length];
            for(int i = 0;i < digits.length;i++){
                newdigits[i] = digits[i];
            }
        }
        return newdigits;
    }
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] source = cin.next().split(",");
    		int[] digits = new int[source.length];
    		for (int i = 0; i < source.length; i++)
    			digits[i] = Integer.parseInt(source[i]);
    		
    		Solution66 plus = new Solution66();
    		int[] result = plus.plusOne(digits);
    		for (int b: result)
    			System.out.print(b + ", ");
    		System.out.println();
    	}
    	cin.close();
    }
}