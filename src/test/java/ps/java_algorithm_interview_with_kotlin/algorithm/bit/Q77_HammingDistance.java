package ps.java_algorithm_interview_with_kotlin.algorithm.bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/hamming-distance/
 * - 해밍 거리
 *
 * 1. XOR 풀이
 */
public class Q77_HammingDistance {

    static class Solution1 {
        public int hammingDistance(int x, int y) {
            int xor = x ^ y;
            return Integer.bitCount(xor);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        1, 4,
                        2
                ),Arguments.of(
                        3, 1,
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int x, int y, int expected) {
        int result = new Solution1().hammingDistance(x, y);
        assertThat(result).isEqualTo(expected);
    }
}
