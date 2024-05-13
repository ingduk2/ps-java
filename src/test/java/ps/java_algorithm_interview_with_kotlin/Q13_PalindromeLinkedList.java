package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.leetcode.ListNode;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 * 1. List TwoPointer
 * 2. Stack
 * 3. Deque
 * 4. Runner
 */
public class Q13_PalindromeLinkedList {

    static class ListTwoPointer {
        public boolean isPalindrome(ListNode head) {
            List<Integer> nodes = new ArrayList<>();

            // ListNode -> List<>
            ListNode node = head;
            while (node != null) {
                nodes.add(node.val);
                node = node.next;
            }

            // valid palindrome
            int start = 0;
            int end = nodes.size() - 1;

            while (start < end) {
                int s = nodes.get(start);
                int e = nodes.get(end);
                if (s != e) {
                    return false;
                }

                start++;
                end--;
            }

            return true;
        }
    }

    static class StackSolve {
        public boolean isPalindrome(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode node = head;
            while (node != null) {
                stack.add(node.val);
                node = node.next;
            }

            while (head != null) {
                if (head.val != stack.pop()) {
                    return false;
                }
                head = head.next;
            }

            return true;
        }
    }

    static class DequeSolve {
        public boolean isPalindrome(ListNode head) {
            Deque<Integer> deque = new LinkedList<>();

            ListNode node = head;
            while (node != null) {
                deque.add(node.val);
                node = node.next;
            }

            while (!deque.isEmpty() && deque.size() > 1) {
                if (!Objects.equals(deque.pollFirst(), deque.pollLast())) {
                    return false;
                }
            }

            return true;
        }
    }

    static class RunnerSolve {
        public boolean isPalindrome(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;

            // 빠른 러너가 끝까지 갈 때 까지 느린 러너와 함꼐 진행
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 홀수 개 일 떄 느린 러너가 한 칸 더 앞으로 가도록 처리
            if (fast != null) {
                slow = slow.next;
            }
            // 중간에 도달한 느린 러너를 기준으로 하여 역순 연결 리스트를 만든다
            ListNode rev = null;
            while (slow != null) {
                ListNode next = slow.next;
                slow.next = rev;
                rev = slow;
                slow = next;
            }
            // rev 연결 리스트를 끝까지 이동하여 비교
            while (rev != null) {
                if (rev.val != head.val) {
                    return false;
                }
                rev = rev.next;
                head = head.next;
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                2, new ListNode(
                                1)))),
                        true
                ),
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                        2)),
                        false
                ),
                Arguments.of(
                        new ListNode(
                                1, new ListNode(
                                2, new ListNode(
                                3, new ListNode(
                                2, new ListNode(
                                1))))),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(ListNode head, boolean expected) {
        // ListTwoPointer
        boolean result = new ListTwoPointer().isPalindrome(head);
        assertThat(result).isEqualTo(expected);

        // Stack
        boolean result2 = new StackSolve().isPalindrome(head);
        assertThat(result2).isEqualTo(expected);

        // Deque
        boolean result3 = new DequeSolve().isPalindrome(head);
        assertThat(result3).isEqualTo(expected);

        // Runner
        boolean result4 = new RunnerSolve().isPalindrome(head);
        assertThat(result4).isEqualTo(expected);
    }
}
