package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 37 게임 맵 최단 거리

풀이
- 미로 최단거리 bfs 를 이용 하자
- 1. x, y 좌표계 풀이
- 2. row, col 풀이
 */
public class Problem37 {

    static class Solution1 {
        private final int[] dx = {0, 0, 1, -1};
        private final int[] dy = {1, -1, 0, 0};

        private static class Point {
            private final int x;
            private final int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int solution(int[][] maps) {
            int height = maps.length;
            int width = maps[0].length;

            int[][] dist = new int[height][width];
            Queue<Point> queue = new ArrayDeque<>();
            queue.add(new Point(0, 0));
            dist[0][0] = 1;

            while (!queue.isEmpty()) {
                Point now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + dx[i];
                    int nextY = now.y + dy[i];

                    if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= height) {
                        continue;
                    }

                    if (maps[nextY][nextX] == 0) {
                        continue;
                    }

                    if (dist[nextY][nextX] == 0) {
                        queue.add(new Point(nextX, nextY));
                        dist[nextY][nextX] = dist[now.y][now.x] + 1;
                    }
                }
            }

            return dist[height - 1][width - 1] == 0 ? -1 : dist[height - 1][width - 1];
        }
    }

    static class Solution2 {
        private final int[] dr = {0, 0, 1, -1};
        private final int[] dc = {1, -1, 0, 0};

        private static class Point {
            private final int row;
            private final int col;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        public int solution(int[][] maps) {
            int rows = maps.length;
            int cols = maps[0].length;

            int[][] dist = new int[rows][cols];
            Queue<Point> queue = new ArrayDeque<>();
            queue.add(new Point(0, 0));
            dist[0][0] = 1;

            while (!queue.isEmpty()) {
                Point now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = now.row + dr[i];
                    int nextCol = now.col + dc[i];

                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                        continue;
                    }

                    if (maps[nextRow][nextCol] == 0) {
                        continue;
                    }

                    if (dist[nextRow][nextCol] == 0) {
                        queue.add(new Point(nextRow, nextCol));
                        dist[nextRow][nextCol] = dist[now.row][now.col] + 1;
                    }
                }
            }

            return dist[rows - 1][cols - 1] == 0 ? -1 : dist[rows - 1][cols - 1];
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 0, 1, 1, 1},
                                {1, 0, 1, 0, 1},
                                {1, 0, 1, 1, 1},
                                {1, 1, 1, 0, 1},
                                {0, 0, 0, 0, 1},
                        },
                        11
                ),
                Arguments.of(
                        new int[][]{
                                {1, 0, 1, 1, 1},
                                {1, 0, 1, 0, 1},
                                {1, 0, 1, 1, 1},
                                {1, 1, 1, 0, 0},
                                {0, 0, 0, 0, 1},
                        },
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] maps, int expected) {
        assertThat(new Solution1().solution(maps)).isEqualTo(expected);
        assertThat(new Solution2().solution(maps)).isEqualTo(expected);
    }
}
