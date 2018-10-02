package LeetCode;

import java.util.Scanner;
/*
 * Program :
 *         Merge Two Sorted Lists
 *         
 *         Merge two sorted linked lists and return it as a new list. 
 *         The new list should be made by splicing together the nodes of the first two lists.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-25
 */
public class Solution21
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
	 * 390ms
	 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode head = null;
        ListNode pre = null;
        pre = head;
        if (l1 == null && l2 == null)
        	return head;
        while (l1 != null && l2 != null)
        {
        	if (l1.val < l2.val)
        	{
        		ListNode temp = l1;
        		if (head == null)
        		{
        			head = temp;
        			pre = head;
        		}
        		else
        		{
        			pre.next = temp;
        			pre = pre.next;
        		}
        		l1 = l1.next;
        	}
        	else
        	{
        		ListNode temp = l2;
        		if (head == null)
        		{
        			head = temp;
        			pre = head;
        		}
        		else
        		{
        			pre.next = temp;
        			pre = pre.next;
        		}
        		l2 = l2.next;
        	}
        }
        
        while (l1 != null)
        {
        	ListNode temp = l1;
        	if (head == null){
        		head = temp;
        		pre = head;
        	}
        	else
        	{
        		pre.next = temp;
        		pre = pre.next;
        	}
        	l1 = l1.next;
        }
        while (l2 != null)
        {
        	ListNode temp = l2;
        	if (head == null){
        		head = temp;
        		pre = head;
        	}
        	else
        	{
        		pre.next = temp;
        		pre = pre.next;
        	}
        	l2 = l2.next;
        }
		return head;
    }
    
    /*
     * 302ms
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    	 ListNode p1 = l1;
         ListNode p2 = l2;
  
         ListNode fakeHead = new ListNode(0);
         ListNode p = fakeHead;
         while(p1 != null && p2 != null){
             if(p1.val <= p2.val){
                 p.next = p1;
                 p1 = p1.next;
             }else{
                 p.next = p2;
                 p2 = p2.next;
             }
    
             p = p.next;
           }
    
           if(p1 != null)
               p.next = p1;
           if(p2 != null)
               p.next = p2;
           return fakeHead.next;
    }
    /*
     * recursion solution
     * 292ms
     */
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2)
    {
    	 if (l1 == null) return l2;
    	 if (l2 == null) return l1;  
    	 ListNode node;
    	 if (l1.val < l2.val)
    	 {    	      
    		 node = l1; 
    		 node.next = mergeTwoLists3(l1.next, l2); 
    	 }else
    	 {
    	      node = l2;
    	      node.next = mergeTwoLists3(l1, l2.next);  
    	 }
    	 return node;
    }
    
    /*
     * 283ms
     */
    public ListNode mergeTwoLists4(ListNode l1, ListNode l2)
    {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	ListNode returnNode, n1;
    	returnNode = n1 = l1.val <l2.val? l1 : l2;
    	ListNode n2 = l1.val < l2.val ? l2 : l1;
    	while (n1 != null && n2 != null)
    	{
    		while(n1.next != null && n1.next.val <= n2.val)
    			n1 = n1.next;
    		ListNode old = n1.next;
    		n1.next = n2;
    		n1 = n2;
    		n2 = old;
    	}
    	return returnNode;
    }
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext())
		{
			ListNode l1 = null;
        	ListNode l2 = null;
    		ListNode pre1 = l1;
    		ListNode pre2 = l2;
    		String s1 = cin.next();
    		String s2 = cin.next();
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
    		
    		Solution21 merge = new Solution21();
    		ListNode head = merge.mergeTwoLists4(l1, l2);
    		while(head!=null) {
    			System.out.print(head.val);
    			if(head.next != null)
    				System.out.print("->");
    			head = head.next;
    		}
		}
		cin.close();
	}
}