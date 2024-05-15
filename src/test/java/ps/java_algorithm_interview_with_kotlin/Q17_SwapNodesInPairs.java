package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * - 2개씩 Swap
 * 1. 값만 교환
 * 2. 반복 구조로 스왑
 * 3. 재귀 구조로 스왑
 */
public class Q17_SwapNodesInPairs {

    static class ValueChange {
        public ListNode swapPairs(ListNode head) {
            ListNode node = head;

            while (node != null && node.next != null) {
                int tmp = node.val;
                node.val = node.next.val;
                node.next.val = tmp;
                node = node.next.next;
            }

            return head;
        }
    }

    static class SwapSolve {
        public ListNode swapPairs(ListNode head) {
            ListNode node = new ListNode(0);
            ListNode root = node;
            node.next = head;

            while (node.next != null && node.next.next != null) {
                ListNode a = node.next;
                ListNode b = node.next.next;
                a.next = b.next;
                node.next = b;
                node.next.next = a;
                node = node.next.next;
            }

            return root.next;
        }
    }

    static class RecursiveSolve {
        public ListNode swapPairs(ListNode head) {
            if (head != null && head.next != null) {
                ListNode p = head.next;
                head.next = swapPairs(head.next.next);
                p.next = head;
                return p;
            }

            return head;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                4)))),
                        new ListNode(
                                2, new ListNode(
                                1, new ListNode(
                                4, new ListNode(
                                3))))
                ),
                Arguments.of(
                        new ListNode(),
                        new ListNode()
                ),
                Arguments.of(
                        new ListNode(1),
                        new ListNode(1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void valueChangeTest(ListNode head, ListNode expected) {
        ListNode result = new ValueChange().swapPairs(head);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void swapSolveTest(ListNode head, ListNode expected) {
        ListNode result = new SwapSolve().swapPairs(head);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void recursiveSolveTest(ListNode head, ListNode expected) {
        ListNode result = new RecursiveSolve().swapPairs(head);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
