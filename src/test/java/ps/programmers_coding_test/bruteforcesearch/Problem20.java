package ps.programmers_coding_test.bruteforcesearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 20 - 수식 최대화 - Level 2

풀이
- 연산자 조합을 만든다
- expression -> List<String> 으로 변환
- 위 조합별로 계산해서 가장 큰 절댓값을 구한다
  -    i-1 i i+1
  - ... 10 + 20 - 100 ...
  - ... - 100 ...
  - ... 30 - 100 ...
  - i-1, i, i+1 계산
  - remove(i - 1) 3번
  - add(i - 1, 값)
 */
public class Problem20 {

    static class Solution1 {

        private static final String[][] precedences = {
                "+-*".split(""),
                "+*-".split(""),
                "-+*".split(""),
                "-*+".split(""),
                "*+-".split(""),
                "*-+".split(""),
        };

        public long solution(String expression) {
            StringTokenizer tokenizer = new StringTokenizer(expression, "+-*", true);
            List<String> tokens = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                tokens.add(tokenizer.nextToken());
            }

            long max = 0;
            for (String[] precedence : precedences) {
                long value = Math.abs(calculate(new ArrayList<>(tokens), precedence));
                if (value > max) {
                    max = value;
                }
            }

            return max;
        }

        private long calculate(List<String> tokens, String[] precedence) {
            for (String op : precedence) {
                for (int i = 0; i < tokens.size(); i++) {
                    if (tokens.get(i).equals(op)) {
                        long a = Long.parseLong(tokens.get(i - 1));
                        long b = Long.parseLong(tokens.get(i + 1));
                        long result = calculate(a, b, op);
                        tokens.remove(i - 1);
                        tokens.remove(i - 1);
                        tokens.remove(i - 1);
                        tokens.add(i - 1, String.valueOf(result));
                        i -= 2;
                    }
                }
            }
            return Long.parseLong(tokens.get(0));
        }

        private long calculate(long a, long b, String op) {
            return switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> 0;
            };
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("100-200*300-500+20", 60420),
                Arguments.of("50*6-3*2", 300)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String expression, long expected) {
        assertThat(new Solution1().solution(expression)).isEqualTo(expected);
    }
}
