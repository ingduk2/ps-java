package ps.leetcode.easy.q9_palindromnumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/palindrome-number/
 * forward (int -> str)
 * backward (forward.reverse)
 * 비교
 */
public class Q9_PalindromeNumber_Convert {

    public boolean isPalindrome(int x) {
        String forward = Integer.toString(x);
        String backward = new StringBuilder(forward).reverse().toString();

        return forward.equals(backward);
    }

    Q9_PalindromeNumber_Convert problem;

    @BeforeEach
    void setUp() {
        problem = new Q9_PalindromeNumber_Convert();
    }

    @ParameterizedTest
    @ValueSource(ints = {121, 12321, 12221, 0, 234432})
    void solutionTrue(int x) {
        assertThat(problem.isPalindrome(x)).isTrue();
    }

    @ParameterizedTest()
    @ValueSource(ints = {-121, 10, 12345})
    void solutionFalse(int x) {
        assertThat(problem.isPalindrome(x)).isFalse();
    }
}
