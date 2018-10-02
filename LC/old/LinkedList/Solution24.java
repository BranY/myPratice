package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Swap Nodes in Pairs 
 *         Given a linked list, swap every two adjacent nodes and return its head.
 *         For example,
 *         Given 1->2->3->4, you should return the list as 2->1->4->3.
 *         Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-25
 */


public class Solution24
{
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	/*
	 * 263ms
	 */
	 public ListNode swapPairs(ListNode head) {
		 if (head == null || head.next == null) {
			 return head;	        
		 }
		 ListNode fakeHead = new ListNode(0);
		 fakeHead.next = head;
		 ListNode pre = fakeHead;
		 while (pre.next != null && pre.next.next != null)
		 {
			 ListNode next = pre.next.next.next;
			 ListNode tmp = pre.next;
			 pre.next = pre.next.next;
			 pre.next.next = tmp;
			 tmp.next = next;
			 pre = tmp;
		 }
		 
		 return fakeHead.next;	    
	 }
	/*
	 * 267ms
	 */
	public ListNode swapPairs2(ListNode head) {
		 return rec(head);
	}
	private ListNode rec(ListNode head) {
		if (head == null || head.next == null) {
            return head;
        }
		ListNode next = rec(head.next.next);
		ListNode headNew = head.next;
		headNew.next = head;
        head.next = next;
        
        return headNew;
	}
	
	public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	while(in.hasNext()) {
    		ListNode l1 = null;
    		ListNode pre = l1;
    		String s1 = in.next();
    		for(int i = 0; i < s1.length(); i++) {
    			int num = Integer.parseInt(s1.substring(i, i + 1));
    			ListNode pa = new ListNode(num);
    			if(l1 == null)
    				l1 = pa;
    			else
    				pre.next = pa;
    			pre = pa;
    		}
    		Solution24 result = new Solution24();
    		ListNode L = result.swapPairs(l1);
    		while(L != null) {
    			System.out.print(L.val);
    			if(L.next != null)
    				System.out.print("->");
    			L = L.next;
    		}
    	}
    	in.close();
	}
}