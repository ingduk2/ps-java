package ps.programmers_coding_test.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 4 행렬의 곱셈 - Level 2

풀이
- (i * k) * (k * j)
 */
public class Problem4 {

    static class Solution1 {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            int[][] answer = new int[arr1.length][arr2[0].length];

            for (int i = 0; i < answer.length; i++) {
                for (int j = 0; j < answer[0].length; j++) {
                    for (int k = 0; k < arr1[0].length; k++) {
                        answer[i][j] += arr1[i][k] * arr2[k][j];
                    }
                }
            }

            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 4},
                                {3, 2},
                                {4, 1}
                        },
                        new int[][]{
                                {3, 3},
                                {3, 3}
                        },
                        new int[][]{
                                {15, 15},
                                {15, 15},
                                {15, 15}
                        }
                ),
                Arguments.of(
                        new int[][]{
                                {2, 3, 2},
                                {4, 2, 4},
                                {3, 1, 4}
                        },
                        new int[][]{
                                {5, 4, 3},
                                {2, 4, 1},
                                {3, 1, 1}
                        },
                        new int[][]{
                                {22, 22, 11},
                                {36, 28, 18},
                                {29, 20, 14}
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] arr1, int[][] arr2, int[][] expected) {
        assertThat(new Solution1().solution(arr1, arr2)).isEqualTo(expected);
    }
}
