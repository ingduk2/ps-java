package ps.java_algorithm_interview_with_kotlin.nonlinear.heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628?language=java
 * - 이중 우선순위 큐
 *
 * 1. 이중 구조 방식
 * 2. 인터벌 힙
 */
public class Q61 {
    static class Solution1 {
        public int[] solution(String[] operations) {
            Queue<Integer> minHeap = new PriorityQueue<>();
            Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (String operation : operations) {
                String[] op = operation.split(" ");
                String command = op[0];
                int value = Integer.parseInt(op[1]);

                switch (command) {
                    case "I" -> {
                        minHeap.add(value);
                        maxHeap.add(value);
                    }
                    case "D" -> {
                        if (value == 1) {
                            Integer max = maxHeap.poll();
                            minHeap.remove(max);
                        }
                        if (value == -1) {
                            Integer min = minHeap.poll();
                            maxHeap.remove(min);
                        }
                    }
                }
            }

            Integer min = minHeap.poll();
            Integer max = maxHeap.poll();

            return new int[]{
                    max == null ? 0 : max,
                    min == null ? 0 : min
            };
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                        new int[]{0, 0}
                ),
                Arguments.of(
                        new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"},
                        new int[]{333, -45}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] operations, int[] expected) {
        int[] result = new Solution1().solution(operations);
        assertThat(result).isEqualTo(expected);
    }
}
