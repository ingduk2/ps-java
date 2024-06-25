package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-height-trees/description/
 * - 최소 높이 트리
 *
 * 1. 단계별 리프 노드 제거
 * - 루트가 가장 가운데에 있는 경우 최소 높이 구성
 */
public class Q54_MinimumHeightTrees {

    static class Solution1 {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int[] edge : edges) {
                graph.putIfAbsent(edge[0], new ArrayList<>());
                graph.putIfAbsent(edge[1], new ArrayList<>());
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }

            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (graph.get(i).size() == 1) {
                    leaves.add(i);
                }
            }

            while (n > 2) {
                n -= leaves.size();
                List<Integer> newLeaves = new ArrayList<>();
                for (int leaf : leaves) {
                    int neighbor = graph.get(leaf).get(0);
                    graph.get(neighbor).remove((Object) leaf);
                    if (graph.get(neighbor).size() == 1) {
                        newLeaves.add(neighbor);
                    }
                }

                leaves = newLeaves;
            }

            return leaves;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        4,
                        new int[][]{
                                new int[]{1, 0},
                                new int[]{1, 2},
                                new int[]{1, 3},
                        },
                        List.of(1)
                ),
                Arguments.of(
                        6,
                        new int[][]{
                                new int[]{3, 0},
                                new int[]{3, 1},
                                new int[]{3, 2},
                                new int[]{3, 4},
                                new int[]{5, 4},
                        },
                        List.of(3, 4)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] edges, List<Integer> expected) {
        List<Integer> result = new Solution1().findMinHeightTrees(n, edges);
        assertThat(result).isEqualTo(expected);
    }
}
