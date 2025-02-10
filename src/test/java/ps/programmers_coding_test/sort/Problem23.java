package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 23 - K번째 수 - Level 1

풀이
- commands[i, j, k] 의 i 번째부터 j 번째까지 subArray
- subArray 정렬
- k 번째의 숫자를 구한다
 */
public class Problem23 {

    static class Solution1 {
        public int[] solution(int[] array, int[][] commands) {
            int[] result = new int[commands.length];

            for (int index = 0; index < commands.length; index++) {
                int i = commands[index][0];
                int j = commands[index][1];
                int k = commands[index][2];

                int[] subArray = getSubArray(array, i, j);
                Arrays.sort(subArray);
                result[index] = subArray[k - 1];
            }

            return result;
        }

        private int[] getSubArray(int[] array, int i, int j) {
            int size = j - i + 1;
            int[] result = new int[size];
            int index = 0;

            for (int k = i - 1; k < j; k++) {
                result[index++] = array[k];
            }

            return result;
        }
    }

    static class Solution2 {
        public int[] solution(int[] array, int[][] commands) {
            int[] result = new int[commands.length];

            for (int i = 0; i < result.length; i++) {
                int[] command = commands[i];
                int from = command[0] - 1;
                int to = command[1];
                int k = command[2] - 1;

                int[] sub = Arrays.copyOfRange(array, from, to);
                Arrays.sort(sub);
                result[i] = sub[k];
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 5, 2, 6, 3, 7, 4},
                        new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}},
                        new int[]{5, 6, 3}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] array, int[][] commands, int[] expected) {
        assertThat(new Solution1().solution(array, commands)).isEqualTo(expected);
        assertThat(new Solution2().solution(array, commands)).isEqualTo(expected);
    }
}
