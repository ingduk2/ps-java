package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.math.BigInteger;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 * 1. 자료형 반환
 * - ListNode 를 뒤집는다
 * - BigInt 로 add 한다
 * - BigInt 를 뒤집어서 ListNode 로 변환한다
 *
 * 2. 전가산기 구현
 */
public class Q16_AddTwoNumbers {

    static class BigIntegerSolve {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode listNode1 = reverseList(l1);
            ListNode listNode2 = reverseList(l2);
            BigInteger add = toBigInt(listNode1).add(toBigInt(listNode2));
            return toReversedLinkedList(add);
        }

        private ListNode reverseList(ListNode head) {
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

        private BigInteger toBigInt(ListNode node) {
            StringBuilder val = new StringBuilder();
            while (node != null) {
                val.append(node.val);
                node = node.next;
            }

            return new BigInteger(val.toString());
        }

        private ListNode toReversedLinkedList(BigInteger val) {
            ListNode prev = null;
            ListNode node = null;

            char[] charArray = String.valueOf(val).toCharArray();
            for (char c : charArray) {
                node = new ListNode(Character.getNumericValue(c));
                node.next = prev;
                prev = node;
            }

            return prev;
        }
    }

    static class FullAdderSolve {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode node = new ListNode(0);
            ListNode root = node;

            int sum = 0;
            int carry = 0;
            int remainder = 0;

            while (l1 != null || l2 != null || carry != 0) {
                sum = 0;
                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                remainder = (sum + carry) % 10;
                carry = (sum + carry) / 10;
                node.next = new ListNode(remainder);
                node = node.next;
            }

            return root.next;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                2, new ListNode(
                                4, new ListNode(
                                3))),
                        new ListNode(
                                5, new ListNode(
                                6, new ListNode(
                                4))),
                        new ListNode(
                                7, new ListNode(
                                0, new ListNode(
                                8)))
                ),
                Arguments.of(
                        new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9))))))),
                        new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9)))),
                        new ListNode(
                                8, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                9, new ListNode(
                                0, new ListNode(
                                0, new ListNode(
                                0, new ListNode(
                                1))))))))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void bigIntegerTest(ListNode l1, ListNode l2, ListNode expected) {
        // BigInteger
        ListNode result = new BigIntegerSolve().addTwoNumbers(l1, l2);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void fullAdderTest(ListNode l1, ListNode l2, ListNode expected) {
        // FullAdder
        ListNode result = new FullAdderSolve().addTwoNumbers(l1, l2);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
