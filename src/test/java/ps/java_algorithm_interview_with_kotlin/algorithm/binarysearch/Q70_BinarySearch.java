package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-search/description/
 *
 * 1. 재귀 풀이
 * 2. 중앙의 위치 계산 개선
 * 3. 반복 풀이
 * 4. 이진 검색 메소드
 */
public class Q70_BinarySearch {

    static class Solution1 {
        public int search(int[] nums, int target) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        private int binarySearch(int[] nums, int target, int left, int right) {
            if (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] < target) {
                    return binarySearch(nums, target, mid + 1, right);
                } else if (nums[mid] > target) {
                    return binarySearch(nums, target, left, mid - 1);
                } else {
                    return mid;
                }
            } else {
                return -1;
            }
        }
    }

    static class Solution2 {
        public int search(int[] nums, int target) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        private int binarySearch(int[] nums, int target, int left, int right) {
            if (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    return binarySearch(nums, target, mid + 1, right);
                } else if (nums[mid] > target) {
                    return binarySearch(nums, target, left, mid - 1);
                } else {
                    return mid;
                }
            } else {
                return -1;
            }
        }
    }

    static class Solution3 {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }

            return -1;
        }
    }

    static class Solution4 {
        public int search(int[] nums, int target) {
            int index = Arrays.binarySearch(nums, target);

            if (index < 0) {
                return -1;
            }

            return index;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{-1,0,3,5,9,12}, 9,
                        4
                ),
                Arguments.of(
                        new int[]{-1,0,3,5,9,12}, 2,
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int target, int expected) {
        int result1 = new Solution1().search(nums, target);
        assertThat(result1).isEqualTo(expected);

        int result2 = new Solution2().search(nums, target);
        assertThat(result2).isEqualTo(expected);

        int result3 = new Solution3().search(nums, target);
        assertThat(result3).isEqualTo(expected);

        int result4 = new Solution4().search(nums, target);
        assertThat(result4).isEqualTo(expected);
    }
}
