package ps.java_algorithm_interview_with_kotlin.algorithm.divideandconquer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/majority-element/description/
 * - n / 2 초과로 나오는 element 를 구하라
 *
 * - Solution1
 * -- Map 으로 카운팅
 *
 * - Solution2
 * -- 분할정복
 *
 * - Solution3
 * -- 과반수가 넘는 엘리먼트라면, 정렬을 하고 가운데를 지정하면 반드시 그 값이다
 */
public class Q89_MajorityElement {

    static class Solution1 {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : nums) {
                int count = countMap.getOrDefault(num, 0) + 1;
                countMap.put(num, count);
            }

            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() > nums.length / 2) {
                    return entry.getKey();
                }
            }

            return -1;
        }
    }

    static class Solution2 {
        public int majorityElement(int[] nums) {
            return majorityElement(0, nums.length - 1, nums);
        }

        private int majorityElement(int left, int right, int[] nums) {
            if (left == right) {
                return nums[left];
            }

            int mid = left + (right - left) / 2;
            int a = majorityElement(left, mid, nums);
            int b = majorityElement(mid + 1, right, nums);

            int countA = 0;

            for (int i = left; i <= right; i++) {
                if (nums[i] == a) {
                    countA++;
                }
            }

            return countA > (right - left + 1) / 2 ? a : b;
        }
    }

    static class Solution3 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 3}, 3),
                Arguments.of(new int[]{2, 2, 1, 1, 1, 2, 2}, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int expected) {
        int result = new Solution1().majorityElement(nums);
        assertThat(result).isEqualTo(expected);

        result = new Solution2().majorityElement(nums);
        assertThat(result).isEqualTo(expected);

        result = new Solution3().majorityElement(nums);
        assertThat(result).isEqualTo(expected);
    }
}
