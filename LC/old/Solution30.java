package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/*
 * Program :
 *         Substring with Concatenation of All Words
 *         
 *         You are given a string, s, and a list of words, words, that are all of the same length.
 *         Find all starting indices of substring(s) in s that is a concatenation of each word in words 
 *         exactly once and without any intervening characters.
 *         For example, given:
 *         s: "barfoothefoobarman"  words: ["foo", "bar"]
 *         "lingmindraboofooowingdingbarrwingmonkeypoundcake", ["fooo","barr","wing","ding","wing"]
 *         You should return the indices: [0,9].
 *         (order does not matter).
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-9
 */
public class Solution30
{
	/*
	 * 思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。
	 * 假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。
	 * 做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串。
	 */
	public List<Integer> findSubstring(String s, String[] words) {
		ArrayList<Integer> res = new ArrayList<Integer>();
	    if(s == null || s.length()==0 || words == null || words.length == 0)
	        return res;
	    HashMap<String,Integer> map = new HashMap<String,Integer>();
	    for(int i = 0;i < words.length;i++)
	    {
	        if(map.containsKey(words[i]))
	        {
	            map.put(words[i], map.get(words[i]) + 1);
	        }
	        else
	        {
	            map.put(words[i], 1);
	        }
	    }
	    for(int i = 0;i < words[0].length();i++)
	    {
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();
	        int count = 0;
	        int left = i;
	        for(int j = i;j <= s.length() - words[0].length();j += words[0].length())
	        {
	            String str = s.substring(j, j + words[0].length());
	            
	            if(map.containsKey(str))
	            {
	                if(curMap.containsKey(str))
	                    curMap.put(str, curMap.get(str) + 1);
	                else
	                    curMap.put(str, 1);
	                if(curMap.get(str) <= map.get(str))
	                    count++;
	                else
	                {
	                    while(curMap.get(str) > map.get(str))
	                    {
	                        String temp = s.substring(left,left + words[0].length());
	                        if(curMap.containsKey(temp))
	                        {
	                            curMap.put(temp, curMap.get(temp) - 1);
	                            if(curMap.get(temp) < map.get(temp))
	                                count--;
	                        }
	                        left += words[0].length();
	                    }
	                }
	                if(count == words.length)
	                {
	                    res.add(left);
	                    String temp = s.substring(left, left + words[0].length());
	                    if(curMap.containsKey(temp))
	                        curMap.put(temp, curMap.get(temp) - 1);
	                    count--;
	                    left += words[0].length();
	                }
	            }
	            else
	            {
	                curMap.clear();
	                count = 0;
	                left = j + words[0].length();
	            }
	        }
	    }
	    return res;
    }
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			String s = cin.next();
			String[] words = cin.next().split(",");
			Solution30 SWC = new Solution30();
			List<Integer> list = SWC.findSubstring(s, words);
			for (Integer b: list)
				System.out.print(b + ",");
			System.out.println();
		}
		cin.close();
	}
}