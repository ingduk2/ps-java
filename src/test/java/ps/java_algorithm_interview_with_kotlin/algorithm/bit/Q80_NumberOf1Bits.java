package ps.java_algorithm_interview_with_kotlin.algorithm.bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * - 1 bit 의 개수
 *
 * 1. 1의 개수 계산
 * 2. 비트 연산
 */
public class Q80_NumberOf1Bits {

    static class Solution1 {
        public int hammingWeight(int n) {
            return Integer.bitCount(n);
        }
    }

    static class Solution2 {
        public int hammingWeight(int n) {
            int count = 0;

            while (n != 0) {
                n &= n - 1;
                count++;
            }

            return count;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        11,
                        3
                ),Arguments.of(
                        128,
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int expected) {
        int result = new Solution1().hammingWeight(n);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().hammingWeight(n);
        assertThat(result2).isEqualTo(expected);
    }
}
