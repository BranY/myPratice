package src.java.offer;

/**
 * Created by Yang on 2017/4/15.
 * 输入一个链表，反转链表后，输出链表的所有元素。
 */
public class Problem24 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
        public String toString() {
            return val + " " + this.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode reversedHead = null;
        ListNode currNode = head;
        ListNode prevNode = null;
        ListNode nextNode = null;
        while(currNode != null) {
            nextNode = currNode.next;
            if(nextNode != null) {
                reversedHead = nextNode;
            }
            currNode.next = prevNode;

            prevNode = currNode;
            currNode = nextNode;
        }
        return reversedHead;
    }

    public static void main(String[] args) {
        Problem24 reverseList = new Problem24();
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for(int i = 1; i < 12; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println(head);
        System.out.println(reverseList.reverseList(head));
    }
}
