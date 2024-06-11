package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 * - 여행 경로
 *
 */
public class Q42 {
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();
        for (String[] ticket : tickets) {
            fromToMap.putIfAbsent(ticket[0], new PriorityQueue<>());
            fromToMap.get(ticket[0]).add(ticket[1]);
        }

        List<String> result = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("ICN");

        while (!stack.isEmpty()) {
            while (fromToMap.containsKey(stack.getFirst()) && !fromToMap.get(stack.getFirst()).isEmpty()) {
                stack.push(fromToMap.get(stack.getFirst()).poll());
            }

            result.add(0, stack.poll());
        }

        return result.toArray(String[]::new);
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[][] {
                                new String[]{"ICN", "JFK"},
                                new String[]{"HND", "IAD"},
                                new String[]{"JFK", "HND"}
                        },
                        new String[]{"ICN", "JFK", "HND", "IAD"}
                ),
                Arguments.of(
                        new String[][] {
                                new String[]{"ICN", "SFO"},
                                new String[]{"ICN", "ATL"},
                                new String[]{"SFO", "ATL"},
                                new String[]{"ATL", "ICN"},
                                new String[]{"ATL", "SFO"},
                        },
                        new String[]{"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[][] tickets, String[] expected) {
        String[] result = new Q42().solution(tickets);
        assertThat(result).isEqualTo(expected);
    }
}
