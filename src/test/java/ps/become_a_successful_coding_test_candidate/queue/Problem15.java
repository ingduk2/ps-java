package ps.become_a_successful_coding_test_candidate.queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 15 요세푸스 문제

N 명의 사람이 원 형태로 서 있습니다. 각 사람은 1부터 N 까지 번호표를 갖고 있습니다.
임의의 숫자 K 가 주어졌을 때 다음과 같이 사람을 없앱니다.
- 1번 번호표를 가진 사람을 기준으로 K번째 사람을 없앱니다
- 없앤 사람 다음 사람을 기준으로 하고 다시 K번째 사람을 없앱니다
N, K 가 주어질 때 마지막에 살아있는 사람의 번호를 반환하는 solution() 함수를 구현

[1, 2, 3, 4, 5], 2
[1, 3, 4, 5] (2 제거)
[1, 3, 5] (4 제거)
[3, 5] (1 제거)
[3] (5 제거)

풀이
- 큐
-- front [1, 2, 3, 4, 5] rear
-- k - 1 까지 빼서 뒤로 옮긴다
-- front [2, 3, 4, 5, 1] rear
-- poll
-- front [3, 4, 5, 1] rear
-- 반복, queue size 1 까지
 */
public class Problem15 {

    static class Solution1 {
        public int solution(int n, int k) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }

            while (queue.size() > 1) {
                for (int i = 0; i < k - 1; i++) {
                    queue.add(queue.poll());
                }

                queue.poll();
            }

            return queue.poll();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(5, 2, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int k, int expected) {
        int result = new Solution1().solution(n, k);
        assertThat(result).isEqualTo(expected);
    }
}
