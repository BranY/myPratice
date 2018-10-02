package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *    You are given two linked lists representing two non-negative numbers. 
 *    The digits are stored in reverse order and each of their nodes contain a single digit. 
 *    Add the two numbers and return it as a linked list.
 *    
 *    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *    Output: 7 -> 0 -> 8
 *    
 *   History:
 *   @author wuyan  version 1 
 *   2015-3-25
 */
public class Solution2 {
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	
    	int cin = 0;
        int digit = 0;
        ListNode head = null;
        ListNode pre = null;
        while(l1 != null && l2 != null)
        {
            digit = (l1.val + l2.val + cin) % 10;
            cin  = (l1.val + l2.val + cin)/10;
            ListNode newNode = new ListNode(digit);
            if(head == null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1!=null)
        {
            digit = (l1.val + cin) % 10;
            cin = (l1.val + cin) / 10;
            ListNode newNode = new ListNode(digit);
            if(head == null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l1 = l1.next;            
        }
        while(l2!=null)
        {
            digit = (l2.val + cin) % 10;
            cin = (l2.val + cin) / 10;
            ListNode newNode = new ListNode(digit);
            if(head==null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l2 = l2.next;            
        }        
        if(cin > 0)
        {
            ListNode newNode = new ListNode(1);
            pre.next = newNode;
        }
        return head;
    }
    
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	while(in.hasNext()) {
    		ListNode l1 = null;
        	ListNode l2 = null;
    		ListNode pre1 = l1;
    		ListNode pre2 = l2;
    		String s1 = in.next();
    		String s2 = in.next();
    		for(int i = 0; i < s1.length(); i++) {
    			int num = Integer.parseInt(s1.substring(i, i + 1));
    			ListNode pa = new ListNode(num);
    			if(l1 == null)
    				l1 = pa;
    			else
    				pre1.next = pa;
    			pre1 = pa;
    		}
    		for(int i = 0; i < s2.length(); i++) {
    			int num = Integer.parseInt(s2.substring(i, i + 1));
    			ListNode pb = new ListNode(num);
    			if(l2 == null)
    				l2 = pb;
    			else
    				pre2.next = pb;
    			pre2 = pb;
    		}
    		Solution2 s = new Solution2();
    		ListNode L = s.addTwoNumbers(l1,l2);
    		while(L!=null) {
    			System.out.print(L.val);
    			if(L.next != null)
    				System.out.print("->");
    			L = L.next;
    		}
    	}
    	in.close();
    }
}
