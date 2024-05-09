package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * - 가장 긴 palindromic substring 찾기
 *
 * 1.
 * - 동시에 start, end 를 줄이는 것이 아니라 end 줄이고, start 늘리는 식으로 전체 확인
 * - 문자열은 어떻게 알지?, 사이 범위는 체크가 안된다
 * 2.
 * - 팰린드롬을 찾으면 확장하는 방식
 * - 2칸짜리(짝수), 3칸짜리(홀수) 투 포인터를 오른쪽으로 이동시키다가 펠린드롬을 발견하면 양쪽으로 확장
 * - 가장 긴 시작점, 끝점 저장
 * - substring()
 * -- ac 인 경우에 a 가 나와야 해서 1칸 짜리도 추가
 */
public class Q6_LongestPalindromicSubstring {

    static class TwoPointer {
        private int left;
        private int maxLen;

        public String longestPalindrome(String s) {
            if (s.length() <= 1) return s;

            for (int i = 0; i < s.length() - 1; i++) {
                expandPalindrome(s, i, i);
                expandPalindrome(s, i, i + 1);
                expandPalindrome(s, i, i + 2);
            }

            return s.substring(left, left + maxLen);
        }

        private void expandPalindrome(String s, int start, int end) {
            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                int len = end - start + 1;
                if (maxLen < len) {
                    left = start;
                    maxLen = len;
                }

                start--;
                end++;
            }
        }
    }

    static class BruteForce {
        public String longestPalindrome(String s) {
            if (s.length() <= 1)
                return s;

            int maxLen = 1;
            String maxStr = s.substring(0, 1);
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + maxLen; j <= s.length(); j++) {
                    if (isPalindrome(s.substring(i, j))) {
                        if (maxLen < j - i) {
                            maxLen = j - i;
                            maxStr = s.substring(i, j);
                        }
                    }
                }
            }

            return maxStr;
        }

        private boolean isPalindrome(String str) {
            int start = 0;
            int end = str.length() - 1;

            while (start < end) {
                if (str.charAt(start) == str.charAt(end)) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("babad", "bab"),
                Arguments.of("cbbd", "bb"),
                Arguments.of("a", "a"),
                Arguments.of("aaaaaaaaa", "aaaaaaaaa"),
                Arguments.of("ac", "a"),
                Arguments.of("bb", "bb")
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(String input, String expected) {
        // TwoPointer
        String result = new TwoPointer().longestPalindrome(input);
        assertThat(result).isEqualTo(expected);

        // BruteForce
        String result2 = new BruteForce().longestPalindrome(input);
        assertThat(result2).isEqualTo(expected);
    }
}
