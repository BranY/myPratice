package LeetCode;
import java.util.Arrays;
/*
* Program : Median of Two Sorted Arrays 
*          There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. 
*          The overall run time complexity should be O(log (m+n)).     
*   History:
*   @author wuyan  version 1
*   2015-4-11
*/
import java.util.Scanner;

public class Solution4 {
	/*
	 * brute force
	 * //double median = (double) (((n + m ) % 2 == 0) ? C[(n + m) >> 1]:(C[(n + m - 1) >> 1] + C[(n + m) >> 1]) / 2.0); wrong
	   double median = (double) (((n + m ) % 2 == 0) ? (C[(m + n) / 2] + C[((m + n) / 2) - 1]) / 2.0:(C[(m + n) / 2]));
	   //double median = C[(n + m) >> 1]; wrong
	    * 423
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int[] C = new int[m + n];
		System.arraycopy(A, 0, C, 0, m);
		System.arraycopy(B, 0, C, m, n);
		Arrays.sort(C);
		
		
		double median = (double) (((n + m ) % 2 == 0) ? (C[(m + n) / 2] + C[((m + n) / 2) - 1]) / 2.0:(C[(m + n) / 2]));
		return median;
	}
	
	/*
	 * (Because k can be odd or even number, so we assume k is even number here for simplicity. 
	 * The following is also true when k is an odd number.)
	 * 
	 * A[k/2-1] = B[k/2-1]
	 * A[k/2-1] > B[k/2-1]
	 * A[k/2-1] < B[k/2-1]
	 * 
	 * if A[k/2-1] < B[k/2-1], that means all the elements from A[0] to A[k/2-1](i.e. the k/2 smallest elements in A)
	 *  are in the range of k smallest elements in the union of A and B. Or, in the other word,
	 *   A[k/2 - 1] can never be larger than the k-th smallest element in the union of A and B.

	 * Boundary Condition:
	 * 1. When A or B is empty, we return B[k-1]( or A[k-1]), respectively;
	 * 2. When k is 1(when A and B are both not empty), we return the smaller one of A[0] and B[0]
	 * 3. When A[k/2-1] = B[k/2-1], we should return one of them
	 * 
	 * 393
	 */
	
	public double findMedianSortedArrays2(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int total = m + n;
		if (total % 2 != 0)
			return findKth(A, m, B, n, total / 2 + 1);
		else
			return (findKth(A, m, B, n, total / 2)
					+ findKth(A, m, B, n, total / 2 + 1)) / 2;
	}
	
	double findKth(int a[], int m, int b[], int n, int k)
	{
		//always assume that m is equal or smaller than n
		if (m > n)
			return findKth(b, n, a, m, k);
		if (m == 0)
			return b[k - 1];
		if (k == 1)
			return a[0] >=b[0] ? b[0] : a[0];
		//divide k into two parts
		int pa = (k/2 >= m) ? m : k/2;
		int pb = k - pa;
		if (a[pa - 1] < b[pb - 1]){
			int[] A = new int[m - pa];
			System.arraycopy(a, pa, A, 0, m - pa);
			return findKth(A, A.length, b, n, k - pa);
		}
		else if (a[pa - 1] > b[pb - 1]){
			int[] B = new int[n - pb];
			System.arraycopy(b, pb, B, 0, n - pb);
			return findKth(a, m, B, B.length, k - pb);
		}
		else
			return a[pa - 1];
	}

	public static void main(String[] args){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int m = cin.nextInt();
			int[] A = new int[m];
			for (int i = 0; i < m; i++)
				A[i] = cin.nextInt();
			Arrays.sort(A);
			int n = cin.nextInt();
			int[] B = new int[n];
			for (int i = 0; i < n; i++)
				B[i] = cin.nextInt();
			Arrays.sort(B);
			
			Solution4 median = new Solution4();
			System.out.println(median.findMedianSortedArrays2(A, B));
		}
		cin.close();
	}
	
	/*
	 * 
	 * Wrong:
	 * [1,2], [1,2]
	 * Output:	1.00000
	 * Expected:	1.50000
	 */
	public  double findMedianSortedArrays3(int A[], int B[]) {
        if (A == null || B == null) {
            return 0;
        }
        
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (double)(dfs(A, B, 0, 0, len / 2) + dfs(A, B, 0, 0, len / 2 + 1)) / 2.0;
        } else {
            return dfs(A, B, 0, 0, len / 2 + 1);
        }
    }
    
    public static double dfs(int A[], int B[], int aStart, int bStart, int k) {
        if (aStart >= A.length) {
            return B[bStart + k - 1];
        } else if (bStart >= B.length) {
            return A[aStart + k - 1];
        }
        
        if (k == 1) {
            return Math.min(A[aStart], B[bStart]);
        }
        
        // k = 4;
        // mid = 1;
        int mid = k / 2 - 1;
        
        if (aStart + mid >= A.length) {
            // drop the left side of B.
            return dfs(A, B, aStart, bStart + k / 2, k - k / 2);
        } else if (bStart + mid >= B.length) {
            // drop the left side of A.
            return dfs(A, B, aStart + k / 2, bStart, k - k / 2);
        } else if (A[aStart + mid] > B[bStart + mid]) {
            // drop the left side of B.
            return dfs(A, B, aStart, bStart + k / 2, k - k / 2);
        } else if (A[aStart + mid] < B[bStart + mid]) {
            // drop the left side of A.
            return dfs(A, B, aStart + k / 2, bStart, k - k / 2);
        }
        
        return A[aStart + mid];        
    }
}