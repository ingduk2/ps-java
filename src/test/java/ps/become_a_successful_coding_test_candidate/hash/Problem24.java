package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 24 메뉴 리뉴얼
- 스카피는 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.
- 단, 코스요리 메뉴는 최소 2가지 이상의 단품메뉴
- 또한, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함

- course 배열의 각 원소는 2 이상 10 이하인 자연수가 오름차순으로 정렬되어 있습니다.
- 배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬되어야 합니다.
- 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.

풀이
- Map<메뉴수, Map<메뉴 조합, 주문수> 를 만든다
-- WX == XW 이므로 정렬이 필요
-- course[] 에 있는 size 만 메뉴 조합으로 만든다
- 결과 만들기
-- max 주문수를 가져온다
-- Map<메뉴 조합, 주문수> 에서 1개 이상인 것을 max 주문수로 filter 한다
-- 결과는 알파벳 오름차순 정렬
 */
public class Problem24 {

    static class Solution1 {

        public String[] solution(String[] orders, int[] course) {
            Map<Integer, Map<String, Integer>> courseMap = new HashMap<>();

            for (String order : orders) {
                List<String> combinations = new ArrayList<>();
                char[] orderArray = order.toCharArray();
                Arrays.sort(orderArray);

                for (int c : course) {
                    combination(0, orderArray, "", combinations, c);
                }

                for (String combination : combinations) {
                    int courseCount = combination.length();
//                    if (courseMap.containsKey(courseCount)) {
//                        Map<String, Integer> menuMap = courseMap.get(courseCount);
//                        menuMap.put(combination, menuMap.getOrDefault(combination, 0) + 1);
//                    } else {
//                        Map<String, Integer> menuMap = new HashMap<>();
//                        menuMap.put(combination, 1);
//                        courseMap.put(courseCount, menuMap);
//                    }

                    courseMap.computeIfAbsent(courseCount, key -> new HashMap<>())
                            .merge(combination, 1, Integer::sum);
                }
            }

            List<String> answer = new ArrayList<>();
            for (Map<String, Integer> counts : courseMap.values()) {
                int maxCount = counts.values().stream()
                        .max(Integer::compareTo)
                        .orElse(0);

                if (maxCount > 1) {
                    counts.entrySet().stream()
                            .filter(entry -> entry.getValue() == maxCount)
                            .forEach(entry -> answer.add(entry.getKey()));
                }
            }
            Collections.sort(answer);

            return answer.toArray(new String[0]);
        }

        private void combination(int idx, char[] order, String result, List<String> combinations, int c) {
            if (result.length() == c) {
                combinations.add(result);
            }

            for (int i = idx; i < order.length; i++) {
                combination(i + 1, order, result + order[i], combinations, c);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                        new int[]{2, 3, 4},
                        new String[]{"AC", "ACDE", "BCFG", "CDE"}
                ),
                Arguments.of(
                        new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                        new int[]{2, 3, 5},
                        new String[]{"ACD", "AD", "ADE", "CD", "XYZ"}
                ),
                Arguments.of(
                        new String[]{"XYZ", "XWY", "WXA"},
                        new int[]{2, 3, 4},
                        new String[]{"WX", "XY"}
                ),
                Arguments.of(
                        new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                        new int[]{2, 3, 4},
                        new String[]{"AC", "ACDE", "BCFG", "CDE"}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] orders, int[] course, String[] expected) {
        assertThat(new Solution1().solution(orders, course)).isEqualTo(expected);
    }
}
