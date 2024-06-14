package ps.java_algorithm_interview_with_kotlin.nonlinear.shortestpath;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 * 1. 다익스트라 알고리즘 응용
 * 2. 다익스트라 알고리즘 개선
 */
public class Q45_CheapestFlightsWithinKStops {

    static class Solve1 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.putIfAbsent(flight[0], new HashMap<>());
                graph.get(flight[0]).put(flight[1], flight[2]);
            }

            Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
            pq.add(Arrays.asList(src, 0, 0));

            while (!pq.isEmpty()) {
                List<Integer> cur = pq.poll();
                int u = cur.get(0);
                int price_u = cur.get(1);
                int k_visited = cur.get(2);

                if (u == dst) {
                    return price_u;
                }

                if (k_visited <= k) {
                    k_visited += 1;
                    if (graph.containsKey(u)) {
                        for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
                            int alt = price_u + v.getValue();
                            pq.add(Arrays.asList(v.getKey(), alt, k_visited));
                        }
                    }
                }
            }

            return -1;
        }
    }

    static class Solve2 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.putIfAbsent(flight[0], new HashMap<>());
                graph.get(flight[0]).put(flight[1], flight[2]);
            }

            Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
            pq.add(Arrays.asList(src, 0, 0));

            Map<Integer, Integer> visited = new HashMap<>();

            while (!pq.isEmpty()) {
                List<Integer> cur = pq.poll();
                int u = cur.get(0);
                int price_u = cur.get(1);
                int k_visited = cur.get(2);

                if (u == dst) {
                    return price_u;
                }

                visited.put(u, k_visited);
                if (k_visited <= k) {
                    k_visited += 1;
                    if (graph.containsKey(u)) {
                        for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
                            if (!visited.containsKey(v.getKey()) || k_visited < visited.get(v.getKey())) {
                                int alt = price_u + v.getValue();
                                pq.add(Arrays.asList(v.getKey(), alt, k_visited));
                            }
                        }
                    }
                }
            }

            return -1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        4,
                        new int[][]{
                                new int[]{0, 1, 100},
                                new int[]{1, 2, 100},
                                new int[]{2, 0, 100},
                                new int[]{1, 3, 600},
                                new int[]{2, 3, 200},
                        },
                        0, 3, 1,
                        700
                ),
                Arguments.of(
                        3,
                        new int[][]{
                                new int[]{0, 1, 100},
                                new int[]{1, 2, 100},
                                new int[]{0, 2, 500},
                        },
                        0, 2, 1,
                        200
                ),
                Arguments.of(
                        3,
                        new int[][]{
                                new int[]{0, 1, 100},
                                new int[]{1, 2, 100},
                                new int[]{0, 2, 500},
                        },
                        0, 2, 0,
                        500
                ),
                Arguments.of(
                        5,
                        new int[][]{
                                new int[]{1, 2, 10},
                                new int[]{2, 0, 7},
                                new int[]{1, 3, 8},
                                new int[]{4, 0, 10},
                                new int[]{3, 4, 2},
                                new int[]{4, 2, 10},
                                new int[]{0, 3, 3},
                                new int[]{3, 1, 6},
                                new int[]{2, 4, 5},
                        },
                        0, 4, 1,
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] flights, int src, int dst, int k, int expected) {
        int result = new Solve1().findCheapestPrice(n, flights, src, dst, k);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solve2().findCheapestPrice(n, flights, src, dst, k);
        assertThat(result2).isEqualTo(expected);
    }
}
