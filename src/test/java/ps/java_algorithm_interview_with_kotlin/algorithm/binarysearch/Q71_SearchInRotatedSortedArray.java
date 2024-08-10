package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * - 회전 정렬된 배열 검색
 *
 * 1. 피벗을 기준으로 한 이진 검색
 * - pivot : 최소값 index
 * -
 */
public class Q71_SearchInRotatedSortedArray {

    static class Solution1 {
        public int search(int[] nums, int target) {
            int pivot = getPivot(nums);
            var optionalTargetIndex = getTargetIndex(nums, target, pivot);

            return optionalTargetIndex.orElse(-1);
        }

        private Optional<Integer> getTargetIndex(int[] nums, int target, int pivot) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPivot = (mid + pivot) % nums.length;

                if (nums[midPivot] < target) {
                    left = mid + 1;
                } else if (nums[midPivot] > target) {
                    right = mid - 1;
                } else {
                    return Optional.of(midPivot);
                }
            }
            return Optional.empty();
        }

        private int getPivot(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 중앙 값이 right 보다 크면 left 포인터 이동
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                }
                // 반의 경우에는 오른쪽 포인터 이동
                else {
                    right = mid;
                }
            }

            return left;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{4,5,6,7,0,1,2}, 0,
                        4
                ),
                Arguments.of(
                        new int[]{4,5,6,7,0,1,2}, 3,
                        -1
                ),
                Arguments.of(
                        new int[]{1}, 0,
                        -1
                ),
                Arguments.of(
                        new int[]{3,4,5,6,0,1,2}, 1,
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int target, int expected) {
        int result = new Solution1().search(nums, target);
        assertThat(result).isEqualTo(expected);
    }
}
