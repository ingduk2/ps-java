package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 8 문자열 압축 - Level 2

풀이
- 1. 1부터 입력 문자열 s 의 길이만큼 자를 문자열의 길이를 설정하며 반복
- 2. 설정된 길이만큼 문자열을 잘라 낸 token 의 배열 생성
- 3. 문자열을 비교하며 token 의 배열을 하나의 문자열로 압축
- 4. 1~3 과정으로 압축된 문자열 중 가장 짧은 길이 반환
 */
public class Problem8 {

    static class Solution1 {
        public int solution(String s) {
            int min = Integer.MAX_VALUE;
            for (int length = 1; length <= s.length(); length++) {
                List<String> tokens = getStringsByLength(s, length);
                int compressedLength = getCompressedLength(tokens);
                min = Math.min(min, compressedLength);
            }

            return min;
        }

        private List<String> getStringsByLength(String s, int length) {
            List<String> tokens = new ArrayList<>();
            for (int startIndex = 0; startIndex < s.length(); startIndex += length) {
                int endIndex = startIndex + length;
                if (endIndex > s.length()) {
                    endIndex = s.length();
                }
                tokens.add(s.substring(startIndex, endIndex));
            }
            return tokens;
        }

        private int getCompressedLength(List<String> tokens) {
            StringBuilder sb = new StringBuilder();
            String before = tokens.get(0);
            int count = 1;
            for (int i = 1; i < tokens.size(); i++) {
                String token = tokens.get(i);
                if (before.equals(token)) {
                    count++;
                } else {
                    if (count == 1) {
                        sb.append(before);
                    } else {
                        sb.append(count).append(before);
                    }

                    count = 1;
                    before = token;
                }
            }

            if (count == 1) {
                sb.append(before);
            } else {
                sb.append(count).append(before);
            }

            return sb.length();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("aabbaccc", 7),
                Arguments.of("ababcdcdababcdcd", 9),
                Arguments.of("abcabcdede", 8),
                Arguments.of("abcabcabcabcdededededede", 14),
                Arguments.of("xababcdcdababcdcd", 17),
                Arguments.of("abcdeabcde", 6),
                Arguments.of("aaaaa", 2), // 경계값 케이스
                Arguments.of("a", 1),     // 최소 문자열
                Arguments.of("ab", 2),    // 반복되지 않는 문자열
                Arguments.of("aabb", 4)   // 전체 길이만큼 반복
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
    }
}
