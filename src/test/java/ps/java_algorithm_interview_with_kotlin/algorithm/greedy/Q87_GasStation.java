package ps.java_algorithm_interview_with_kotlin.algorithm.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/gas-station
 * - 시계 방향으로 순회가 가능한지
 * - 출발점은 하나다
 * - gas = [5, 1, 2, 3, 4] | cost = [4, 4, 1, 5, 1]
 *
 * - Solution1 - 브루트 포스
 * -- idx tank gas cost
 * -- 4 -> 0 + 4 - 1 = 3
 * -- 0 -> 3 + 5 - 4 = 4
 * -- 1 -> 4 + 1 - 4 = 1
 * -- 2 -> 1 + 2 - 1 = 2
 * -- 3 -> 2 + 3 - 5 = 0
 * -- tank + gas - cost < 0 이면 실패
 * -- tank = tank + gas - cost
 * -- O(n^2)
 *
 * - Solution 2
 * -- 전체 기름의 양이 전체 비용보다 크다면 전체를 방문할 수 있는 출발점이 반드시 존재
 * -- 가스의 합 < 비용의 합 -> -1
 * -- 반드시 존재하는 경우만 남아 있게 된다
 * -- 출발을 할 수 있으면 정답
 */
public class Q87_GasStation {

    static class Solution1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int length = gas.length;

            for (int i = 0; i < length; i++) {
                if (isTravel(gas, cost, i)) {
                    return i;
                }
            }

            return -1;
        }

        private boolean isTravel(int[] gas, int[] cost, int start) {
            int length = gas.length;
            int tank = 0;

            for (int i = start; i < length + start; i++) {
                int index = i % length;

                if (tank + gas[index] - cost[index] < 0) {
                    return false;
                } else {
                    tank = tank + gas[index] - cost[index];
                }
            }

            return true;
        }
    }

    static class Solution2 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
                return -1;
            }

            int start = 0;
            int fuel = 0;

            for (int i = 0; i < gas.length; i++) {
                // 전체 주유소를 순회하면서 성립되지 않는 위치를 찾는다.
                // 남은 기름으로 출발점이 안 되는 주유소가 있다면 이미 지나친 지점도 전부 출발점이 될 수 없으므로
                // 출발점을 다음 지점으로 밀어낸다
                if (fuel + gas[i] - cost[i] < 0) {
                    start = i + 1;
                    fuel = 0;
                } else {
                    // 남은 기름을 계속 누적
                    fuel += gas[i] - cost[i];
                }
            }

            return start;
        }
    }

    static class Solution3 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int total_tank = 0;    // 전체 경로에서의 연료 상태
            int current_tank = 0;  // 현재 구간에서의 연료 상태
            int start_station = 0; // 시작 지점 후보

            for (int i = 0; i < gas.length; i++) {
                // 각 지점에서의 연료 상태를 업데이트
                total_tank += gas[i] - cost[i];
                current_tank += gas[i] - cost[i];

                // 현재 구간에서 연료가 부족한 경우
                if (current_tank < 0) {
                    // 현재 지점에서 출발할 수 없으므로 다음 지점을 출발점 후보로 변경
                    start_station = i + 1;
                    current_tank = 0; // 새로운 출발점에서는 연료를 초기화
                }
            }

            // 전체 경로에서 연료가 충분하다면 시작 지점 반환, 아니면 -1 반환
            return total_tank >= 0 ? start_station : -1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2},
                        3
                ),
                Arguments.of(
                        new int[]{2, 3, 4}, new int[]{3, 4, 3},
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] gas, int[] cost, int expected) {
        int result = new Solution1().canCompleteCircuit(gas, cost);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().canCompleteCircuit(gas, cost);
        assertThat(result2).isEqualTo(expected);

        int result3 = new Solution3().canCompleteCircuit(gas, cost);
        assertThat(result3).isEqualTo(expected);
    }
}
