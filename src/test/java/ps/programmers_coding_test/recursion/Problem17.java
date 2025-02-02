package ps.programmers_coding_test.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 17 - 모음 사전 - Level 2

풀이
- 상태
  - (word)
  - word 로 시작하는 모든 단어
- 종료 조건
  - (길이가 5인 word) = word
- 점화식
  - (word) = [word] + (word + 'A') + (word + 'E') + (word + 'I') + (word + 'O') + (word + 'U')
 */
public class Problem17 {

    static class Solution1 {
        private static final char[] CHARS = "AEIOU".toCharArray();

        public int solution(String word) {
            List<String> words = generate("");
            return words.indexOf(word);
        }

        private List<String> generate(String word) {
            List<String> words = new ArrayList<>();
            words.add(word);

            if (word.length() == 5) return words;

            for (char c : CHARS) {
                words.addAll(generate(word + c));
            }

            return words;
        }
    }

    static class Solution2 {
        private static final char[] CHARS = "AEIOU".toCharArray();

        public int solution(String word) {
            List<String> words = new ArrayList<>();
            generate("", words);
            return words.indexOf(word);
        }

        public void generate(String word, List<String> words) {
            words.add(word);

            if (word.length() == 5) return;

            for (char c : CHARS) {
                generate(word + c, words);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("AAAAE", 6),
                Arguments.of("AAAE", 10),
                Arguments.of("I", 1563),
                Arguments.of("EIO", 1189)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String word, int expected) {
        assertThat(new Solution1().solution(word)).isEqualTo(expected);
        assertThat(new Solution2().solution(word)).isEqualTo(expected);
    }
}
