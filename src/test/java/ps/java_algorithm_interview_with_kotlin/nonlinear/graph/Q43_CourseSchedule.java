package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

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
 * https://leetcode.com/problems/course-schedule/description/
 * 1. DFS 로 순환 구조 판별
 * 2. 가지치기를 이용한 최적화
 *
 */
public class Q43_CourseSchedule {

    static class Solve1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> finishToTakeMap = new HashMap<>();
            for (int[] p : prerequisites) {
                finishToTakeMap.putIfAbsent(p[0], new ArrayList<>());
                finishToTakeMap.get(p[0]).add(p[1]);
            }

            List<Integer> takes = new ArrayList<>();
            for (Integer finish : finishToTakeMap.keySet()) {
                if (!dfs(finishToTakeMap, finish, takes)) {
                    return false;
                }
            }

            return true;
        }

        private boolean dfs(
                Map<Integer, List<Integer>> finishToTakeMap,
                Integer finish,
                List<Integer> takes
        ) {
            if (takes.contains(finish)) {
                return false;
            }

            if (finishToTakeMap.containsKey(finish)) {
                takes.add(finish);
                for (Integer take : finishToTakeMap.get(finish)) {
                    if (!dfs(finishToTakeMap, take, takes)) {
                        return false;
                    }
                }
                takes.remove(finish);
            }
            return true;
        }
    }

    static class Solve2 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> finishToTakeMap = new HashMap<>();
            for (int[] p : prerequisites) {
                finishToTakeMap.putIfAbsent(p[0], new ArrayList<>());
                finishToTakeMap.get(p[0]).add(p[1]);
            }

            List<Integer> takes = new ArrayList<>();
            List<Integer> taken = new ArrayList<>();
            for (Integer finish : finishToTakeMap.keySet()) {
                if (!dfs(finishToTakeMap, finish, takes, taken)) {
                    return false;
                }
            }

            return true;
        }

        private boolean dfs(
                Map<Integer, List<Integer>> finishToTakeMap,
                Integer finish,
                List<Integer> takes,
                List<Integer> taken) {
            if (takes.contains(finish)) {
                return false;
            }

            if (taken.contains(finish)) {
                return true;
            }

            if (finishToTakeMap.containsKey(finish)) {
                takes.add(finish);
                for (Integer take : finishToTakeMap.get(finish)) {
                    if (!dfs(finishToTakeMap, take, takes, taken)) {
                        return false;
                    }
                }
                takes.remove(finish);
            }
            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        2,
                        new int[][] {
                                new int[]{1, 0}
                        },
                        true
                ),
                Arguments.of(
                        2,
                        new int[][] {
                                new int[]{1, 0},
                                new int[]{0, 1}
                        },
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int numCourses, int[][] prerequisites, boolean expected) {
        boolean result = new Solve1().canFinish(numCourses, prerequisites);
        assertThat(result).isEqualTo(expected);

        boolean result2 = new Solve2().canFinish(numCourses, prerequisites);
        assertThat(result2).isEqualTo(expected);
    }
}
