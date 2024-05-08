package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/most-common-word
 * - , . 무시
 * - banned 되지 않은 단어 중 제일 많이 나온 단어
 * - 소문자로
 *
 * 1 , . 문자를 제거
 * 2 map 으로 단어 카운팅
 * 3 banned 에 있는 것은 제외하고 제일 많이 나온 것 반환
 */
public class Q4_MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String input = paragraph.toLowerCase().replaceAll("\\W+", " ");
        Set<String> ban = new HashSet<>(Arrays.asList(banned));

        Map<String, Long> countingMap = Arrays.stream(input.split(" "))
                .filter(it -> ! ban.contains(it))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        return countingMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        "Bob hit a ball, the hit BALL flew far after it was hit.",
                        new String[]{"hit"},
                        "ball"
                ),
                Arguments.of(
                        "a.",
                        new String[]{},
                        "a"
                )
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(String paragraph, String[] banned, String expected) {
        Q4_MostCommonWord problem = new Q4_MostCommonWord();
        String result = problem.mostCommonWord(paragraph, banned);

        assertThat(result).isEqualTo(expected);
    }
}
