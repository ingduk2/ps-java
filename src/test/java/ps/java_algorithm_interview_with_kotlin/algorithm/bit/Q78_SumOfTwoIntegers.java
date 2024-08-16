package ps.java_algorithm_interview_with_kotlin.algorithm.bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sum-of-two-integers/description/
 * - 두 정수의 합
 *
 * 1. 전가산기 구현
 * 2. 좀 더 간소한 구현
 */
public class Q78_SumOfTwoIntegers {

    static class Solution1 {
        public int getSum(int a, int b) {
            String binA = String.format("%32s", Integer.toBinaryString(a)).replace(' ', '0');
            String binB = String.format("%32s", Integer.toBinaryString(b)).replace(' ', '0');

            List<Character> result = new ArrayList<>();
            int carry = 0;
            int sum;

            for (int i = 0; i < 32; i++) {
                int inputA = Character.getNumericValue(binA.charAt(31 - i));
                int inputB = Character.getNumericValue(binB.charAt(31 - i));

                int q1 = inputA & inputB;
                int q2 = inputA ^ inputB;
                int q3 = q2 & carry;
                sum = carry ^ q2;
                carry = q1 | q3;

                result.add(0, Character.forDigit(sum, 2));
            }

            String collect = result.stream().map(String::valueOf).collect(Collectors.joining(""));
            return Integer.parseUnsignedInt(collect, 2);
        }
    }

    static class Solution2 {
        public int getSum(int a, int b) {
            while (b != 0) {
                int carry = (a & b) << 1;
                a = a ^ b;
                b = carry;
            }

            return a;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        1, 2,
                        3
                ),Arguments.of(
                        2, 3,
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int a, int b, int expected) {
        int result = new Solution1().getSum(a, b);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().getSum(a, b);
        assertThat(result2).isEqualTo(expected);
    }
}
