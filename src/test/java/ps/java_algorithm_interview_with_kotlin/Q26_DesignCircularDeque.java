package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/design-circular-deque/description/
 * circular-deque
 */
public class Q26_DesignCircularDeque {
    static class MyCircularDeque {
        static class DoublyLinkedList {
            private DoublyLinkedList left;
            private DoublyLinkedList right;
            int val;

            public DoublyLinkedList(int val) {
                this.val = val;
            }
        }

        private int len;
        private int k;
        private DoublyLinkedList head;
        private DoublyLinkedList tail;

        public MyCircularDeque(int k) {
            head = new DoublyLinkedList(0);
            tail = new DoublyLinkedList(0);
            head.right = tail;
            tail.left = head;
            this.k = k;
            this.len = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }

            // - (head.l, head, head.r) - (node.l, node, node.r) - (tail.l, tail, tail.r)
            DoublyLinkedList node = new DoublyLinkedList(value);
            node.right = head.right;  // 새 노드의 오른쪽 링크를 기존 첫 번째 노드로 설정
            node.left = head;         // 새 노드의 왼쪽 링크를 head로 설정
            head.right.left = node;   // 기존 첫 번째 노드의 왼쪽 링크를 새 노드로 설정
            head.right = node;        // head의 오른쪽 링크를 새 노드로 설정
            len++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }

            // - (head.l, head, head.r) - (node.l, node, node.r) - (tail.l, tail, tail.r)
            DoublyLinkedList node = new DoublyLinkedList(value);
            node.left = tail.left;  // 새 노드의 왼쪽 링크를 기존 마지막 노드로 설정
            node.right = tail;      // 새 노드의 오른쪽 링크를 tail로 설정
            tail.left.right = node; // 기존 마지막 노드의 오른쪽 링크를 새 노드로 설정
            tail.left = node;       // tail의 왼쪽 링크를 새 노드로 설정
            len++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            head.right.right.left = head; // 두 번째 노드의 왼쪽 링크를 head로 설정
            head.right = head.right.right; // head의 오른쪽 링크를 두 번째 노드로 설정
            len--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            tail.left.left.right = tail; // 마지막 전 노드의 오른쪽 링크를 tail로 설정
            tail.left = tail.left.left; // tail의 왼쪽 링크를 마지막 전 노드로 설정
            len--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : head.right.val;
        }

        public int getRear() {
            return isEmpty() ? -1 : tail.left.val;
        }

        public boolean isEmpty() {
            return len == 0;
        }

        public boolean isFull() {
            return len == k;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of("MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"),
                        List.of(List.of(3), List.of(1), List.of(2), List.of(3), List.of(4), List.of(), List.of(), List.of(), List.of(4), List.of()),
                        new Object[]{null, true, true, true, false, 2, true, true, true, 4}
                ),
                Arguments.of(
                        List.of("MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "getFront"),
                        List.of(List.of(4), List.of(1), List.of(2), List.of(3), List.of(4), List.of(), List.of(), List.of(), List.of()),
                        new Object[]{null, true, true, true, true, 2, true, true, 4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<String> commands, List<List<Integer>> values, Object[] expected) {
        MyCircularDeque myQueue = null;
        Object[] result = new Object[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "MyCircularDeque":
                    myQueue = new MyCircularDeque(values.get(i).get(0));
                    result[i] = null;
                    break;
                case "insertFront":
                    boolean insertFront = myQueue.insertFront(values.get(i).get(0));
                    result[i] = insertFront;
                    break;
                case "insertLast":
                    boolean insertLast = myQueue.insertLast(values.get(i).get(0));
                    result[i] = insertLast;
                    break;
                case "deleteFront":
                    boolean deleteFront = myQueue.deleteFront();
                    result[i] = deleteFront;
                    break;
                case "deleteLast":
                    boolean deleteLast = myQueue.deleteLast();
                    result[i] = deleteLast;
                    break;
                case "getFront":
                    int front = myQueue.getFront();
                    result[i] = front;
                    break;
                case "getRear":
                    int rear = myQueue.getRear();
                    result[i] = rear;
                    break;
                case "isEmpty":
                    boolean isEmpty = myQueue.isEmpty();
                    result[i] = isEmpty;
                    break;
                case "isFull":
                    boolean isFull = myQueue.isFull();
                    result[i] = isFull;
                    break;
                default:
                    System.out.println(commands.get(i));
                    throw new RuntimeException();
            }
        }

        assertThat(result).isEqualTo(expected);
    }
}
