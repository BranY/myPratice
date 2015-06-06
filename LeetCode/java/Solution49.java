package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * Program :
 *         Anagrams
 *         Given an array of strings, return all groups of strings that are anagrams.
 *         Note: All inputs will be in lower-case.
 *         Input:　　["tea","and","ate","eat","den"]
 *         Output:   ["tea","ate","eat"]
 *   History:
 *   @author wuyan  version 1 
 *   2015-6-2
 */
public class Solution49
{
    public List<String> anagrams(String[] strs) {
    	List<String> list = new ArrayList<String>();
    	if (strs == null || strs.length == 0)
    		return list;
    	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	for(int i=0;i<strs.length;i++)
        {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String str = new String(charArr);
            if(map.containsKey(str))
            {
                map.get(str).add(strs[i]);
            }
            else
            {
                ArrayList<String> res = new ArrayList<String>();
                res.add(strs[i]);
                map.put(str,res);
            }
        }
        @SuppressWarnings("rawtypes")
		Iterator iter = map.values().iterator();
        while(iter.hasNext())
        {
            @SuppressWarnings("unchecked")
			ArrayList<String> item = (ArrayList<String>)iter.next();
            if(item.size() > 1)
                list.addAll(item);
        }
        return list;
    }
    
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		String[] strs = cin.next().split(",");
    		Solution49 ana = new Solution49();
    		List<String> res = ana.anagrams(strs);
    		for(String s: res)
    			System.out.println(s);
    	}
    	cin.close();
    }
}