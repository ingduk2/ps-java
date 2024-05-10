package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/3sum/description/
 * - 세 수의 합 = 0
 * - i != j, i != k, j != k
 * - 중복 숫자는 X
 * 1. BruteForce
 * - 중복 처리 (정렬을 했기 떄문에 현재값 != 이전값)
 * - 느리다
 *
 * 2. TwoPointer
 * - 1개를 정하고, 2개는 투포인터로 합 0 계산
 * - 합이 0인 경우 중복 처리 필요
 */
public class Q9_3Sum {

    static class BruteForce {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                for (int j = i + 1; j < nums.length - 1; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    for (int k = j + 1; k < nums.length; k++) {
                        if (k > j + 1 && nums[k] == nums[k - 1]) continue;
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }

            return result;
        }
    }

    static class TwoPointer {
        public List<List<Integer>> threeSum(int[] nums) {
            int left, right, sum;
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                left = i + 1;
                right = nums.length - 1;

                while (left < right) {
                    sum = nums[i] + nums[left] + nums[right];
                    if (sum < 0) {
                        left++;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    }
                }
            }
            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{-1,0,1,2,-1,-4},
                        List.of(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1))
                ),
                Arguments.of(
                        new int[]{0,1,1},
                        List.of()
                ),
                Arguments.of(
                        new int[]{0,0,0},
                        List.of(Arrays.asList(0,0,0))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] input, List<List<Integer>> expected) {
        // BruteForce
        List<List<Integer>> result = new BruteForce().threeSum(input);
        assertList(result, expected);

        // TwoPointer
        List<List<Integer>> result2 = new TwoPointer().threeSum(input);
        assertList(result2, expected);
    }

    private void assertList(List<List<Integer>> result, List<List<Integer>> expected) {
        List<List<Integer>> sortedExpected = expected.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();

        List<List<Integer>> sortedResult = result.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();
        assertThat(sortedResult).containsExactlyElementsOf(sortedExpected);
    }
}
