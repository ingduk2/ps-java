package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * - 전부 소문자로
 * - alphanumeric 인 경우만
 *
 * 1. String 전체를 뒤집어서 비교 - 느릴듯
 * 2. pointer 로 이동하면서 비교
 */
public class Q1_ValidPalindrome {
    public boolean isPalindrome(String s) {
        String filtered = filterAlphaNumeric(s.toLowerCase());

        int start = 0;
        int end = filtered.length() - 1;
        while (start < end) {
            char startChar = filtered.charAt(start);
            char endChar = filtered.charAt(end);
            if (startChar == endChar) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return true;
    }

    private static String filterAlphaNumeric(String s) {
        return s.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    @Test
    void test() {
        Q1_ValidPalindrome problem = new Q1_ValidPalindrome();
        assertThat(problem.isPalindrome("A man, a plan, a canal: Panama")).isTrue();
        assertThat(problem.isPalindrome("race a car")).isFalse();
        assertThat(problem.isPalindrome(" ")).isTrue();
        assertThat(problem.isPalindrome("aba")).isTrue();
    }
}
