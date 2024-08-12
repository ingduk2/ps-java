package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 * - 2D 행렬 검색 II
 * -- row, col 은 ascending 정렬
 *
 * 1. 첫 행의 맨 뒤에서 탐색
 * - target 이 첫 행의 맨 뒤 값보다 작으면 왼쪽, 값이 크면 아래로 이동
 */
public class Q74_SearchA2DMatrixII {
    static class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int row = 0;
            int col = matrix[0].length - 1;

            while (row < matrix.length && col >= 0) {
                int value = matrix[row][col];
                if (target < value) {
                    col--;
                } else if (target > value) {
                    row++;
                } else {
                    return true;
                }
            }

            return false;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1,4,7,11,15},
                                {2,5,8,12,19},
                                {3,6,9,16,22},
                                {10,13,14,17,25},
                                {18,21,23,26,30}
                        },
                        5,
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {1,4,7,11,15},
                                {2,5,8,12,19},
                                {3,6,9,16,22},
                                {10,13,14,17,24},
                                {18,21,23,26,30},
                        },
                        20,
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] matrix, int target, boolean expected) {
        boolean result = new Solution1().searchMatrix(matrix, target);
        assertThat(result).isEqualTo(expected);
    }
}
