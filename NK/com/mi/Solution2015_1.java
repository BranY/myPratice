package com.mi;

import java.util.Scanner;
/*
 * Program :
 * 世界上有10种人，一种懂二进制，一种不懂。那么你知道两个int32整数m和n的二进制表达，有多少个位(bit)不同么？ 
 * 输入例子:
 * 1999 2299
 * 输出例子:
 * 7
 *   History:
 *   @author wuyan
 *   2015-5-21
 */
public class Solution2015_1 {
    /**
     * 获得两个整形二进制表达位数不同的数量
     * 
     * @param m 整数m
     * @param n 整数n
     * @return 整型
     * ^ 相同为0，不同为1
     */
    public int countBitDiff(int m, int n) {
    	int temp = m ^ n;
    	int count = 0;
    	for (int i = 0; i < 32; i++)
    	{
    		if (((temp >> i) & 1) == 1)
    			count++;
    		
    	}
    	return count;
    }
    
    public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int m = cin.nextInt();
    		int n = cin.nextInt();
    		Solution2015_1 diffBit = new  Solution2015_1();
    		System.out.println(diffBit.countBitDiff(m, n));
    	}
    	cin.close();
    }
}