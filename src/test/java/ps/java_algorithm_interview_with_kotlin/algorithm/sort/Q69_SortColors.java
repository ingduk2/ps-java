package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sort-colors/description/
 * - 색 정렬
 *
 * 1. 네덜란드 국기 문제를 응용한 풀이
 * - red, white, blue [0, 1, 2]
 */
public class Q69_SortColors {
    static class Solution1 {
        public void sortColors(int[] nums) {
            int red = 0;
            int white = 0;
            int blue = nums.length;

            while (white < blue) {
                if (nums[white] < 1) {
                    swap(nums, red, white);

                    red++;
                    white++;
                } else if (nums[white] > 1) {
                    blue--;

                    swap(nums, white, blue);
                } else {
                    white++;
                }
            }
        }

        private void swap(int[] nums, int idxA, int idxB) {
            int temp = nums[idxA];
            nums[idxA] = nums[idxB];
            nums[idxB] = temp;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{2,0,2,1,1,0},
                        new int[]{0,0,1,1,2,2}
                ),
                Arguments.of(
                        new int[]{2,0,1},
                        new int[]{0,1,2}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int[] expected) {
        new Solution1().sortColors(nums);
        assertThat(nums).isEqualTo(expected);
    }
}
