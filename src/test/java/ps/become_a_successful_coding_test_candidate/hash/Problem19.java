package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 19 완주하지 못한 선수

제한사항
- 100,000 명 이하
- 동명이인이 있을 수 있음

풀이
- 완주자 Map<이름, 인원> 을 만든다 (동명이인)
- 참가자를 순회하면서
-- 완주자 Map 에 이름이 없거나, count 가 0 인 경우(동명이인이 완주) return
-- 이름이 있으면 count - 1
 */
public class Problem19 {

    static class Solution1 {
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> map = new HashMap<>();
            for (String c : completion) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            for (String p : participant) {
                if (map.getOrDefault(p, 0) == 0) {
                    return p;
                }
                map.put(p, map.get(p) - 1);
            }

            throw new RuntimeException();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"leo", "kiki", "eden"},
                        new String[]{"eden", "kiki"},
                        "leo"
                ),
                Arguments.of(
                        new String[]{"marina", "josipa", "nikola", "vinko", "filipa"},
                        new String[]{"josipa", "filipa", "marina", "nikola"},
                        "vinko"
                ),
                Arguments.of(
                        new String[]{"mislav", "stanko", "mislav", "ana"},
                        new String[]{"stanko", "ana", "mislav"},
                        "mislav"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] participant, String[] completion, String expected) {
        String result = new Solution1().solution(participant, completion);
        assertThat(result).isEqualTo(expected);
    }
}
