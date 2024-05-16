package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/odd-even-linked-list/description/
 * - 홀수 그룹 -> 짝수 그룹 으로 만들자
 *
 * - 홀수 그룹 만들고
 * - 짝수 그룹 만들고
 * - 홀수 마지막에 짝수 그룹 연결
 */
public class Q18_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                5))))),
                        new ListNode(
                                1, new ListNode(
                                3, new ListNode(
                                5, new ListNode(
                                2, new ListNode(
                                4)))))
                ),
                Arguments.of(
                        new ListNode(
                                2, new ListNode(
                                1, new ListNode(
                                3, new ListNode(
                                5, new ListNode(
                                6, new ListNode(
                                4, new ListNode(
                                7))))))),
                        new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                6, new ListNode(
                                7, new ListNode(
                                1, new ListNode(
                                5, new ListNode(
                                4)))))))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode head, ListNode expected) {
        ListNode result = new Q18_OddEvenLinkedList().oddEvenList(head);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
