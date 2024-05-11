package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * - i th day 의 price
 * - 이익을 최대화 할 수 있는 값
 * - 날짜 순서 대로 사고 팔 수 있다
 *
 * 차가 가장 큰 경우
 * 1. BruteForce - Timeout
 * 2. 저점과 현재 값과의 차이 계산
 */
public class Q12_BestTimeToBuyAndSellStock {

    static class BruteForce {
        public int maxProfit(int[] prices) {
            int result = 0;

            for (int i = 0; i < prices.length - 1; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int buy = prices[i];
                    int sell = prices[j];
                    if (buy < sell) {
                        result = Math.max(result, sell - buy);
                    }
                }
            }

            return result;
        }
    }

    static class Solve2 {
        public int maxProfit(int[] prices) {
            int result = 0;
            int minPrice = prices[0];

            for (int price : prices) {
                minPrice = Math.min(minPrice, price);
                result = Math.max(result, price - minPrice);
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{7,1,5,3,6,4},
                        5
                ),
                Arguments.of(
                        new int[]{7,6,4,3,1},
                        0
                ),
                Arguments.of(
                        new int[]{1,4,2},
                        3
                )
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] input, int expected) {
        // BruteForce
        int result = new BruteForce().maxProfit(input);
        assertThat(result).isEqualTo(expected);

        // TwoPointer
        int result2 = new Solve2().maxProfit(input);
        assertThat(result2).isEqualTo(expected);
    }
}
