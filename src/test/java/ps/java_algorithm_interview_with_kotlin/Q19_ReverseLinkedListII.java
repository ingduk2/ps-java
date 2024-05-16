package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.leetcode.AssertionsLeetCode.assertListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 * 1. 반복 구조로 노드 뒤집기
 *
 * 2. subList 만 swap1
 * - start > subList -> end
 *
 * 3. subList 만 swap2
 * - start > subList -> end
 *
 */
public class Q19_ReverseLinkedListII {

    static class SwapSolve {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null) {
                return null;
            }

            ListNode root = new ListNode(0);
            root.next = head;

            ListNode start = root;
            for (int i = 0; i < left - 1; i++) {
                start = start.next;
            }
            ListNode end = start.next;

            for (int i = 0; i < right - left; i++) {
                ListNode tmp = start.next;
                start.next = end.next;
                end.next = end.next.next;
                start.next.next = tmp;
            }

            return root.next;
        }
    }

    static class ReverseSolve1 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null) {
                return null;
            }

            ListNode root = new ListNode(0);
            root.next = head;

            // left 이전 노드
            ListNode leftPrev = root;
            for (int i = 0; i < left - 1; i++) {
                leftPrev = leftPrev.next;
            }

            // left 노드
            ListNode leftNode = leftPrev.next;

            // right 노드
            ListNode rightNode = leftNode;
            for (int i = left; i < right; i++) {
                rightNode = rightNode.next;
            }

            ListNode rightNext = rightNode.next;
            rightNode.next = null;

            // reverse
            ListNode prev = null;
            ListNode current = leftNode;
            while (current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            leftPrev.next = rightNode;
            leftNode.next = rightNext;
            return root.next;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode node = head;

            while (node != null) {
                ListNode next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }

            return prev;
        }
    }

    static class ReverseSolve2 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || left == right) return head; // 예외 처리

            ListNode dummy = new ListNode(0); // 더미 노드 생성
            dummy.next = head; // 더미 노드의 다음을 head로 설정
            ListNode prev = dummy; // 이전 노드 초기화
            ListNode current = head; // 현재 노드 초기화

            // left 이전 노드 찾기
            for (int i = 0; i < left - 1; i++) {
                prev = current;
                current = current.next;
            }

            ListNode start = prev; // subList 시작 노드 설정
            ListNode end = current; // subList 끝 노드 설정

            // subList 역순으로 만들기
            for (int i = left; i <= right; i++) {
                ListNode next = current.next; // 다음 노드 임시 저장
                current.next = prev; // 현재 노드의 다음을 이전 노드로 설정
                prev = current; // 이전 노드 업데이트
                current = next; // 현재 노드 업데이트
            }

            start.next = prev; // subList의 시작 노드의 다음을 subList의 끝으로 설정
            end.next = current; // subList의 끝 노드의 다음을 subList 이후 노드로 설정

            return dummy.next; // 더미 노드의 다음 노드 반환
        }
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
                        2, 4,
                        new ListNode(
                                1, new ListNode(
                                4, new ListNode(
                                3, new ListNode(
                                2, new ListNode(
                                5)))))
                ),
                Arguments.of(
                        new ListNode(5),
                        1, 1,
                        new ListNode(5)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void swapSolveTest(ListNode head, int left, int right, ListNode expected) {
        ListNode result = new SwapSolve().reverseBetween(head, left, right);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void reverseSolveTest1(ListNode head, int left, int right, ListNode expected) {
        ListNode result = new ReverseSolve1().reverseBetween(head, left, right);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void reverseSolveTest2(ListNode head, int left, int right, ListNode expected) {
        ListNode result = new ReverseSolve2().reverseBetween(head, left, right);
        result.printListNode();
        expected.printListNode();
        assertListNode(result, expected);
    }
}
