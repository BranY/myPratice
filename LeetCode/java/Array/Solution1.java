package LeetCode;

/*
 * Program :
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) 
 * are not zero-based.
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 *   History:
 *   @author wuyan  version 1 
 *   2015-3-25
 */
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution1 {
	/*
	 * better than 1
	 */
    public int[] twoSum1(int[] numbers, int target) {
    	int[] result = new int[2];
    	Map<Integer, Integer> ht = new HashMap<Integer, Integer>();
    	
    	for(int i = 0; i < numbers.length; i++) {
    		Integer temp = ht.get(numbers[i]);
    		if (temp == null)
    			ht.put(numbers[i], i);
    		temp = ht.get(target - numbers[i]);
    		if (temp != null && temp < i)
    		{
    			result[0] = temp + 1;
    			result[1] = i + 1;
    			return result;
    		}
    	}
		return result; 
    }
    
    public static void main(String[] args) {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext()) {
    		int target = cin.nextInt();
    		String[] num = cin.next().split(",");
    		
    		int[] numbers = new int[num.length];
    		for (int i = 0; i < num.length; i++) {
    			Integer n = Integer.parseInt(num[i]);
    			numbers[i] = n.intValue();
    		}
    		Solution1 s = new Solution1();
    		int[] result = s.twoSum3(numbers, target);
    		System.out.println("index1: " + result[0] + ", index2: " + result[1]);
    	}
    	cin.close();
    }
    
    /*
     * version 2
     */
    public int[] twoSum2(int[] numbers, int target) {
    	int[] result = new int[2];
    	Map<Integer, Integer> ht = new HashMap<Integer, Integer>();
    	
    	for(int i = 0; i < numbers.length; i++) {
    		if (ht.get(target - numbers[i]) != null){
    			result[0] = ht.get(target - numbers[i]) + 1;
    			result[1] = i + 1;
    		}
    		else
    			ht.put(numbers[i], i);
    	}
		return result; 
    }
    
    /*
     * version 3
     * Wrong answer
     */
    public int[] twoSum3(int[] numbers, int target) {
    	
    	class pair{
    		int key;
    		int value;
    		
    	}
    	int[] result = new int[2];
    	pair[] index = new pair[numbers.length];
    	for (int i = 0; i < numbers.length; i++) {
    	}
    	
    	Map<Integer, Integer> ht = new HashMap<Integer, Integer>();
    	
    	for(int i = 0; i < numbers.length; i++) {
    		Integer temp = ht.get(numbers[i]);
    		if (temp == null)
    			ht.put(numbers[i], i);
    		temp = ht.get(target - numbers[i]);
    		if (temp != null && temp < i)
    		{
    			result[0] = temp + 1;
    			result[1] = i + 1;
    			return result;
    		}
    	}
		return result; 
    }
}