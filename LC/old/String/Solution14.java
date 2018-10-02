package LeetCode;

/*
* Program :
*         Longest Common Prefix
*         
*         Write a function to find the longest common prefix string amongst an array of strings.
*   History:
*   @author wuyan  version 1
*   2015-4-21
*/
public class Solution14
{
	/*
	 * 以第一个字符串为标准，对于每个字符串从第一个字符开始，看看是不是和标准一致，如果不同，则跳出循环返回当前结果，否则继续下一个字符。
	 * 268ms
	 */
	public String longestCommonPrefix(String[] strs)
	{
		if (strs == null || strs.length == 0) {
            return "";
        }
		 String s = strs[0];
	     int len = s.length();
	     
	     for (int i = 0; i < len; i++) {
	    	 char c = s.charAt(i);
	    	 for (int j = 1; j < strs.length; j++) {
	                
	    		 if (strs[j].length() <= i || c != strs[j].charAt(i)) {
	    			 
	    			 return s.substring(0, i);  
	    		 }
	    	 }
	     }
	     return s;
	}
	
	/*
	 * 317
	 */
	public String longestCommonPrefix2(String[] strs)
	{
		if (strs == null || strs.length == 0) {
            return "";
        }
		
        String s0 = strs[0];
        int len = s0.length();
        
        for(int i = 1; i < strs.length; i++)
        {
            int k;
            for(k = 0; k < Math.min(len, strs[i].length()); k++)
                if(strs[0].charAt(k) != strs[i].charAt(k))
                	break;
            if(len > k)
            	len = k;
        }
        return s0.substring(0, len);
	}
	public static void main(String[] args){
		Solution14 LCP = new Solution14();
		String[] str = {"abca","abc"};
		System.out.println(LCP.longestCommonPrefix2(str));
	}
}