package ps.become_a_successful_coding_test_candidate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 06 실패율

실패율은 다음과 같이 정의한다.
    스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
실패율이 높은 스테이지부터 내림차순으로 스테이지 번호

제한사항
    스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
    stages 의 길이는 1 이상 200,000 이하이다.
    stages 에는 1 이상 N + 1 이하의 자연수가 담겨있다.
        각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
        단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
    만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
    스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.

풀이
- 각 스테이지별 도전자 수를 구함
- 스테이지별 실패한 사용자 수 계산
- 각 스테이지를 순회하며, 실패율 계산
 */
public class Problem6 {

    static class Solution1 {
        public int[] solution(int N, int[] stages) {
            int[] challenger = new int[N + 2];
            for (int stage : stages) {
                challenger[stage] += 1;
            }

            Map<Integer, Double> fails = new HashMap<>();
            double total = stages.length;

            for (int i = 1; i <= N; i++) {
                if (challenger[i] == 0) {
                    fails.put(i, 0d);
                } else {
                    fails.put(i, challenger[i] / total);
                    total -= challenger[i];
                }
            }

            return fails.entrySet().stream()
                    .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                    .mapToInt(Map.Entry::getKey)
                    .toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        5, new int[]{2, 1, 2, 6, 2, 4, 3, 3},
                        new int[]{3, 4, 2, 1, 5}
                ),
                Arguments.of(
                        4, new int[]{4, 4, 4, 4, 4},
                        new int[]{4, 1, 2, 3}
                ),
                Arguments.of(
                        4, new int[]{1, 1, 2, 4},
                        new int[]{4, 1, 2, 3}
                ),
                Arguments.of(
                        5, new int[]{1, 2, 3},
                        new int[]{3, 2, 1, 4, 5}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int N, int[] stages, int[] expected) {
        int[] result = new Solution1().solution(N, stages.clone());
        assertThat(result).isEqualTo(expected);
    }
}
