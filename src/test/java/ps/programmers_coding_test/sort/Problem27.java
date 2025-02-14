package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 27 - 문자열 내 마음대로 정렬하기 - Level 1

풀이
- index n 의 문자를 기준으로 정렬
- 같은 문자인 경우, 사전순으로 앞선 문자열이 앞쪽에 위치
 */
public class Problem27 {

    static class Solution1 {
        public String[] solution(String[] strings, int n) {
            return Arrays.stream(strings)
                    .sorted(Comparator
                            .comparingInt((String o) -> o.charAt(n))
                            .thenComparing(o -> o)
                    )
                    .toArray(String[]::new);
        }
    }

    static class Solution2 {
        public String[] solution(String[] strings, int n) {
            Arrays.sort(strings, (o1, o2) -> {
                if (o1.charAt(n) != o2.charAt(n)) {
                    return o1.charAt(n) - o2.charAt(n);
                }
                return o1.compareTo(o2);
            });
            return strings;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"sun", "bed", "car"}, 1,
                        new String[]{"car", "bed", "sun"}
                ),
                Arguments.of(
                        new String[]{"abce", "abcd", "cdx"}, 2,
                        new String[]{"abcd", "abce", "cdx"}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] strings, int n, String[] expected) {
        assertThat(new Solution1().solution(strings, n)).isEqualTo(expected);
        assertThat(new Solution2().solution(strings, n)).isEqualTo(expected);
    }
}
