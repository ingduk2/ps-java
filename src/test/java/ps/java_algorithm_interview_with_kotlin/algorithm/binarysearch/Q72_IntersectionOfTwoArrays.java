package ps.java_algorithm_interview_with_kotlin.algorithm.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 * - 두 배열의 교집합
 *
 * 1. 브루트 포스 계산
 * 2. 이진검색으로 일치 여부 판별
 * 3. 투 포인터로 일치 여부 판결
 */
public class Q72_IntersectionOfTwoArrays {

    static class Solution1 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> result = new HashSet<>();

            for (int n1 : nums1) {
                for (int n2 : nums2) {
                    if (n1 == n2) {
                        result.add(n1);
                    }
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Solution2 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> result = new HashSet<>();

            Arrays.sort(nums2);
            for (int n1 : nums1) {
                int value = Arrays.binarySearch(nums2, n1);
                if (value >= 0) {
                    result.add(n1);
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Solution3 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> result = new HashSet<>();

            Arrays.sort(nums1);
            Arrays.sort(nums2);

            int i = 0;
            int j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] > nums2[j]) {
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    result.add(nums1[i]);
                    i++;
                    j++;
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1,2,2,1}, new int[]{2,2},
                        new int[]{2}
                ),
                Arguments.of(
                        new int[]{4,9,5}, new int[]{9,4,9,8,4},
                        new int[]{9,4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums1, int[] nums2, int[] expected) {
        int[] result = new Solution1().intersection(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertThat(result).isEqualTo(expected);

        int[] result2 = new Solution2().intersection(nums1, nums2);
        Arrays.sort(result2);
        Arrays.sort(expected);
        assertThat(result2).isEqualTo(expected);

        int[] result3 = new Solution3().intersection(nums1, nums2);
        Arrays.sort(result3);
        Arrays.sort(expected);
        assertThat(result3).isEqualTo(expected);
    }
}
