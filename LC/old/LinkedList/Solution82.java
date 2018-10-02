package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Remove Duplicates from Sorted List II  
 *         Given a sorted linked list, delete all nodes that have duplicate numbers, 
 *         leaving only distinct numbers from the original list.
 *         For example,
 *         Given 1->2->3->3->4->4->5, return 1->2->5.
 *         Given 1->1->1->2->3, return 2->3.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-27
 */
public class Solution82
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
		if (head == null || head.next == null)
			return head;
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode pre = fakeHead;
		while (head != null && head.next != null)
		{
			if (head.val == head.next.val)
			{
				while (head.next != null && head.val == head.next.val)
					head = head.next;
				pre.next = head.next;
				head = head.next;
			}
			else
			{
				pre = head;
				head = head.next;
			}
		}
        return fakeHead.next;
    }
	
	public ListNode deleteDuplicates2(ListNode head) {
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
    		Solution82 removeD = new Solution82();
    		ListNode L = removeD.deleteDuplicates2(head);
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