package pkg.ywj.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * link: https://leetcode-cn.com/problems/two-sum/
 * tag: array、hash table
 */
public class Problem1 {
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

    /*
     * version 2
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> ht = new HashMap<>();

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
            Problem1 s = new Problem1();
            int[] result = s.twoSum2(numbers, target);
            System.out.println("index1: " + result[0] + ", index2: " + result[1]);
        }
        cin.close();
    }
}
