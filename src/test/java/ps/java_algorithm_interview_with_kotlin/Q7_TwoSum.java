package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/two-sum/description/
 * - 같은 element 를 두 번 사용 할 수 없음
 *
 * 1.
 * - bruteForce
 * 2.
 * - 첫 번쨰 수를 뺀 결과 키 조회
 * 3.
 * - 첫 번째 수를 뺀 결과 키 조회를 start, end 로 동시 진행
 */
public class Q7_TwoSum {

    static class BruteForce {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int sum = nums[i] + nums[j];
                    if (sum == target) {
                        return new int[]{i, j};
                    }
                }
            }

            return new int[]{};
        }
    }

    static class DifferenceAsKeyLookup {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numsMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                numsMap.put(nums[i], i);
            }

            for (int i = 0; i < nums.length; i++) {
                int difference = target - nums[i];
                if (numsMap.containsKey(difference) && i != numsMap.get(difference)) {
                    return new int[]{i, numsMap.get(difference)};
                }
            }

            return new int[]{};
        }
    }

    static class DifferenceAsKeyLookupV2 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numsMap = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (numsMap.containsKey(target - nums[i])) {
                    return new int[]{i, numsMap.get(target - nums[i])};
                }

                numsMap.put(nums[i], i);
            }

            return new int[]{};
        }
    }

    static class DifferenceAsKeyLookupStartEnd {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numsMap = new HashMap<>();

            int end = nums.length - 1;
            for (int i = 0; i < nums.length; i++) {
                // start
                int startKey = target - nums[i];
                if (numsMap.containsKey(startKey)) {
                    return new int[]{i, numsMap.get(startKey)};
                }
                numsMap.put(nums[i], i);

                // end
                int endKey = target - nums[end - i];
                if (numsMap.containsKey(endKey)) {
                    return new int[]{numsMap.get(endKey), end - i};
                }
                numsMap.put(nums[end - i], end - i);
            }

            return new int[]{};
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int target, int[] expected) {
        // BruteForce
        int[] result = new BruteForce().twoSum(nums, target);
        assertThat(result).contains(expected);

        // DifferenceAsKeyLookup
        int[] result2 = new DifferenceAsKeyLookup().twoSum(nums, target);
        assertThat(result2).contains(expected);

        // DifferenceAsKeyLookupV2
        int[] result3 = new DifferenceAsKeyLookupV2().twoSum(nums, target);
        assertThat(result3).contains(expected);

        // DifferenceAsKeyLookupStartEnd
        int[] result4 = new DifferenceAsKeyLookupStartEnd().twoSum(nums, target);
        assertThat(result4).contains(expected);
    }
}
