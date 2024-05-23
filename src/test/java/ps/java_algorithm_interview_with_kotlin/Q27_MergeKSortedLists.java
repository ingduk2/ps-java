package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * - 우선 순위 큐 이용 (ListNode.val 오름차순)
 * - 각 ListNode 를 pq 에 add
 * - pq 에서 poll 해서 tail = tail.next 로 계속 연결 (오름차순이 됨)
 * - tail.next != null 이면 pq.add
 *
 */
public class Q27_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.val == o2.val) return 0;
            else if (o1.val > o2.val) return 1;
            else return -1;
        });

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode root = new ListNode(0);
        ListNode tail = root;

        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next != null) {
                pq.add(tail.next);
            }
        }

        return root.next;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode[]{
                                new ListNode(1, new ListNode(4, new ListNode(5))),
                                new ListNode(1, new ListNode(3, new ListNode(4))),
                                new ListNode(2, new ListNode(6)),
                        },
                        new ListNode(
                                1, new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                4, new ListNode(
                                4, new ListNode(
                                5, new ListNode(
                                6))))))))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode[] lists, ListNode expected) {
        ListNode result = new Q27_MergeKSortedLists().mergeKLists(lists);
        assertListNode(result, expected);
    }
}
