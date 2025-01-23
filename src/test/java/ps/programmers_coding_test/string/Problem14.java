package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 14 - 신규 아이디 추천 - Level 1

풀이
- new_id 모든 대문자 -> 소문자로 치환
- new_id 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자를 제거
- new_id 마침표 2번 이상 연속된 부분 -> 하나의 마침표로 치환
- new_id 마침표 가 처음이나 끝에 위치한다면 제거
- new_id 빈 문자열이라면, new_id 에 "a" 를 대입
- new_id 길이 >= 16 첫 15개의 문자를 제외한 나머지는 모두 제거, 제거 후 마침표가 끝에 위치한다면, 마지막 마침표 제거
- new_id 길이가 2자 이하라면, new_id 의 마지막 문자를 new_id 의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
public class Problem14 {

    static class Solution1 {
        public String solution(String new_id) {
            new_id = step1(new_id);
            new_id = step2(new_id);
            new_id = step3(new_id);
            new_id = step4(new_id);
            new_id = step5(new_id);
            new_id = step6(new_id);
            new_id = step7(new_id);

            return new_id;
        }

        private String step1(String newId) {
            return newId.toLowerCase();
        }

        private String step2(String newId) {
            StringBuilder sb = new StringBuilder();
            for (char c : newId.toCharArray()) {
                if (Character.isAlphabetic(c) ||
                        Character.isDigit(c) ||
                        c == '-' || c == '_' || c == '.'
                ) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }

        private String step3(String newId) {
            StringBuilder sb = new StringBuilder();

            boolean lastWasDot = false;
            for (char c : newId.toCharArray()) {
                if (c == '.') {
                    if (!lastWasDot) {
                        sb.append(c);
                        lastWasDot = true;
                    }
                } else {
                    sb.append(c);
                    lastWasDot = false;
                }
            }

            return sb.toString();
        }

        private String step4(String newId) {
            if (getFirstChar(newId) == '.') {
                newId = newId.substring(1);
            }

            if (newId.isEmpty()) {
                return newId;
            }

            if (getLastChar(newId) == '.') {
                newId = newId.substring(0, newId.length() - 1);
            }

            return newId;
        }

        private String step5(String newId) {
            if (newId.isEmpty()) {
                return "a";
            }

            return newId;
        }

        private String step6(String newId) {
            if (newId.length() >= 16) {
                String str = newId.substring(0, 15);
                if (getLastChar(str) == '.') {
                    str = str.substring(0, 14);
                }

                return str;
            }

            return newId;
        }

        private String step7(String newId) {
            if (newId.length() <= 2) {
                StringBuilder sb = new StringBuilder(newId);
                char lastWord = getLastChar(newId);

                while (sb.length() < 3) {
                    sb.append(lastWord);
                }

                return sb.toString();
            }

            return newId;
        }

        private static char getFirstChar(String str) {
            return str.charAt(0);
        }

        private static char getLastChar(String str) {
            return str.charAt(str.length() - 1);
        }
    }

    static class Solution2 {
        public String solution(String new_id) {
            new_id = new_id.toLowerCase();
            new_id = new_id.replaceAll("[^a-z0-9\\-_.]", "");
            new_id = new_id.replaceAll("\\.+", ".");
            new_id = new_id.replaceAll("^\\.+|\\.+$", "");
            if (new_id.isEmpty()) new_id = "a";
            if (new_id.length() >= 16) {
                new_id = new_id.substring(0, 15);
                new_id = new_id.replaceAll("\\.+$", "");
            }
            while (new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length() - 1);
            }

            return new_id;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("...!@BaT#*..y.abcdefghijklm", "bat.y.abcdefghi"),
                Arguments.of("z-+.^.", "z--"),
                Arguments.of("=.=", "aaa"),
                Arguments.of("123_.def", "123_.def"),
                Arguments.of("abcdefghijklmn.p", "abcdefghijklmn")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String new_id, String expected) {
        assertThat(new Solution1().solution(new_id)).isEqualTo(expected);
        assertThat(new Solution2().solution(new_id)).isEqualTo(expected);
    }
}
