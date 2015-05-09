package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Reverse Linked List II 
 *         Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *         For example:
 *         Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *         return 1->4->3->2->5->NULL.
 *         Note:
 *         Given m, n satisfy the following condition:
 *         1 ≤ m ≤ n ≤ length of list.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-27
 */
public class Solution92
{
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	if (head == null || m == n)
    		return head;
    	int i = 0;
    	ListNode fakeHead = new ListNode(0);
    	fakeHead.next = head;
    	ListNode pre = fakeHead;
    	if (head != null)
    	{
    		++i;
    		while (head != null && i != m) {
    			pre = head;
    			head = head.next;
    			++i;
    		}
    		while (head != null && i != n)
    		{
    			head = head.next;
    			++i;
    		}
    		pre = reverse(pre, head.next);
    		head = pre.next;
    	}
		return fakeHead.next;  
    }
    
    private ListNode reverse(ListNode pre, ListNode next) {
    	ListNode last = pre.next;
        ListNode cur = last.next;
        while(cur != next){
        	
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
	}
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head == null || m == n)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        int i = 1;
        while(preNode.next != null && i < m)
        {
            preNode = preNode.next;
            i++;
        }
        if(i < m)
            return head;
        ListNode mNode = preNode.next;
        ListNode cur = mNode.next;
        while(cur != null && i < n)
        {
            ListNode next = cur.next;
            
            cur.next = preNode.next;
            preNode.next = cur;
            mNode.next = next;
            cur = next;
            i++;
        }
        return dummy.next;
    }
    
	public static void main(String[] args)
    {
    	Scanner cin = new Scanner(System.in);
    	while (cin.hasNext())
    	{
    		int start = cin.nextInt();
    		int end = cin.nextInt();
    		String[] str = cin.next().split(",");
    		Solution92 reverse2 = new Solution92();
    		ListNode head = reverse2.reverseBetween(getLinkedList(str), start, end);
    		while(head!= null) {
    			System.out.print(head.val);
    			if(head.next != null)
    				System.out.print("->");
    			head = head.next;
    		}
    	}
    	cin.close();
    }
    public static ListNode getLinkedList(String[] str) {
		ListNode fakeHead = new ListNode(0);
		ListNode pre = fakeHead;
		for (int i = 0; i < str.length; i++)
		{
			int num = Integer.parseInt(str[i]);
			ListNode pa = new ListNode(num);
			pre.next = pa;
			pre = pa;
		}
		pre.next = null;
		return fakeHead.next;
	}
}