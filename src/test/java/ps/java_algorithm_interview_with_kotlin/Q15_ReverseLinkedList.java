package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * - 주어진 LinkedList 를 reverse
 * 1. 반복 구조로 뒤집기
 * - node : 1, 2, 3, 4, 5
 * - reverse : null
 *                      node: 1,2,3,4,5 | 2,3,4,5  | 3,4,5      | 4,5         | 5
 * ListNode next = node.next; 2,3,4,5   | 3,4,5    | 4,5        | 5           | null
 * node.next = reverse;       null      | 1,null   | 2,1,null   | 3,2,1,null  | 4,3,2,1,null
 * reverse = node;            1, null   | 2,1,null | 3,2,1,null | 4,3,2,1,null| 5,4,3,2,1,null
 * node = next;               2,3,4,5   | 3,4,5    | 4,5        | 5           | null
 */
public class Q15_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode reverse = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = reverse;
            reverse = node;
            node = next;
        }

        return reverse;
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
                                5, new ListNode(
                                4, new ListNode(
                                3, new ListNode(
                                2, new ListNode(
                                1)))))

                ),
                Arguments.of(
                        new ListNode(1,
                                new ListNode(2)),
                        new ListNode(2,
                                new ListNode(1))
                ),
                Arguments.of(
                        new ListNode(),
                        new ListNode()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode head, ListNode expected) {
        ListNode result = new Q15_ReverseLinkedList().reverseList(head);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
