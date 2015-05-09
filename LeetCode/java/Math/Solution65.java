package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Valid Number
 *         Validate if a given string is numeric.
 *         Some examples:
 *         "0" => true
 *         " 0.1 " => true
 *         "abc" => false
 *         "1 a" => false
 *         "2e10" => true
 *         Note: It is intended for the problem statement to be ambiguous. 
 *         You should gather all requirements up front before implementing one.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-24
 */
public class Solution65
{
	/*
	 * digit -> [0-9]
	 * digits -> {digit}+
	 * number -> digits(.digits)?(E[+-}?digits)?
	 * .1-->true
	 * e9->no
	 */
    public boolean isNumber(String s) {
    	s = s.trim();
    	if (s.length() == 0)
    		return false;
    	int i = 0;
    	if (s.charAt(i) == '+' || s.charAt(i) == '-')
    		++i;
    	if (i == s.length())
    		return false;
    	if (i < s.length() && (!Character.isDigit(s.charAt(i))  && s.charAt(i) != '.'))
    		return false;
    	while (i < s.length() && Character.isDigit(s.charAt(i)))
    		i++;
    	if (i == s.length())
    		return true;
    	if (i < s.length() && s.charAt(i) == '.')
    	{
    		++i;
    		if (i == s.length())
    		{
        		if (i == 2 && ( s.charAt(0) == '-' || s.charAt(0) == '+'))
        			return false;
        		else if (i == 1)
        			return false;
        		else
        			return true;
    		}
    		while (i < s.length() && Character.isDigit(s.charAt(i)))
        		i++;
    		if (i == s.length())
    			return true;
    		else if ((s.charAt(i) == 'e' || s.charAt(i) =='E') && i < s.length())
    		{
    			if (s.charAt(0) == '.' && !Character.isDigit(s.charAt(1)))
    				return false;
    			++i;
    			if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
    				++i;
    			if (i == s.length())
    				return false;
    			else
    			{
    				while (i < s.length() && Character.isDigit(s.charAt(i)))
    	        		i++;
    				if (i != s.length())
    					return false;
    				else
    					return true;
    			}
    		}
    		else
    			return false;
    	}
    	else if (i < s.length() && (s.charAt(i) == 'e' || s.charAt(i) =='E'))
    	{
    		++i;
			if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
				++i;
			if (i == s.length())
				return false;
			else
			{
				while (i < s.length() && Character.isDigit(s.charAt(i)))
	        		i++;
				if (i != s.length())
					return false;
				else
					return true;
			}
    	}
    	else
    		return false;
    }
    
    public boolean isNumber2(String s) {
        if(s==null)
            return false;
        s = s.trim();
        if(s.length()==0)
            return false;
        boolean dotFlag = false;
        boolean eFlag = false;
        for(int i=0;i<s.length();i++)
        {
            switch(s.charAt(i))
            {
                case '.':
                    if(dotFlag || eFlag 
                    || ((i==0||!(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9')) 
                        && (i==s.length()-1||!(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'))))
                        return false;
                    dotFlag = true;
                    break;
                case '+':
                case '-':
                    if((i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))
                      || (i==s.length()-1 || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'||s.charAt(i+1)=='.')))
                        return false;
                    break;
                case 'e':
                case 'E':
                    if(eFlag || i==s.length()-1 || i==0)
                        return false;
                    eFlag = true;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	
    	while(cin.hasNext())
    	{
    		Solution65 valid  = new Solution65();
    		/*if (valid.isNumber("1    "))
    			System.out.println("Yes");
    		else
    			System.out.println("No");*/
    		String str = cin.next();
    		if (valid.isNumber(str))
    			System.out.println("Yes");
    		else
    			System.out.println("No");
    	}
    	cin.close();
    }
}