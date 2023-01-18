import java.util.*;

public class Main {
    public static void main(String[] args) {

        String c1 = "UUDL";
        int[] r1 = walk(c1);
        System.out.println(r1[0] + "," + r1[1]);
        String c2 = "UUULRDUR";
        int[] r2 = walk(c2);
        System.out.println(r2[0] + "," + r2[1]);

        String s = "abcda";
        System.out.println(firstNonRepeating(s));


        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l4.next = l5;
        int res = sumOfLinkedList(l1, l4);
        System.out.println(res);
    }


    /**
     * A robot on an infinite XY-plane starts at point [0,0],
     * this robot can receive a series of command, such as "UUDL",
     * and move accordingly.
     * @param commands
     * @return
     */
    public static int[] walk(String commands) {
        int[] res = new int[] {0, 0};

        for (char ch: commands.toCharArray()) {
            if (ch == 'U') {
                res[1] ++;
            } else if (ch == 'D') {
                res[1] --;
            } else if (ch == 'R') {
                res[0] ++;
            } else if (ch == 'L') {
                res[0] --;
            }

        }

        return res;
    }

    public static char firstNonRepeating(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch:s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        char res = ' ';
        for (char ch:s.toCharArray()) {
            if (map.get(ch) == 1) {
                res = ch;
                break;
            }
        }
        return res;
    }

    public static int sumOfLinkedList(ListNode l1, ListNode l2){
        ListNode re1 = reverse(l1);
        ListNode re2 = reverse(l2);
        ListNode res = new ListNode(-1);
        ListNode curr = res;
        int flag = 0;
        while (re1 != null && re2 != null) {
            int a = re1.val + re2.val + flag;
            if (a > 10) {
                curr.next = new ListNode(a - 10);
                flag = 1;
            } else {
                curr.next = new ListNode(a);
                flag = 0;
            }
            re1 = re1.next;
            re2 = re2.next;
            curr = curr.next;
        }

        ListNode tmp = null;
        if (re1 != null) {
            tmp = re1;
        } else if (re2 != null) {
            tmp = re2;
        }
        while (tmp!= null) {
            int a = tmp.val + flag;
            if (a > 10) {
                curr.next = new ListNode(a - 10);
                flag = 1;
            } else {
                curr.next = new ListNode(a);
                flag = 0;
            }
            tmp = tmp.next;
            res = res.next;
        }

        res = reverse(res);
        int ans = 0;
        while (res != null && res.val!=-1) {
            ans *= 10;
            ans += res.val;
            res = res.next;
        }
        return ans;



    }

    public static ListNode reverse(ListNode head) {
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

}

class ListNode{
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}