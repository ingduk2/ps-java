package ps.programmers_coding_test.sort;

/*
문제 29 - 메뉴 리뉴얼 - Level 2

풀이
1. 주문된 단품 메뉴 조합을 만들기
2. 각 조합의 빈도수 계산
3. 가장 많이 등장한 조합 찾기
4. 사전순 정렬하여 반환
 */

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem29 {

    static class Solution1 {

        private static class Course {
            private final String course;
            private final int occurrences;

            private Course(String course, int occurrences) {
                this.course = course;
                this.occurrences = occurrences;
            }
        }

        public String[] solution(String[] orders, int[] course) {
            List<Set<String>> orderList = Arrays.stream(orders)
                    .map(String::chars)
                    .map(charStream -> charStream
                            .mapToObj(menu -> String.valueOf((char) menu))
                            .collect(Collectors.toSet())
                    ).collect(Collectors.toList());

            Map<Integer, List<Course>> courses = new HashMap<>();
            for (int length : course) {
                List<Course> list = new ArrayList<>();
                list.add(new Course("", 0));
                courses.put(length, list);
            }

            getCourses('A', new HashSet<>(), orderList, courses);

            return courses.values().stream()
                    .filter(list -> list.get(0).occurrences > 0)
                    .flatMap(List::stream)
                    .map(c -> c.course)
                    .sorted()
                    .toArray(String[]::new);
        }

        private void getCourses(
                char nextMenu,
                Set<String> selectedMenus,
                List<Set<String>> orderList,
                Map<Integer, List<Course>> courses
        ) {
            int occurrences = (int) orderList.stream()
                    .filter(order -> order.containsAll(selectedMenus))
                    .count();

            if (occurrences < 2) return;

            int size = selectedMenus.size();
            if (courses.containsKey(size)) {
                List<Course> courseList = courses.get(size);
                Course course = new Course(selectedMenus.stream()
                        .sorted()
                        .collect(Collectors.joining("")),
                        occurrences
                );
                Course original = courseList.get(0);
                if (original.occurrences < occurrences) {
                    courseList.clear();
                    courseList.add(course);
                } else if (original.occurrences == occurrences) {
                    courseList.add(course);
                }
            }

            if (size >= 10) return;
            for (char menuChar = nextMenu; menuChar <= 'Z'; menuChar++) {
                String menu = String.valueOf(menuChar);
                selectedMenus.add(menu);
                getCourses((char) (menuChar + 1), selectedMenus, orderList, courses);
                selectedMenus.remove(menu);
            }
        }
    }

    static class Solution2 {
        public String[] solution(String[] orders, int[] course) {
            List<String> result = new ArrayList<>();

            // 각 코스 크기에 대해 처리
            for (int c : course) {
                Map<String, Integer> combinationCount = new HashMap<>();
                int maxCount = 2;

                // 각 손님의 주문을 정렬 후 조합 생성
                for (String order : orders) {
                    char[] menu = order.toCharArray();
                    Arrays.sort(menu);
                    generateCombinations(menu, c, 0, "", combinationCount);
                }

                // 가장 많이 등장한 조합 찾기
                List<String> candidates = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : combinationCount.entrySet()) {
                    if (entry.getValue() >= maxCount) {
                        if (entry.getValue() > maxCount) {
                            maxCount = entry.getValue();
                            candidates.clear();
                        }
                        candidates.add(entry.getKey());
                    }
                }

                result.addAll(candidates);
            }

            Collections.sort(result);
            return result.toArray(new String[0]);
        }

        private void generateCombinations(char[] menu, int length, int index, String current, Map<String, Integer> combinationCount) {
            if (current.length() == length) {
                combinationCount.put(current, combinationCount.getOrDefault(current, 0) + 1);
                return;
            }

            for (int i = index; i < menu.length; i++) {
                generateCombinations(menu, length, i + 1, current + menu[i], combinationCount);
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
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] orders, int[] course, String[] expected) {
        assertThat(new Solution1().solution(orders, course)).isEqualTo(expected);
        assertThat(new Solution2().solution(orders, course)).isEqualTo(expected);
    }
}
