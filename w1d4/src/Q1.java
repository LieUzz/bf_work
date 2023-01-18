/**
 * Reverse a LinkedList.
 */
public class Q1 {
    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println(printListNode(n1));
        ListNode res = reverseList(n1);
        System.out.println(printListNode(res));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        return prev;
    }

    public static String printListNode(ListNode head) {
        String str = "";
        while (head != null) {
            str += head.val + " ";
            head = head.next;
        }
        return str;
    }
}

class ListNode {
    int val;
    ListNode next;

//    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

