package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Remove Nth Node From End of List
 *         
 *         Given a linked list, remove the nth node from the end of list and return its head.
 *         
 *         For example,
 *         Given linked list: 1->2->3->4->5, and n = 2.
 *         After removing the second node from the end, the linked list becomes 1->2->3->5.
 *         Note:
 *         Given n will always be valid.
 *         Try to do this in one pass.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-24
 */
public class Solution19
{
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	
	  public ListNode removeNthFromEnd(ListNode head, int n) {
		  int i = 0, j = 0;  
		  ListNode cur = head;
		  ListNode tmp = head;
		  ListNode prev = null;
		  while (cur != null)
		  {
			  ++j;
			  if ((j - i) == n && cur.next != null)
			  {
				  ++i;
				  prev = tmp;
				  tmp = tmp.next;
			  }
			  else if ((j - i) == n && cur.next == null)
			  {
				  if (i == 0)
					  head = head.next;
				  else
					  prev.next = tmp.next;
				  return head;
			  }
			  cur = cur.next;
		  }
		return head;
	  }
	public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	while(in.hasNext()) {
    		ListNode l1 = null;
    		ListNode pre = l1;
    		int n = in.nextInt();
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
    		Solution19 result = new Solution19();
    		ListNode L = result.removeNthFromEnd(l1, n);
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