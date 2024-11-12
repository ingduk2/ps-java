package ps.become_a_successful_coding_test_candidate.queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 16 기능 개발
뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포

풀이
- 각 progress 가 몇일 후에 배포 가능한지 구한다 (100 - progress) / speed 올림 처리
- 몇일 후 배포 가능한지 일수로 각 배포마다 몇 개의 기능이 배포되는지를 구한다
-- [7, 3, 9] -> [2, 1] | [5, 10, 1, 1, 20, 1] -> [1, 3, 2]
 */
public class Problem16 {

    static class Solution1 {
        public int[] solution(int[] progresses, int[] speeds) {
            int length = progresses.length;
            int[] days = new int[length];

            for (int i = 0; i < length; i++) {
                days[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            }

            List<Integer> result = new ArrayList<>();
            System.out.println(Arrays.toString(days));

            int count = 0;
            int maxDay = days[0];
            for (int i = 0; i < length; i++) {
                if (days[i] <= maxDay) {
                    count++;
                } else {
                    result.add(count);
                    count = 1;
                    maxDay = days[i];
                }
            }

            result.add(count);

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Solution2 {
        public int[] solution(int[] progresses, int[] speeds) {

            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < progresses.length; i++) {
                int day = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
                queue.offer(day);
            }

            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                int day = queue.poll();
                int count = 1;

                while (!queue.isEmpty() && queue.peek() <= day) {
                    count++;
                    queue.poll();
                }

                result.add(count);
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{93, 30, 55}, new int[]{1, 30, 5}, new int[]{2, 1}),
                Arguments.of(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1}, new int[]{1, 3, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] progresses, int[] speeds, int[] expected) {
        int[] result = new Solution1().solution(progresses, speeds);
        assertThat(result).isEqualTo(expected);

        int[] result2 = new Solution2().solution(progresses, speeds);
        assertThat(result2).isEqualTo(expected);
    }
}
