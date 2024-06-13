package ps.java_algorithm_interview_with_kotlin.nonlinear.shortestpath;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/network-delay-time/description/
 * 1. 다익스트라 알고리즘 구현
 */
public class Q44_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new HashMap<>());
            graph.get(time[0]).put(time[1], time[2]);
        }

        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.add(new AbstractMap.SimpleEntry<>(k, 0));

        Map<Integer, Integer> dist = new HashMap<>();
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> cur = pq.poll();
            int u = cur.getKey();
            int dist_u = cur.getValue();

            if (!dist.containsKey(u)) {
                dist.put(u, dist_u);
                if (graph.containsKey(u)) {
                    for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
                        int alt = dist_u + v.getValue();
                        pq.add(new AbstractMap.SimpleEntry<>(v.getKey(), alt));
                    }
                }
            }
        }

        if (dist.size() == n) {
            return Collections.max(dist.values());
        }

        return -1;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][] {
                                new int[]{2, 1, 1},
                                new int[]{2, 3, 1},
                                new int[]{3, 4, 1},
                        },
                        4, 2,
                        2
                ),
                Arguments.of(
                        new int[][] {
                                new int[]{1, 2, 1}
                        },
                        2, 1,
                        1
                ),
                Arguments.of(
                        new int[][] {
                                new int[]{1, 2, 1}
                        },
                        2, 2,
                        -1
                ),
                Arguments.of(
                        new int[][] {
                                new int[]{3, 1, 5},
                                new int[]{3, 2, 2},
                                new int[]{2, 1, 2},
                                new int[]{3, 4, 1},
                                new int[]{4, 5, 1},
                                new int[]{5, 6, 1},
                                new int[]{6, 7, 1},
                                new int[]{7, 8, 1},
                                new int[]{8, 1, 1},
                        },
                        8, 3,
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] times, int n, int k, int expected) {
        int result = new Q44_NetworkDelayTime().networkDelayTime(times, n, k);
        assertThat(result).isEqualTo(expected);
    }
}
