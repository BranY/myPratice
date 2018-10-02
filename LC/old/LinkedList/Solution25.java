package LeetCode;

import java.util.Scanner;

/*
 * Program :
 *         Reverse Nodes in k-Group 
 *         Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *         If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *         
 *         You may not alter the values in the nodes, only nodes itself may be changed.
 *         Only constant memory is allowed.
 *         For example.
 *         Given this linked list: 1->2->3->4->5
 *         For k = 2, you should return: 2->1->4->3->5
 *         For k = 3, you should return: 3->2->1->4->5
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-26
 */
public class Solution25
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
	 * 399ms
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) 
        	return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        while(head != null){
            i++;
            if(i % k == 0){
                pre = reverse(pre, head.next);
                head = pre.next;
            }else {
                head = head.next;
            }
        }
        return dummy.next;
    }
	/*
	 *reverse range[pre.next, head.next)
	 */
	public static ListNode reverse(ListNode pre, ListNode next) {
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
	/*
	 * wrong
	 */
	/*
	 *reverse range[pre.next, head.next)
	 *头插法
	 *p->next
	 *q->head
	 */
	
	/*
	 * 344ms
	 */
	public ListNode reverseKGroup1(ListNode head, int k) {
		if (head == null || head.next == null || k<=1) 
			return head;
		int len = 0;
		ListNode p = head;
		while (p != null)
		{
			len++;
			p = p.next;
		}
		if (k > len)
			return head;
		ListNode q = head;
		p = null;
		int n = k;
		while (q != null && n > 0)
		{
			ListNode x = q.next;
			q.next = p;
			p = q;
			q = x;
			n--;
		}
		if (len - k >= k)
			head.next = reverseKGroup1(q, k);
		else
			head.next = q;
		return p;
	}
	public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	while(in.hasNext()) {
    		int k = in.nextInt();
    		String[] str = in.next().split(",");
    		ListNode head = getLinkedList(str);
    		Solution25 reverseK = new Solution25();
    		ListNode L = reverseK.reverseKGroup(head, k);
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