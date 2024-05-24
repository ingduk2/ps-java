package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 * - 가장 낮은 두 개의 음식 스코빌을 합쳐서 (음식1 + (음식2 * 2)) 새로운 음식을 만들어서
 * - 모든 음식의 스코빌 지수가 K 이상이 될 때까지 섞는다
 * - 최소 횟수를 구하라
 * - 만들 수 없는 경우에는 -1
 *
 * 1. 우선 순위 큐 사용
 * - int[] scoville 을 우선순위 큐에 넣는다
 * -
 */
public class Q29 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }

        if (pq.peek() >= K) {
            return 0;
        }

        int answer = 1;
        while (pq.size() >= 2) {
            System.out.println("a");
            int newScoville = pq.poll() + (pq.poll() * 2);
            pq.add(newScoville);

            // 우선순위 큐 이므로 peek 가 k 이상인지 확인
            if (pq.peek() >= K) {
                return answer;
            }

            answer++;
        }

        return -1;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                // 섞어야 하는 경우
                Arguments.of(
                        new int[]{1, 2, 3, 9, 10, 12},
                        7,
                        2
                ),
                // 이미 다 섞여 있는 경우
                Arguments.of(
                        new int[]{1, 2, 3, 9, 10, 12},
                        1,
                        0
                ),
                // 만들 수 없는 경우
                Arguments.of(
                        new int[]{1, 2, 3, 9, 10, 12},
                        1000,
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] scoville, int k, int expected) {
        int result = new Q29().solution(scoville, k);
        assertThat(result).isEqualTo(expected);
    }
}
