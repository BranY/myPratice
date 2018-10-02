package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
 * Program :
 *         Merge k Sorted Lists 
 *         
 *         Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *   History:
 *   @author wuyan  version 1 
 *   2015-4-25
 */
public class Solution23
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
	 * merge sort
	 * O(nklogk)
	 * 空间复杂度的话是递归栈的大小O(logk)
	 * 428ms
	 */
    public ListNode mergeKLists(ListNode[] lists) {
    	if (lists.length == 0)
    		return null;
    	if (lists.length == 1)
    		return lists[0];
    	
    	return helper(lists, 0, lists.length - 1);
    }
    private ListNode helper(ListNode[] lists, int l, int r) {
        if(l < r)
        {
            int m = (l + r) / 2;
            return merge(helper(lists, l, m), helper(lists, m+1, r));
        }
        return lists[l];
	}
	private ListNode merge(ListNode l1, ListNode l2) {
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
	/*
	 * 这种方法用到了堆的数据结构，思路比较难想到，但是其实原理比较简单。维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
	 * 因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
	 * 这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)。
	 * 468ms
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>()
		{
			@Override
            public int compare(ListNode n1, ListNode n2)  
            {  
                return n1.val - n2.val;  
            } 
		});
	    for(int i = 0;i < lists.length;i++)
	    {
	        ListNode node = lists[i]; 
	        if(node != null)
	        {
	            heap.offer(node);
	        }
	    }
	    ListNode head = null;
	    ListNode pre = head;
	    while(heap.size() > 0)
	    {
	        ListNode cur = heap.poll();
	        if(head == null)
	        {
	            head = cur;
	            pre = head;
	        }
	        else
	        {
	            pre.next = cur;
	        }
	        pre = cur;
	        if(cur.next != null)
	            heap.offer(cur.next);
	    }
	    return head;
	}
	/*
	 * 527ms
	 */
	public ListNode mergeKLists3(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>()
		{
			@Override
            public int compare(ListNode n1, ListNode n2)  
            {  
                return n1.val - n2.val;  
            } 
		});
		for (ListNode list : lists) {
			if (list != null)
				heap.add(list);
		}
		ListNode head = new ListNode(0);
		ListNode p = head; 
 
		while (heap.size() > 0) {
			ListNode temp = heap.poll();
			p.next = temp;
			if (temp.next != null)
				heap.add(temp.next);
			p = p.next;
		}
		return head.next;
	}
    public static void main(String[] args)
    {
    	Scanner in = new Scanner(System.in);
    	while (in.hasNext())
    	{
    		Solution23 mergeK =new Solution23();
    		int size = in.nextInt();
    		ListNode[] lists = new ListNode[size];
    		for (int i = 0; i < size; i++)
    		{
    			String[] str = in.next().split(",");
    			lists[i] = getNodeList(str);
    		}
    		ListNode result = mergeK.mergeKLists2(lists);
    		while(result != null) {
    			System.out.print(result.val);
    			if(result.next != null)
    				System.out.print("->");
    			result = result.next;
    		}
    	}
    	in.close();
    }

	public static ListNode getNodeList(String[] str) {
		ListNode l1 = null;
		ListNode pre = l1;
		for (int i = 0; i < str.length; i++)
		{
			int num = Integer.parseInt(str[i]);
			ListNode pa = new ListNode(num);
			if(l1 == null)
				l1 = pa;
			else
				pre.next = pa;
			pre = pa;
		}
		return l1;
	}
}