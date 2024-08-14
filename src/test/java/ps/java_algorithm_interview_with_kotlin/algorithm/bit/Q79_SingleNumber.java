package ps.java_algorithm_interview_with_kotlin.algorithm.bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/single-number/description/
 * - 싱글 넘버
 *
 * 1. Map 사용 카운팅
 * 2. XOR 풀이
 * - xor 은 값이 다르면 true, 같으면 false
 * - 두 번 등장하면 0이 된다
 */
public class Q79_SingleNumber {

    static class Solution1 {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : nums) {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> countEntry : countMap.entrySet()) {
                int num = countEntry.getKey();
                int count = countEntry.getValue();
                if (count == 1) {
                    return num;
                }
            }

            return -1;
        }
    }

    static class Solution2 {
        public int singleNumber(int[] nums) {
            int result = 0;

            for (int num : nums) {
                result ^= num;
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{2,2,1},
                        1
                ),Arguments.of(
                        new int[]{4,1,2,1,2},
                        4
                ),Arguments.of(
                        new int[]{1},
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int expected) {
        int result = new Solution1().singleNumber(nums);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().singleNumber(nums);
        assertThat(result2).isEqualTo(expected);
    }
}
