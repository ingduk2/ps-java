package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 7 이상한 문자 만들기 - Level 1

풀이 1
- 짝수번째 알파벳은 대문자호, 홀수번째 알파벳은 소문자로
- 단어별 짝, 홀수 인덱스 판단
- split 사용시 -1 limit 필요
- index 가 0 이상인 경우만 공백 추가 (substring 제외)

풀이 2
- 문자열의 시작이나 끝도 공백이 있을 수 있다
- 하나씩 조회하면서, flag 로 컨트롤
 */
public class Problem7 {

    static class Solution1 {
        public String solution(String s) {
            StringBuilder sb = new StringBuilder();
            String[] words = s.split(" ", -1);

            for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
                if (wordIdx > 0) {
                    sb.append(" ");
                }

                String word = words[wordIdx];
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (i % 2 == 0) {
                        c = Character.toUpperCase(c);
                    } else {
                        c = Character.toLowerCase(c);
                    }
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    static class Solution2 {
        public String solution(String s) {
            StringBuilder sb = new StringBuilder();
            boolean toUpper = true;

            for (char c : s.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    sb.append(c);
                    toUpper = true;
                } else {
                    if (toUpper) {
                        sb.append(Character.toUpperCase(c));
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    toUpper = !toUpper;
                }
            }

            return sb.toString();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        "try hello world",
                        "TrY HeLlO WoRlD"
                ),
                Arguments.of(
                        "a",
                        "A"
                ),
                Arguments.of(
                        "a  a ",
                        "A  A "
                ),
                Arguments.of(
                        " a  a ",
                        " A  A "
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, String expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
        assertThat(new Solution2().solution(s)).isEqualTo(expected);
    }
}
