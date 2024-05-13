package ps.java_algorithm_interview_with_kotlin.leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printListNode() {
        ListNode print = this;
        while (print != null) {
            System.out.print(print.val + " ");
            print = print.next;
        }
        System.out.println();
    }
}
