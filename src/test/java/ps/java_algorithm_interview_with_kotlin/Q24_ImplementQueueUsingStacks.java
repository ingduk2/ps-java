package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/description/
 * - 2개의 stack 으로 queue 를 만들어라
 * - 1,2,3,4 (push) -> 1,2,3,4 (pop)
 * - stack 은 4,3,2,1 이 되어야 1,2,3,4 로 나온다
 * - stack 은 LIFO, queue 는 FIFO
 * - stack pop 시에 마지막 게 나오는데, 그걸 첫 번째로 바꿔주어야 한다
 * - stack 을 다 빼서, 다른 stack 에 넣고 pop 을 하면 뒤집어진다
 * - Queue 만드는 것 처럼 push 할 때 넣으면 1, 2 | 3 이면 2, 1, 3 이 되어 버린다,
 */
public class Q24_ImplementQueueUsingStacks {
    static class MyQueue {

        private Deque<Integer> input;
        private Deque<Integer> output;

        public MyQueue() {
            input = new ArrayDeque<>();
            output = new ArrayDeque<>();
        }

        public void push(int x) {
            input.push(x);
        }

        public int pop() {
            peek();
            return output.pop();
        }

        public int peek() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }

            return output.peek();
        }

        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of("MyQueue", "push", "push", "peek", "pop", "empty"),
                        List.of(List.of(), List.of(1), List.of(2), List.of(), List.of()),
                        new Object[]{null, null, null, 1, 1, false}
                ),
                Arguments.of(
                        List.of("MyQueue", "push", "push", "push", "push", "pop", "pop", "pop", "pop", "empty"),
                        List.of(List.of(), List.of(1), List.of(2), List.of(3), List.of(4), List.of(), List.of(), List.of(), List.of(), List.of()),
                        new Object[]{null, null, null, null, null, 1, 2, 3, 4, true}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<String> commands, List<List<Integer>> values, Object[] expected) {
        MyQueue myQueue = null;
        Object[] result = new Object[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "MyQueue":
                    myQueue = new MyQueue();
                    result[i] = null;
                    break;
                case "push":
                    myQueue.push(values.get(i).get(0));
                    result[i] = null;
                    break;
                case "peek":
                    int peek = myQueue.peek();
                    result[i] = peek;
                    break;
                case "pop":
                    int pop = myQueue.pop();
                    result[i] = pop;
                    break;
                case "empty":
                    boolean empty = myQueue.empty();
                    result[i] = empty;
                    break;
                default:
                    System.out.println(commands.get(i));
                    throw new RuntimeException();
            }
        }

        assertThat(result).isEqualTo(expected);
    }
}
