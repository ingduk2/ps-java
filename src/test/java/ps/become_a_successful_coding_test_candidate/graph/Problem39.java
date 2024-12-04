package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 39 미로 탈출

풀이
- 최단 경로 - bfs
- x, y 좌표계 사용
- 레버까지 최단 거리 + 레버에서 출구까지 최단 거리 이지만
-- 레버에서 출구로 이동할 때 시작 지점에서 레버로 이동할 때 지나간 경로를
-- 다시 이용할 수도 있기 때문에 dist 배열을 다시 초기화 해야 한다
- bfs 를 두 번 진행
-- 시작 지점 -> 레버
-- 레버 -> 출구 지점
 */
public class Problem39 {

    static class Solution1 {
        private final int[] dx = {0, 0, 1, -1};
        private final int[] dy = {1, -1, 0, 0};

        private static class Point {
            private final int nx;
            private final int ny;

            public Point(int nx, int ny) {
                this.nx = nx;
                this.ny = ny;
            }
        }

        private static char[][] map;
        private static int height;
        private static int width;

        public int solution(String[] maps) {
            height = maps.length;
            width = maps[0].length();

            map = new char[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    map[y] = maps[y].toCharArray();
                }
            }

            Point start = null;
            Point end = null;
            Point lever = null;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (map[y][x] == 'S') start = new Point(x, y);
                    else if (map[y][x] == 'E') end = new Point(x, y);
                    else if (map[y][x] == 'L') lever = new Point(x, y);
                }
            }

            int startLever = bfs(start, lever);
            int leverEnd = bfs(lever, end);

            if (startLever == -1 || leverEnd == -1) {
                return -1;
            } else {
                return startLever + leverEnd;
            }
        }

        private int bfs(Point start, Point end) {
            int[][] dist = new int[height][width];
            Queue<Point> queue = new ArrayDeque<>();
            dist[start.ny][start.nx] = 1;
            queue.add(start);

            while (!queue.isEmpty()) {
                Point now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = now.nx + dx[i];
                    int nextY = now.ny + dy[i];

                    if (nextX < 0 || nextX >= width || nextY < 0 | nextY >= height) {
                        continue;
                    }

                    if (dist[nextY][nextX] > 0) {
                        continue;
                    }

                    if (map[nextY][nextX] == 'X') {
                        continue;
                    }

                    dist[nextY][nextX] = dist[now.ny][now.nx] + 1;
                    queue.add(new Point(nextX, nextY));

                    if (nextX == end.nx && nextY == end.ny) {
                        return dist[end.ny][end.nx] - 1;
                    }
                }
            }

            return -1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"},
                        16
                ),
                Arguments.of(
                        new String[]{"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"},
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] maps, int expected) {
        assertThat(new Solution1().solution(maps)).isEqualTo(expected);
    }
}
