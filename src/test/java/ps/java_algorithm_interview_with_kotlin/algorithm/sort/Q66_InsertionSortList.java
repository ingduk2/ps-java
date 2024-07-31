package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 *
 * 1. 삽입 정렬
 */
public class Q66_InsertionSortList {

    static class Solution1 {
        public ListNode insertionSortList(ListNode head) {
            // 루트를 가리키는 변수
            ListNode parent = new ListNode();
            // 정렬을 끝낸 대상을 가리키는 포인터
            ListNode p = parent;

            while (head != null) {
                // 정렬을 끝낸 포인터(p) 의 다음 값이 정렬을 해야 할 대상(head) 보다 작다면 계속 이동
                while (p.next != null && p.next.val < head.val) {
                    p = p.next;
                }

                // 서로 위치를 교환하고 정렬을 해야 할 대상(head) 은 다음 노드로 이동
                ListNode pNext = p.next;
                ListNode headNext = head.next;
                p.next = head;
                head.next = pNext;
                head = headNext;

                // 정렬을 끝낸 포인터(p) 의 값이 다음번 정렬을 해야 할 대상(head) 보다 클 때만
                // 포인터 맨 앞으로 이동
                if (head != null && p.val > head.val) {
                    p = parent;
                }
            }

            return parent.next;
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
                                0))))
                        ),
                        new ListNode(
                                -1, new ListNode(
                                0, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                5))))
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode head, ListNode expected) {
        ListNode result = new Solution1().insertionSortList(head);
        AssertionsLeetCode.assertListNode(result, expected);
    }
}
