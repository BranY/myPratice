package LeetCode;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * Program :
 *         Permutation Sequence  
 *         The set [1,2,3,…,n] contains a total of n! unique permutations.
 *         By listing and labeling all of the permutations in order,
 *         We get the following sequence (ie, for n = 3):
 *         "123", "132","213","231","312","321"
 *         
 *         Given n and k, return the kth permutation sequence.
 *         Note: Given n will be between 1 and 9 inclusive.
 *   History:
 *   @author wuyan  version 1 
 *   2015-5-2
 */
public class Solution60
{ 
    public String getPermutation(int n, int k) {
        if(n <= 0)
            return "";
        k--;
        StringBuilder res = new StringBuilder();
        int factorial = 1;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i = 2;i < n; i++)
        {
            factorial *= i;
        }
        for(int i = 1;i <= n;i++)
        {
            nums.add(i);
        }
        int round = n - 1;
        while(round >= 0)
        {
            int index = k / factorial;
            k %= factorial;
            res.append(nums.get(index));
            nums.remove(index);
            if(round > 0)
                factorial /= round;
            round--;
        }
        return res.toString();
    }
    
    /*
     * 224ms
     */
    public String getPermutation1(int n, int k) {
		boolean[] output = new boolean[n];
		StringBuilder buf = new StringBuilder("");
 
		int[] res = new int[n];
		res[0] = 1;
 
		for (int i = 1; i < n; i++)
			res[i] = res[i - 1] * i;
 
		for (int i = n - 1; i >= 0; i--) {
			int s = 1;
 
			while (k > res[i]) {
				s++;
				k = k - res[i];
			}
 
			for (int j = 0; j < n; j++) {
				if (j + 1 <= s && output[j]) {
					s++;
				}
			}
 
			output[s - 1] = true;
			buf.append(Integer.toString(s));
		}
 
		return buf.toString();
	}
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{   
    		int n = cin.nextInt();
    		int k = cin.nextInt();
    		Solution60 perm = new Solution60();
    		System.out.println(perm.getPermutation1(n, k));
    	}
    	cin.close();
    }
}