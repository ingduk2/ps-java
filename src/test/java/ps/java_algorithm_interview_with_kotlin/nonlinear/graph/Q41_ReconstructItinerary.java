package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/description/
 * - 일정을 재구성
 * - 중복된 일정의 경우 어휘순
 *
 * 1. 일정 그래프 재귀 DFS
 * 2. 일정 그래프 반복 DFS
 */
public class Q41_ReconstructItinerary {

    static class RecursiveSolve {
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> result = new LinkedList<>();
            Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();

            for (List<String> ticket : tickets) {
                fromToMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
                fromToMap.get(ticket.get(0)).add(ticket.get(1));
            }

            dfs(result, fromToMap, "JFK");
            return result;
        }

        private void dfs(
                List<String> result,
                Map<String, PriorityQueue<String>> fromToMap,
                String from
        ) {
            // from -> To 값이 존재하는 경우 반복
            while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
                dfs(result, fromToMap, fromToMap.get(from).poll());
            }

            // 재귀가 끝났다면, 최종 위치는 도착지 이므로 결과를 출발지까지 앞으로 삽입
            result.add(0, from);
        }
    }

    static class IterativeSolve {
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> result = new LinkedList<>();
            Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();

            for (List<String> ticket : tickets) {
                fromToMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
                fromToMap.get(ticket.get(0)).add(ticket.get(1));
            }

            Deque<String> stack = new ArrayDeque<>();
            stack.push("JFK");
            while (!stack.isEmpty()) {
                while (fromToMap.containsKey(stack.getFirst()) && !fromToMap.get(stack.getFirst()).isEmpty()) {
                    stack.push(fromToMap.get(stack.getFirst()).poll());
                }

                result.add(0, stack.pop());
            }

            return result;
        }

    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of("MUC", "LHR"),
                                List.of("JFK", "MUC"),
                                List.of("SFO", "SJC"),
                                List.of("LHR", "SFO")
                        ),
                        Arrays.asList("JFK","MUC","LHR","SFO","SJC")
                ),
                Arguments.of(
                        List.of(
                                List.of("JFK","SFO"),
                                List.of("JFK","ATL"),
                                List.of("SFO","ATL"),
                                List.of("ATL","JFK"),
                                List.of("ATL","SFO")
                        ),
                        Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<List<String>> tickets, List<String> expected) {
        List<String> result1 = new RecursiveSolve().findItinerary(tickets);
        assertThat(result1).isEqualTo(expected);

        List<String> result2 = new IterativeSolve().findItinerary(tickets);
        assertThat(result2).isEqualTo(expected);
    }
}
