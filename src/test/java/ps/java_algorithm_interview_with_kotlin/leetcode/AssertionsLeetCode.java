package ps.java_algorithm_interview_with_kotlin.leetcode;

public class AssertionsLeetCode {
    private AssertionsLeetCode() {}

    public static void assertListNode(ListNode result, ListNode expected) {
        while (result != null && expected != null) {
            if (result.val != expected.val) {
                throw new RuntimeException();
            }
            result = result.next;
            expected = expected.next;
        }
    }
}
