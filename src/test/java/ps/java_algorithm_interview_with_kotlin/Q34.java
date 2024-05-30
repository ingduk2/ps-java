package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * - participant 에 있고, completion 에는 없는 사람 찾기
 * - 동명이인이 있는 경우 두명이 다 들어와야 한다
 *
 * 1. 각각 map 에 counting 후에 completion 에 없거나 count 가 다른 경우
 * - participant map
 * - completion map
 * - participant for 돌면서, completion 에 없거나 get 한 수가 다른 경우 완주하지 못한 선수
 *
 * 2. map 에 참가자 counting 후에 completion 에 있는 경우 삭제
 * - count == 1 인 경우 삭제
 * - 1이 아닌 경우 count - 1
 * - map 에 남아 있는 키가 결과
 */
public class Q34 {
    static class Solve1 {
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> participantCountMap = new HashMap<>();
            for (String s : participant) {
                int count = participantCountMap.getOrDefault(s, 0);
                participantCountMap.put(s, count + 1);
            }

            Map<String, Integer> completionCount = new HashMap<>();
            for (String s : completion) {
                int count = completionCount.getOrDefault(s, 0);
                completionCount.put(s, count + 1);
            }

            for (String name : participantCountMap.keySet()) {
                if (!completionCount.containsKey(name)) {
                    return name;
                }

                int pCount = participantCountMap.get(name);
                int cCount = completionCount.get(name);
                if (pCount != cCount) {
                    return name;
                }
            }

            return "";
        }
    }

    static class Solve2 {
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> map = new HashMap<>();
            for (String p : participant) {
                map.put(p, map.getOrDefault(p, 0) + 1);
            }

            for (String c : completion) {
                int count = map.get(c);
                if (count == 1) {
                    map.remove(c);
                } else {
                    map.put(c, count - 1);
                }
            }

            return map.entrySet().iterator().next().getKey();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"leo", "kiki", "eden"},
                        new String[]{"eden", "kiki"},
                        "leo"),
                Arguments.of(
                        new String[]{"marina", "josipa", "nikola", "vinko", "filipa"},
                        new String[]{"josipa", "filipa", "marina", "nikola"},
                        "vinko"),
                Arguments.of(
                        new String[]{"mislav", "stanko", "mislav", "ana"},
                        new String[]{"stanko", "ana", "mislav"},
                        "mislav")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] participant, String[] completion, String expected) {
        String result = new Solve1().solution(participant, completion);
        assertThat(result).isEqualTo(expected);

        String resul2 = new Solve2().solution(participant, completion);
        assertThat(resul2).isEqualTo(expected);
    }
}
