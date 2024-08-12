package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 * - 두 수의 합 II
 *
 * 1. 투 포인터
 * 2. 이진 검색
 * 3. 이진 검색 메소드 사용
 */
public class Q73_TwoSumIIInputArrayIsSorted {

    static class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[left] + numbers[right];

                if (sum < target) {
                    left += 1;
                } else if (sum > target) {
                    right -= 1;
                } else {
                    return new int[]{left + 1, right + 1};
                }
            }

            return null;
        }
    }

    static class Solution2 {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int left = i + 1;
                int right = numbers.length - 1;
                int expected = target - numbers[i];

                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (numbers[mid] < expected) {
                        left = mid + 1;
                    } else if (numbers[mid] > expected) {
                        right = mid - 1;
                    } else {
                        return new int[]{i + 1, mid + 1};
                    }
                }
            }

            return null;
        }
    }

    static class Solution3 {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int expected = target - numbers[i];
                int index = Arrays.binarySearch(numbers, i + 1, numbers.length, expected);
                if (index >= 0) {
                    return new int[]{i + 1, index + 1};
                }
            }

            return null;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{2,7,11,15}, 9,
                        new int[]{1,2}
                ),
                Arguments.of(
                        new int[]{2,3,4}, 6,
                        new int[]{1,3}
                ),
                Arguments.of(
                        new int[]{-1,0}, -1,
                        new int[]{1,2}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] numbers, int target, int[] expected) {
        int[] result = new Solution1().twoSum(numbers, target);
        assertThat(result).isEqualTo(expected);

        int[] result2 = new Solution2().twoSum(numbers, target);
        assertThat(result2).isEqualTo(expected);

        int[] result3 = new Solution3().twoSum(numbers, target);
        assertThat(result3).isEqualTo(expected);
    }
}
