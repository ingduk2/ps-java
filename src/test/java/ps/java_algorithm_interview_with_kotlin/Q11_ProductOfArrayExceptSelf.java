package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/product-of-array-except-self/description/
 * - o(n) division operation 없이
 * - 자신을 빼고 나머지의 곱
 *
 * 1. 왼쪽 곱셈 결과에 오른쪽 값을 차례대로 곱하기
 * -  1, 2, 3, 4 (input)
 * -> 1, 1, 2, 6 (왼쪽 곱셈)
 *    24,12,4, 1 <- (오른쪽 곱셈)
 *
 */
public class Q11_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int p = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = p;
            p *= nums[i];
        }

        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= p;
            p *= nums[i];
        }

        return result;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1,2,3,4},
                        new int[]{24,12,8,6}
                ),
                Arguments.of(
                        new int[]{-1,1,0,-3,3},
                        new int[]{0,0,9,0,0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] input, int[] expected) {
        int[] result = new Q11_ProductOfArrayExceptSelf().productExceptSelf(input);
        assertThat(result).containsExactly(expected);
    }
}
