package ps.programmers_coding_test.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 31 입국심사 - Level 3

풀이
- 각 심사대가 시간 t 동안 처리할 수 있는 입국 심사 개수를 모두 더하여, 이를 기다리는 사람수와 비교
- 가장 작은 값은 입국 심사를 기다리는 사람 1명, 심사 1분 -> 총 1분
- 가장 큰 값은 입국 심사 기다리는 사람 1,000,000,000, 심사 1,000,000,000 -> 1,000,000,000,000,000,000 분
- 조건을 만족하는 값 중 가장 작은 값을 찾는 이진 탐색
 */
public class Problem31 {

    static class Solution1 {
        public long solution(int n, int[] times) {
            long start = 0;
            long end = 1000000000000000000L;

            while (end > start) {
                long t = (start + end) / 2;
                if (isValid(t, n, times)) {
                    end = t;
                } else {
                    start = t + 1;
                }
            }

            return start;
        }

        private boolean isValid(long t, int n, int[] times) {
            long c = 0;
            for (int time : times) {
                c += t / time; // 각 심사관이 t 시간 동안 심사할 수 있는 사람 수 계산
            }
            return c >= n; // 심사받은 사람이 n 명 이상이면 true
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(6, new int[]{7, 10}, 28)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[] times, long expected) {
        assertThat(new Solution1().solution(n, times)).isEqualTo(expected);
    }
}
