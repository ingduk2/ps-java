package ps.become_a_successful_coding_test_candidate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 5 행렬의 곱셈
https://school.programmers.co.kr/learn/courses/30/lessons/12949

두 행렬 곱한 결과를 반환하는 함수 작성

제한 조건
    행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
    행렬 arr1, arr2의 원소는 -10 이상 20 이하인 자연수입니다.
    곱할 수 있는 배열만 주어집니다.

풀이
- 행렬은 m * n | n * p -> m * p 행렬이 나옴
- 가운데가 같아야 곱셈이 가능
 */
public class Problem5 {

    static class Solution1 {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            int m = arr1.length;
            int n = arr1[0].length;
            int p = arr2[0].length;

            int[][] result = new int[m][p];

            for (int i = 0; i < m; i++) { // arr1 의 행 수
                for (int j = 0; j < p; j++) { // arr2 의 행 수
                    for (int k = 0; k < n; k++) { // arr1 의 열 수 / arr2 의 행 수
                        result[i][j] += arr1[i][k] * arr2[k][j];
                    }
                }
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 4},
                                {3, 2},
                                {4, 1}},
                        new int[][]{
                                {3, 3},
                                {3, 3}},
                        new int[][]{
                                {15, 15},
                                {15, 15},
                                {15, 15}}
                ),
                Arguments.of(
                        new int[][]{
                                {2, 3, 2},
                                {4, 2, 4},
                                {3, 1, 4}},
                        new int[][]{
                                {5, 4, 3},
                                {2, 4, 1},
                                {3, 1, 1}},
                        new int[][]{
                                {22, 22, 11},
                                {36, 28, 18},
                                {29, 20, 14}}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] arr1, int[][] arr2, int[][] expected) {
        int[][] result = new Solution1().solution(arr1, arr2);
        assertThat(result).isEqualTo(expected);
    }
}
