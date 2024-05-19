package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/daily-temperatures/description/
 * - 몇 일 후에 더 더워지는지
 *
 * 1. BruteForce - Timeout
 * 2. Stack
 * - 현재의 인덱스를 스택에 쌓아두다가, 이전보다 상승하는 지점에서 현재 온도와 스택의 peek 를 비교
 * - 높은 경우에, popIndex 위치에 (현재 인덱스 - pop Index) 를 저장
 */
public class Q22_DailyTemperatures {

    static class BruteForceSolve {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] result = new int[temperatures.length];
            for (int i = 0; i < temperatures.length - 1; i++) {
                for (int j = i; j < temperatures.length; j++) {
                    if (temperatures[i] < temperatures[j]) {
                        result[i] = j - i;
                        break;
                    }
                }
            }

            return result;
        }
    }

    static class StackSolve {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] result = new int[temperatures.length];
            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int last = stack.pop();
                    result[last] = i - last;
                }
                stack.push(i);
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{73,74,75,71,69,72,76,73},
                        new int[]{1,1,4,2,1,1,0,0}
                ),
                Arguments.of(
                        new int[]{30,40,50,60},
                        new int[]{1,1,1,0}
                ),
                Arguments.of(
                        new int[]{30,60,90},
                        new int[]{1,1,0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] temperatures, int[] expected) {
        // BruteForce
        int[] result = new BruteForceSolve().dailyTemperatures(temperatures);
        assertThat(result).isEqualTo(expected);

        // Stack
        int[] result2 = new StackSolve().dailyTemperatures(temperatures);
        assertThat(result2).isEqualTo(expected);
    }
}
