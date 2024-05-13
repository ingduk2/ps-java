package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * - 2개의 ListNode 를 하나의 sorted List 로 합쳐라
 *
 * 1. 재귀 구조로 연결
 */
public class Q14_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                4))),
                        new ListNode(
                                1, new ListNode(
                                3, new ListNode(
                                4))),
                        new ListNode(
                                1, new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                4))))))
                ),
                Arguments.of(
                        new ListNode(),
                        new ListNode(),
                        new ListNode()
                ),
                Arguments.of(
                        new ListNode(),
                        new ListNode(0),
                        new ListNode(0)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode list1, ListNode list2, ListNode expected) {
        ListNode result = new Q14_MergeTwoSortedLists().mergeTwoLists(list1, list2);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
