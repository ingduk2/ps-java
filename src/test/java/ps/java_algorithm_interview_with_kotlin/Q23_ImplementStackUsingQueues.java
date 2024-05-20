package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/description/
 * - 2개의 queue 를 사용하여 Stack 을 구현
 * - queue 는 FIFO, Stack 은 LIFO
 * - 1,2,3,4,5 (push) -> 5,4,3,2,1 (pop)
 * - queue 를 빼서 다른 queue 에 넣으면 뒤집어진다
 *
 * - queue1(주), queue2(보조)
 * 1. push 할 때 queue2 에 add
 * 2. queue1 에 값이 있다면 queue2 에 add
 * 3. 마지막에 queue1, queue2 swap
 * - 그러면 후에 push 된 것이 맨 앞에 위치, 이전에 있던 것들은 뒤로 가게 된다
 *
 * -- push 1
 *   - queue1, queue2
 *   - []      [1]      push 1
 *   - [1]     []       swap
 * -- push 2
 *   - queue1, queue2
 *   - [1]     [2]      push 2
 *   -         [2] [1]  queue1 poll -> queue2 add
 *   - [2] [1] []       swap
 */
public class Q23_ImplementStackUsingQueues {
    static class MyStack {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue2.add(x);

            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }

            // queue1 queue2 swap
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of("MyStack", "push", "push", "top", "pop", "empty"),
                        List.of(List.of(), List.of(1), List.of(2), List.of(), List.of()),
                        new Object[]{null, null, null, 2, 2, false}
                ),
                Arguments.of(
                        List.of("MyStack", "push", "push", "push", "push", "pop", "pop", "pop", "pop", "empty"),
                        List.of(List.of(), List.of(1), List.of(2), List.of(3), List.of(4), List.of(), List.of(), List.of(), List.of(), List.of()),
                        new Object[]{null, null, null, null, null, 4, 3, 2, 1, true}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<String> commands, List<List<Integer>> values, Object[] expected) {
        MyStack myStack = null;
        Object[] result = new Object[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "MyStack":
                    myStack = new MyStack();
                    result[i] = null;
                    break;
                case "push":
                    myStack.push(values.get(i).get(0));
                    result[i] = null;
                    break;
                case "top":
                    int top = myStack.top();
                    result[i] = top;
                    break;
                case "pop":
                    int pop = myStack.pop();
                    result[i] = pop;
                    break;
                case "empty":
                    boolean empty = myStack.empty();
                    result[i] = empty;
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        assertThat(result).isEqualTo(expected);
    }
}
