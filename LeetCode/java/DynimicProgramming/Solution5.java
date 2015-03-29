package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Given a string S, find the longest palindromic substring in S. 
 *         You may assume that the maximum length of S is 1000, 
 *         and there exists one unique longest palindromic substring.
 *    
 *   History:
 *   @author wuyan  version 1 
 *   2015-3-29
 */

public class Solution5 {
	/*
	 * Dynamic Programming:
	 * Status Function:
	 * 用f(i,j)表示区间[i,j]上S是否回文
	 * 
	 * <pre>
	 *      f(i,j) = 
	 *              true                        if i==j
	 *              S[i] == S[j]                if i==j+1
	 *              S[i] == S[j] && f(i+1, j-1) if j > i+1
	 * </pre>
	 */
    public String longestPalindrome1(String s) {
    	char[] str = s.toCharArray();
    	final int size = s.length();
    	boolean[][] status = new boolean[size][size];
    	int max_len, start;
    	max_len = 1;
    	start = 0;
    	
    	for(int i = 0; i < size; i++){
    		status[i][i] = true;
    		for(int j = 0; j < i; j++)
    		{
    			status[j][i] = ((str[j] == str[i]) && 
    					(i - j < 2 || status[j + 1][i -1 ]));
    			if(status[j][i] && max_len < (i - j + 1)) {
    				max_len = i - j + 1;
    				start = j;
    			}
    		}
    	}
		return s.substring(start, (max_len + start));
    }
    /*
     * Improve Dynamic Programming
     */
    public String longestPalindrome1_1(String s) {
    	int n = s.length();
    	char[] str = s.toCharArray();
    	if (n == 0) return "";
    	String longest = s.substring(0, 1);  // a single char itself is a palindrome
    	for (int i = 0; i < n-1; i++) {
    		
    		int l = i, r = i;
    		while (l >= 0 && r <= n-1 && str[l] == str[r]) {
        		l--;
        	    r++;
        	}
    		String p1 = s.substring(l+1, r);
    	    if (p1.length() > longest.length())
    	      longest = p1;
    	    
    	    l = i;
    	    r = i + 1;
    	    while (l >= 0 && r <= n-1 && str[l] == str[r]) {
        		l--;
        	    r++;
        	}
    	    String p2 = s.substring(l+1, r);
    	    if (p2.length() > longest.length())
    	      longest = p2;
    	}
    	return longest;
    }
    
    public static void main(String[] args) {
    	Scanner cin = new Scanner(System.in);
    	Solution5 so = new Solution5();
    	while(cin.hasNext()) {
    		String s = cin.next();
    		System.out.println(so.longestPalindrome3(s));
    	}
    	cin.close();
    }
    
    /*
     * 以某个元素为中心，分别计算偶数长度的回文最大长度和奇数长度的回文最大长度。
     */
    public String longestPalindrome2(String s) {
    	char[] str = s.toCharArray();
    	final int size = s.length();
    	if (size <= 1)
    		return s;
    	int max_len, start;
    	max_len = 1;
    	start = 0;
    	
    	for(int i = 0; i < size; i++){
            //寻找以i-1,i为中点偶数长度的回文
            int low = i - 1, high = i;
            while(low >= 0 && high < size && str[low] == str[high])
            {
                low--;
                high++;
            }
            /*
             * high && low 间距由于自增运算符和自减运算符扩大了2个单位
             * (high - low + 1) - 2 = high -low -1 
             */
            if(high - low - 1 > max_len)
            {
                max_len = high - low -1;
                start = low + 1;
            }
             
            //寻找以i为中心的奇数长度的回文
            low = i - 1; high = i + 1;
            while(low >= 0 && high < size && str[low] == str[high])
            {
                low--;
                high++;
            }
            if(high - low - 1 > max_len)
            {
                max_len = high - low -1;
                start = low + 1;
            }
        }
		return s.substring(start, (max_len + start));
    }
    
    /*
     * 该算法首先对字符串进行预处理，在字符串的每个字符前后都加入一个特殊符号，比如字符串 abcd 处理成 #a#b#c#d#,
     * 为了避免处理越界，在字符串首尾加上不同的两个特殊字符(c类型的字符串尾部不用加，因为自带‘\0’)，
     * 这样预处理后最终变成$#a#b#c#d#^，经过这样处理后有个好处是原来的偶数长度和奇数长度的回文在处理后的字符串中都是奇数长度。
     * 假设处理后的字符串为s 
     * s：   $   #  1  #  2  #  2  #  1  #  2  #  3  #  2  #  1  #  ^ 
     * p：       1  2  1  2   5  2  1  4  1   2  1  6  1   2  1  2  1
     * 变为求p[i]
     */
    public String longestPalindrome3(String s) {
    	if (s.length() <= 1)
    		return s;
    	
    	char[] str = s.toCharArray();
    	final int size = s.length();
    	int mx = 0;
    	int id = 0;
    	char[] process = new char[size + size + 3];
    	
    	process[0] = '$';
    	int k = 1;
    	for (int i = 1, j =0; i < process.length&& j < size; i+=2, j++)
    	{
    		process[k++] = '#';
    		process[k++] = str[j];
    	}
    	process[k] = '#';
    	process[++k] = '^';
    	
    	int[] p = new int[process.length];
    	for(int i = 1; i < process.length - 1; i++)
        {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            
            while(process[i + p[i]] == process[i - p[i]])
            	p[i]++;
            if(i + p[i] > mx)
            {
                mx = i + p[i];
                id = i;
            }
        }
    	 int maxLen = 0, index = 0;
         for(int i = 1; i < process.length - 1; i++)
        	 if(p[i] > maxLen){
                 maxLen = p[i];
                 index = i;
             }
         
		return s.substring((index - maxLen) / 2, (index + maxLen - 1) / 2);
    }
    
}