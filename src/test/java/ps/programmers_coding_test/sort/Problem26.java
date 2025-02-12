package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 26 - 문자열 내림차순으로 배치하기 - Level 1

풀이
- 문자를큰 것 부터 작은 순으로 정렬
- 대문자는 소문자보다 작은 것으로 간주
 */
public class Problem26 {

    static class Solution1 {
        public String solution(String s) {
            char[] charArr = s.toCharArray();
            Character[] characterArr = new Character[charArr.length];
            Arrays.setAll(characterArr, i -> charArr[i]);

            Arrays.sort(characterArr, Collections.reverseOrder());

            StringBuilder sb = new StringBuilder();
            for (char c : characterArr) {
                sb.append(c);
            }

            return sb.toString();
        }
    }

    static class Solution2 {
        public String solution(String s) {
            List<String> collect = s.chars().boxed()
                    .sorted(Comparator.reverseOrder())
                    .map(it -> String.valueOf((char)(int)it))
                    .collect(Collectors.toList());

            return String.join("", collect);
        }
    }

    static class Solution3 {
        public String solution(String s) {
            return s.chars()
                    .boxed()
                    .sorted(Collections.reverseOrder())
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("Zbcdefg", "gfedcbZ")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, String expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
        assertThat(new Solution2().solution(s)).isEqualTo(expected);
        assertThat(new Solution3().solution(s)).isEqualTo(expected);
    }
}
