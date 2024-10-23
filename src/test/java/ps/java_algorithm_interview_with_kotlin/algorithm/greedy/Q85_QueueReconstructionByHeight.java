package ps.java_algorithm_interview_with_kotlin.algorithm.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height
 * - (h, k) h 는 키, k 는 앞에 서있는 사람들 중 자신과 키가 같거나 더 큰 사람들의 수
 * - 규칙에 맞게 재정렬
 *
 * - h(키) 는 내림차순, 동일하면 k(앞선 사람 수) 는 오름차순으로 정렬
 * - 각 사람을 k 값에 해당하는 인덱스에 삽입
 * -- 키가 큰 사람들은 먼저 자리잡고, 같은 키를 가진 사람들은 k 값에 따라 적절한 위치에 배치
 * -- 키가 같은 사람들은 k 값이 작은 사람이 더 앞에 온다
 */
public class Q85_QueueReconstructionByHeight {

    static class Solution1 {
        public int[][] reconstructQueue(int[][] people) {
            // 우선순위 큐 선언, 정렬 기준은 큰 키 우선, 값이 같다면 앞에 줄 서 있는 사람이 작은 순으로
            Queue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> a[0] == b[0]
                            ? a[1] - b[1]
                            : b[0] - a[0]
            );

            pq.addAll(Arrays.asList(people));

            List<int[]> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                // 큰 키 우선, 앞에 줄 서 있는 사람이 작은 순으로 추출
                int[] person = pq.poll();
                // 앞에 줄 서 있는 사람을 인덱스로 정해서 삽입
                result.add(person[1], person);
            }

            return result.toArray(new int[people.length][2]);
        }
    }

    static class Solution2 {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            });

            System.out.println(Arrays.deepToString(people));
            List<int[]> result = new ArrayList<>();
            for (int[] person : people) {
                result.add(person[1], person);
            }

            return result.toArray(new int[people.length][2]);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}},
                        new int[][]{{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}}
                ),
                Arguments.of(
                        new int[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}},
                        new int[][]{{4, 0}, {5, 0}, {2, 2}, {3, 2}, {1, 4}, {6, 0}}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] people, int[][] expected) {
        int[][] result = new Solution1().reconstructQueue(people);
        assertThat(result).isEqualTo(expected);

        int[][] result2 = new Solution2().reconstructQueue(people);
        assertThat(result2).isEqualTo(expected);
    }
}
