package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/array-partition/description/
 * - 2n integers
 * - n pairs (4 -> 2), (6 -> 3) (a1,b1)(a2,b2)...(an,bn)
 * - pair 의 min 값이 합이 최대인 값
 *
 * 1. 오름차순 정렬을 한 후에 pair 는 최대값
 * 1, 2, 3, 4 -> (1, 2) (3, 4)
 * 1, 2, 2, 5, 6, 6 -> (1, 2) (2, 5) (6, 6)
 *
 * 2. pair 중 min 값만 사용 하므로 홀수번째만 sum
 *
 */
public class Q10_ArrayPartition {

    static class Solve1 {
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);

            int result = 0;
            for (int i = 0; i < nums.length - 1; i += 2) {
                result += Math.min(nums[i], nums[i + 1]);
            }

            return result;
        }
    }

    static class Solve2 {
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);

            int result = 0;
            for (int i = 0; i < nums.length - 1; i += 2) {
                result += nums[i];
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1,4,3,2}, 4),
                Arguments.of(new int[]{6,2,6,5,1,2}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] input, int expected) {
        int result = new Solve1().arrayPairSum(input);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solve2().arrayPairSum(input);
        assertThat(result2).isEqualTo(expected);
    }
}
