package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/design-circular-queue/description/
 * - 원형 큐
 *
 */
public class Q25_DesignCircularQueue {
    static class MyCircularQueue {
        private int[] q;
        private int front = 0;
        private int rear = -1;
        private int len = 0;

        public MyCircularQueue(int k) {
            this.q = new int[k];
        }

        public boolean enQueue(int value) {
            if (!this.isFull()) {
                this.rear = (this.rear + 1) % this.q.length;
                this.q[rear] = value;
                this.len++;
                return true;
            } else {
                return false;
            }
        }

        public boolean deQueue() {
            if (!this.isEmpty()) {
                this.front = (this.front + 1) % this.q.length;
                this.len--;
                return true;
            } else {
                return false;
            }
        }

        public int Front() {
            return this.isEmpty() ? -1 : this.q[this.front];
        }

        public int Rear() {
            return this.isEmpty() ? -1 : this.q[this.rear];
        }

        public boolean isEmpty() {
            return this.len == 0;
        }

        public boolean isFull() {
            return this.len == this.q.length;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of("MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"),
                        List.of(List.of(3), List.of(1), List.of(2), List.of(3), List.of(4), List.of(), List.of(), List.of(), List.of(4), List.of()),
                        new Object[]{null, true, true, true, false, 3, true, true, true, 4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<String> commands, List<List<Integer>> values, Object[] expected) {
        MyCircularQueue myQueue = null;
        Object[] result = new Object[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "MyCircularQueue":
                    myQueue = new MyCircularQueue(3);
                    result[i] = null;
                    break;
                case "Front":
                    int front = myQueue.Front();
                    result[i] = front;
                    break;
                case "Rear":
                    int rear = myQueue.Rear();
                    result[i] = rear;
                    break;
                case "enQueue":
                    boolean enQueue = myQueue.enQueue(values.get(i).get(0));
                    result[i] = enQueue;
                    break;
                case "deQueue":
                    boolean deQueue = myQueue.deQueue();;
                    result[i] = deQueue;
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
