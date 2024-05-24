package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 * - 0,0 에서 가장 가까운 k 개의 점 목록
 * - 우선 순위 큐를 사용해서 Euclidean 거리로 오름차순으로 정렬되게 넣는다
 * - k 개수 만큼 결과를 만든다
 */
public class Q28_KClosestPointsToOrigin {
    static class Point {
        private double distance;
        private int[] point;

        public Point(double distance, int[] point) {
            this.distance = distance;
            this.point = point;
        }
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.distance));

        for (int[] point : points) {
            double distance = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
            pq.add(new Point(distance, point));
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point point = pq.poll();
            result[i] = point.point;
        }

        return result;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{{1, 3}, {-2, 2}},
                        1,
                        new int[][]{{-2, 2}}),
                Arguments.of(
                        new int[][]{{3, 3}, {5, -1}, {-2, 4}},
                        2,
                        new int[][]{{3, 3}, {-2, 4}}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] points, int k, int[][] expected) {
        int[][] result = new Q28_KClosestPointsToOrigin().kClosest(points, k);
        assertThat(result).isEqualTo(expected);
    }
}
