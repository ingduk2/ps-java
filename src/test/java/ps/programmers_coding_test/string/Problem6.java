package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 6 시저 암호 - Level 1

풀이
- s 를 char 로 for
- 알파벳인 경우 ascii 를 + n 한다. (z -> a)
- 알파벳이 아닌 경우 그대로
 */
public class Problem6 {

    static class Solution1 {
        private static final int UPPER_Z = 'Z';
        private static final int UPPER_A = 'A';
        private static final int LOWER_Z = 'z';
        private static final int LOWER_A = 'a';

        public String solution(String s, int n) {
            StringBuilder sb = new StringBuilder();

            for (char c : s.toCharArray()) {
                int caesarCipher = c;
                if (Character.isAlphabetic(c)) {
                    if (Character.isUpperCase(c)) {
                        caesarCipher = getCaesarCipher(c, n, UPPER_Z, UPPER_A);
                    } else {
                        caesarCipher = getCaesarCipher(c, n, LOWER_Z, LOWER_A);
                    }
                }

                sb.append(Character.toString(caesarCipher));
            }
            return sb.toString();
        }

        private int getCaesarCipher(char c, int n, int z, int a) {
            int mod = (c + n) % z;
            if (c + n > z) {
                return a + mod - 1;
            } else if (c + n == z) {
                return z;
            } else {
                return mod;
            }
        }
    }

    static class Solution2 {
        public String solution(String s, int n) {
            StringBuilder sb = new StringBuilder();

            for (char c : s.toCharArray()) {
                sb.append(push(c, n));
            }

            return sb.toString();
        }

        private char push(char c, int n) {
            if (!Character.isAlphabetic(c)) return c;

            int offset = Character.isUpperCase(c) ? 'A' : 'a';
            int position = c - offset;
            position = (position + n) % ('Z' - 'A' + 1);
            return (char) (offset + position);
        }
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("AB", 1, "BC"),
                Arguments.of("z", 1, "a"),
                Arguments.of("a B z", 4, "e F d"),
                Arguments.of("a", 24, "y"),
                Arguments.of("a", 25, "z")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int n, String expected) {
        assertThat(new Solution1().solution(s, n)).isEqualTo(expected);
        assertThat(new Solution2().solution(s, n)).isEqualTo(expected);
    }
}
