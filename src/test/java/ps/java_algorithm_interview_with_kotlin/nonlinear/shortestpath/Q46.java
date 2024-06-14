package ps.java_algorithm_interview_with_kotlin.nonlinear.shortestpath;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 * 1. 다익스트라 알고리즘 1
 * - dist key 가 Integer 면 효율성 통과
 * - String 이면 효율성 실패
 * 2. 다익스트라 알고리즘 2
 * 3. BFS
 */
public class Q46 {
    static class Dijkstra1 {
        static class Position {
            final int y;
            final int x;
            final int distance;

            public Position(int y, int x, int distance) {
                this.y = y;
                this.x = x;
                this.distance = distance;
            }
        }

        public int solution(int[][] maps) {
            Queue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
            pq.add(new Position(0, 0, 1));
            Map<Integer, Position> dist = new LinkedHashMap<>();

            while (!pq.isEmpty()) {
                Position cur = pq.poll();

                if (!dist.containsKey(distKey(cur.y, cur.x))) {
                    dist.put(distKey(cur.y, cur.x), cur);

                    findPath(cur.y, cur.x + 1, cur.distance, maps, pq);
                    findPath(cur.y, cur.x - 1, cur.distance, maps, pq);
                    findPath(cur.y + 1, cur.x, cur.distance, maps, pq);
                    findPath(cur.y - 1, cur.x, cur.distance, maps, pq);
                }
            }

            int resultKey = distKey(maps.length - 1, maps[0].length - 1);
            if (dist.containsKey(resultKey)) {
                return dist.get(resultKey).distance;
            }

            return -1;
        }

        private int distKey(int y, int x) {
            return y * 1000 + x;
        }

        private void findPath(int y, int x, int distance, int[][] maps, Queue<Position> pq) {
            if (y >= 0 &&
                    y < maps.length &&
                    x >= 0 &&
                    x < maps[0].length &&
                    maps[y][x] != 0) {
                maps[y][x] = 0;
                pq.add(new Position(y, x, distance + 1));
            }
        }
    }

    static class Dijkstra2 {
        static class Position {
            final int y;
            final int x;
            final int distance;

            public Position(int y, int x, int distance) {
                this.y = y;
                this.x = x;
                this.distance = distance;
            }
        }

        public int solution(int[][] maps) {
            int rows = maps.length;
            int cols = maps[0].length;
            Queue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
            pq.add(new Position(0, 0, 1));

            boolean[][] visited = new boolean[rows][cols];

            while (!pq.isEmpty()) {
                Position cur = pq.poll();

                if (cur.y == rows - 1 && cur.x == cols - 1) {
                    return cur.distance;
                }

                if (!visited[cur.y][cur.x]) {
                    visited[cur.y][cur.x] = true;

                    findPath(cur.y, cur.x + 1, cur.distance, maps, visited, pq);
                    findPath(cur.y, cur.x - 1, cur.distance, maps, visited, pq);
                    findPath(cur.y + 1, cur.x, cur.distance, maps, visited, pq);
                    findPath(cur.y - 1, cur.x, cur.distance, maps, visited, pq);
                }
            }

            return -1;
        }

        private void findPath(int y, int x, int distance, int[][] maps, boolean[][] visited, Queue<Position> pq) {
            if (y >= 0 &&
                    y < maps.length &&
                    x >= 0 &&
                    x < maps[0].length &&
                    maps[y][x] != 0 &&
                    !visited[y][x]
            ) {
                pq.add(new Position(y, x, distance + 1));
            }
        }
    }

    static class BFS {
        static class Position {
            final int y;
            final int x;
            final int distance;

            public Position(int y, int x, int distance) {
                this.y = y;
                this.x = x;
                this.distance = distance;
            }
        }

        public int solution(int[][] maps) {
            int rows = maps.length;
            int cols = maps[0].length;


            Queue<Position> queue = new LinkedList<>();
            queue.add(new Position(0, 0, 1));

            while (!queue.isEmpty()) {
                Position cur = queue.poll();

                if (cur.y == rows - 1 && cur.x == cols - 1) {
                    return cur.distance;
                }

                findPath(cur.y, cur.x + 1, cur.distance, maps, queue);
                findPath(cur.y, cur.x - 1, cur.distance, maps, queue);
                findPath(cur.y + 1, cur.x, cur.distance, maps, queue);
                findPath(cur.y - 1, cur.x, cur.distance, maps, queue);
            }

            return -1;
        }

        private void findPath(int y, int x, int distance, int[][] maps, Queue<Position> queue) {
            if (y >= 0 &&
                    y < maps.length &&
                    x >= 0 &&
                    x < maps[0].length &&
                    maps[y][x] != 0
            ) {
                maps[y][x] = 0;
                queue.add(new Position(y, x, distance + 1));
            }
        }
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                new int[]{1, 0, 1, 1, 1},
                                new int[]{1, 0, 1, 0, 1},
                                new int[]{1, 0, 1, 1, 1},
                                new int[]{1, 1, 1, 0, 1},
                                new int[]{0, 0, 0, 0, 1},
                        },
                        11
                ),
                Arguments.of(
                        new int[][]{
                                new int[]{1, 0, 1, 1, 1},
                                new int[]{1, 0, 1, 0, 1},
                                new int[]{1, 0, 1, 1, 1},
                                new int[]{1, 1, 1, 0, 0},
                                new int[]{0, 0, 0, 0, 1},
                        },
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test1(int[][] maps, int expected) {
        int result = new Dijkstra1().solution(maps);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test2(int[][] maps, int expected) {
        int result = new Dijkstra2().solution(maps);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test3(int[][] maps, int expected) {
        int result = new BFS().solution(maps);
        assertThat(result).isEqualTo(expected);
    }

}
