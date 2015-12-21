import java.util.Scanner;

/**
 * Rotate List
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 *
 * Created by wuyan on 2015/12/21.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k < 0)
            return null;
        if (k == 0)
            return head;
        ListNode p = head;
        ListNode q = p;
        while (q != null) {
            int num = 0;
            while (num < k && q != null) {
                ++num;
                q = q.next;
            }
            if (q == null)
                return head;
            if (q.next == null)
                break;
            else
                p = q;
        }
        ListNode tmp = head;
        head = p.next;
        q.next = tmp;
        p.next = null;

        return head;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        ListNode p = head;
        int len = 1;//since p is already point to head
        while (p.next != null) {
            len++;
            p = p.next;
        }
        p.next = head; //form a loop
        k = k % len;
        for (int i = 0; i < len - k; i++) {
            p = p.next;
        } //now p points to the prev of the new head
        head = p.next;
        p.next = null;
        return head;
    }
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int k = cin.nextInt();
            String[] str = cin.next().split(",");

            ListNode head = new ListNode(Integer.parseInt(str[0]));
            ListNode next = head;
            for (int i = 1; i < str.length; i++) {
                int num = Integer.parseInt(str[i]);
                ListNode tmp = new ListNode(num);
                next.next = tmp;
                tmp.next = null;
                next = tmp;
            }
            ListNode p = head;
            while(p != null) {
                System.out.print(p.val + "->");
                p = p.next;
            }
            System.out.println("null");

            Solution61 rotate = new Solution61();
            ListNode result = rotate.rotateRight2(head, k);
            ListNode q = result;
            while(q != null) {
                System.out.print(q.val + "->");
                q = q.next;
            }
            System.out.println("null");
        }
        cin.close();
    }
}
