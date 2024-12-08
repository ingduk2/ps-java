package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 02 방의 개수

풀이
- 점 방문 여부 확인 Set<Point>
- 간선 방문 여부 확인 Set<Edge>
- 이미 방문한 점에 도달하면서 새로운 간선을 추가할 때, 새로운 방이 형성
- (교차점 처리)이동이 대각선으로 교차하면서 새로운 방이 형성될 수도 있으므로, 각 이동 중간 점을 추가하여 2번의 이동으로 분리
 */
public class GraphRecommendProblem2 {

    static class Solution1 {

        private final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        private final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        static class Point {
            private int x;
            private int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

        public int solution(int[] arrows) {
            Set<Point> visitedPoints = new HashSet<>();
            Set<Edge> visitedEdges = new HashSet<>();

            int x = 0;
            int y = 0;
            visitedPoints.add(new Point(x, y));

            int answer = 0;

            for (int arrow : arrows) {
                for (int i = 0; i < 2; i++) {
                    int nx = x + dx[arrow];
                    int ny = y + dy[arrow];

                    Point current = new Point(x, y);
                    Point next = new Point(nx, ny);
                    Edge edge = new Edge(current, next);

                    if (visitedPoints.contains(next) && !visitedEdges.contains(edge)) {
                        answer++;
                    }

                    visitedPoints.add(next);
                    visitedEdges.add(edge);
                    visitedEdges.add(new Edge(next, current));

                    x = nx;
                    y = ny;
                }
            }

            return answer;
        }

        static class Edge {
            private Point from;
            private Point to;

            public Edge(Point from, Point to) {
                this.from = from;
                this.to = to;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Edge edge = (Edge) o;
                return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
            }

            @Override
            public int hashCode() {
                return Objects.hash(from, to);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{
                                6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0
                        },
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] arrows, int expected) {
        assertThat(new Solution1().solution(arrows)).isEqualTo(expected);
    }
}
