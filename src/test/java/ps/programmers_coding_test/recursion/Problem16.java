package ps.programmers_coding_test.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 16 - 하노이의 탑 - Level 3

풀이
- 상태
  - (n, from, to) n 개의 원반을 기둥 from 에서 기둥 to 로 옮기는 과정
- 종료 조건
  - (1, from, to) = [[from, to]]
- 점화식
  - (n, from, to) = (n - 1, from, empty) + (1, from, to) + (n - 1, empty, to)
  - 단 empty = 6 - from - to (1번, 2번, 3번 - 기둥 번호의 합은 6 - from,to 제외 나머지 기둥 번호 계산)
 */
public class Problem16 {

    static class Solution1 {
        public int[][] solution(int n) {
            return hanoi(n, 1, 3).toArray(new int[0][]);
        }

        private List<int[]> hanoi(int n, int from, int to) {
            if (n == 1) {
                return List.of(new int[]{from, to});
            }

            int empty = 6 - from - to;
            List<int[]> result = new ArrayList<>();
            result.addAll(hanoi(n - 1, from, empty));
            result.addAll(hanoi(1, from, to));
            result.addAll(hanoi(n - 1, empty, to));
            return result;
        }
    }

    static class Solution2 {
        public int[][] solution(int n) {
            List<int[]> process = new ArrayList<>();
            hanoi(n, 1, 3, process);
            return process.toArray(new int[0][]);
        }

        private void hanoi(int n, int from, int to, List<int[]> process) {
            if (n == 1) {
                process.add(new int[]{from, to});
                return;
            }

            int empty = 6 - from - to;

            hanoi(n - 1, from, empty, process);
            hanoi(1, from, to, process);
            hanoi(n - 1, empty, to, process);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(2, new int[][]{{1, 2}, {1, 3}, {2, 3}})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] expected) {
        assertThat(new Solution1().solution(n)).isEqualTo(expected);
        assertThat(new Solution2().solution(n)).isEqualTo(expected);
    }
}
