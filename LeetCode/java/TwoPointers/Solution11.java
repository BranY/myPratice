package LeetCode;

import java.util.Scanner;

public class Solution11
{
	/*
	 * Time Limit Exceeded
	 */
	public int maxArea(int[] height)
	{
		if(height.length <= 1)
			return 0;
		int maxArea = 0;
        for(int i = 1; i < height.length; i++){
        	if(height[i] == 0)
        		continue;
        	for(int j = 0; j < i; j++) {
        		int area = Math.min(height[i], height[j]) * Math.abs(i - j);
                if(area > maxArea) {
                     maxArea = area;
                 }
             }
         }
         return maxArea;
	}
	
	/*
	 * 434ms
	 */
	public int maxArea2(int[] height)
	{
		 int maxArea = 0;
		 int i = 0; 
		 int j = height.length - 1;	        
		 if(j <= 0) 
			 return 0; 
		 while(i < j) { 
			 int area = Math.min(height[i], height[j]) * (j - i); 
			 if(height[i] < height[j]){  
				 i++;
			 }else {  
				 j--; 
			 }  
			 if(area > maxArea) 	
				 maxArea = area;
		 } 
		 return maxArea;
	}
	
	public int maxArea3(int[] height)
	{
		 int maxArea = 0;
		 int i = 0; 
		 int j = height.length - 1;
		 int left = height[0];
		 int right = height[j];
		 if(j <= 0) 
			 return 0; 
		 while(i < j) {
			 
			 int area = Math.min(left, right) * (j - i); 
			 if(area > maxArea) 	
				 maxArea = area;
			 if(left < right){ 
				 while (i < j && height[i] <= left) {  
	                    i ++;  
	              } 
				 if (i < j)
					 left = height[i];
			 }else {
				 while (i < j && height[j] <= right)
					 j--;
				 if (i < j)
					 right = height[j];
			 }  
		 } 
		 return maxArea;
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext())
		{
			int size = cin.nextInt();
			int[] array = new int[size];
			for (int i = 0; i < array.length; i++)
				array[i] = cin.nextInt();
			Solution11 max = new Solution11();
			System.out.println(max.maxArea3(array));
		}
		cin.close();
	}
}
