package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Remove Duplicates from Sorted List
 *         Given a sorted linked list, delete all duplicates such that each element appear only once.
 *         For example,
 *         Given 1->1->2, return 1->2.
 *         Given 1->1->2->3->3, return 1->2->3.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-27
 */
public class Solution83
{
	static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	public ListNode deleteDuplicates(ListNode head) {
	    if(head == null)
	        return head;
	    ListNode pre = head;
	    ListNode cur = head.next;
	    while(cur!=null)
	    {
	        if(cur.val == pre.val)
	            pre.next = cur.next;
	        else    
	            pre = cur;
	        cur = cur.next;
	    }
	    return head;
	}
	public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	while(in.hasNext()) {
    		String[] str = in.next().split(",");
    		ListNode head = getLinkedList(str);
    		Solution83 removeD = new Solution83();
    		ListNode L = removeD.deleteDuplicates(head);
    		while(L != null) {
    			System.out.print(L.val);
    			if(L.next != null)
    				System.out.print("->");
    			L = L.next;
    		}
    	}
    	in.close();
	}
	/*
	 * 尾插法
	 */
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
	/*
	 *头插法 
	 *读入数据的顺序与生成的链表中元素的顺序是相反的
	 */
	public static ListNode getLinkedListH(String[] str) {
		ListNode fakeHead = new ListNode(0);
		for (int i = 0; i < str.length; i++)
		{
			int num = Integer.parseInt(str[i]);
			ListNode pa = new ListNode(num);
			pa.next = fakeHead.next;
			fakeHead.next = pa;
		}
		return fakeHead.next;
	}
	public static ListNode reverseH(ListNode head)
	 {
		 ListNode fakeHead = new ListNode(0);
		 ListNode p = head;
		 ListNode q = null;
		 while (p != null)
		 {
			 q = p.next; 
			 
			 p.next = fakeHead.next;
			 fakeHead.next = p;
			 
			 p = q;
		 }
		return fakeHead.next; 
	 }
}