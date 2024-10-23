package ps.java_algorithm_interview_with_kotlin.algorithm.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
 * - 여러 번의 거래로 낼 수 있는 최대 이익을 산출하라
 *
 * - 오르기 전에 사고, 내리기 전에 팔자 -> prices[i + 1] - prices[i] > 0
 */
public class Q84_BestTimeToBuyAndSellStock2 {

    static class Solution1 {
        public int maxProfit(int[] prices) {
            int result = 0;

            for (int i = 0; i < prices.length - 1; i++) {
                int diff = prices[i + 1] - prices[i];

                if (diff > 0) {
                    result += diff;
                }
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{7, 1, 5, 3, 6, 4},
                        7
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5},
                        4
                ),
                Arguments.of(
                        new int[]{7, 6, 4, 3, 1},
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] prices, int expected) {
        int result = new Solution1().maxProfit(prices);
        assertThat(result).isEqualTo(expected);
    }
}
