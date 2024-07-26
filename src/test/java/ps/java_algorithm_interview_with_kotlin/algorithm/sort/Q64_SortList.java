package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/sort-list/description/
 * 연결리스트를 O(n log n) 에 정렬하라
 *
 * 1. 병합 정렬
 */
public class Q64_SortList {

    static class Solution1 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode half = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                half = slow;
                slow = slow.next;
                fast = fast.next.next;
            }

            half.next = null;

            ListNode l1 = sortList(head);
            ListNode l2 = sortList(slow);

            return mergeTwoLists(l1, l2);
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            if (l1.val > l2.val) {
                ListNode temp = l1;
                l1 = l2;
                l2 = temp;
            }

            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                4, new ListNode(
                                2, new ListNode(
                                1, new ListNode(
                                3)))
                        ),
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                4)))
                        )
                ),
                Arguments.of(
                        new ListNode(
                                -1, new ListNode(
                                5, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                0))))),
                        new ListNode(
                                -1, new ListNode(
                                0, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                5)))))
                ),
                Arguments.of(
                        null,
                        null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode head, ListNode expected) {
        ListNode result = new Solution1().sortList(head);
        AssertionsLeetCode.assertListNode(result, expected);
    }
}
