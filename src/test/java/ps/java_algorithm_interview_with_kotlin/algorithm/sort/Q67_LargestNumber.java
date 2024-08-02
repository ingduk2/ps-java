package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/largest-number/description/
 * - 가장 큰 수
 *
 * 1. 삽입 정렬
 * 2. 문자열 비교 정렬
 */
public class Q67_LargestNumber {
    static class Solution1 {
        public String largestNumber(int[] nums) {
            int i = 1;
            while (i < nums.length) {
                int j = i;
                while (j > 0 && toSwap(nums[j - 1], nums[j])) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    j--;
                }
                i++;
            }

            if (nums[0] == 0) {
                return "0";
            } else {
                return Arrays.toString(nums).replaceAll("\\[|]|,|\\s", "");
            }
        }

        private boolean toSwap(int n1, int n2) {
            BigDecimal num1 = new BigDecimal(String.valueOf(n1) + n2);
            BigDecimal num2 = new BigDecimal(String.valueOf(n2) + n1);
            return num1.compareTo(num2) < 0;
        }
    }

    static class Solution2 {
        public String largestNumber(int[] nums) {
            String[] strNums = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strNums[i] = String.valueOf(nums[i]);
            }

            Arrays.sort(strNums, (o1, o2) -> {
                String num1 = o1 + o2;
                String num2 = o2 + o1;
                return num2.compareTo(num1);
            });

            // result
            if (strNums[0].equals("0")) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            for (String strNum : strNums) {
                sb.append(strNum);
            }

            return sb.toString();
        }
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{10, 2},
                        "210"
                ),
                Arguments.of(
                        new int[]{3, 30, 34, 5, 9},
                        "9534330"
                ),
                Arguments.of(
                        new int[]{1000000000, 1000000000},
                        "10000000001000000000"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, String expected) {
        String result = new Solution1().largestNumber(nums);
        assertThat(result).isEqualTo(expected);

        String result2 = new Solution2().largestNumber(nums);
        assertThat(result2).isEqualTo(expected);
    }
}
