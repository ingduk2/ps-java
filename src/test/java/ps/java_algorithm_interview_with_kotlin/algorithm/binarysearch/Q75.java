package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * - 입국 심사
 * 1. 이진 검색
 */
public class Q75 {
    static class Solution1 {
        public long solution(int n, int[] times) {
            long answer = 0;

            long left = 0;
            long right = (long) Arrays.stream(times).max().getAsInt() * n;
            long mid = right;

            while (left <= right) {
                long calcN = 0;
                for (int time : times) {
                    calcN += mid / time;
                }

                if (calcN >= n) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                mid = left + (right - left) / 2;
            }

            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        6, new int[]{7,10},
                        28
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[] times, long expected) {
        long result = new Solution1().solution(n, times);
        assertThat(result).isEqualTo(expected);
    }
}
