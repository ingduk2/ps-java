package ps.programmers_coding_test.bruteforcesearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 18 - 모의고사 - Level 1

풀이
- 수포자 1, 2, 3 방식 배열 정의
- answers 를 for 돌면서, 각각의 방식만큼 % 계산해서 합을 구한다
- 가장 높은 점수 여럿 일 경우 오름차순 정렬
 */
public class Problem18 {

    static class Solution1 {

        private static class Supoja {
            private final int number;
            private final int[] pattern;
            private int answerCount;

            private Supoja(int number, int[] pattern) {
                this.number = number;
                this.pattern = pattern;
            }

            public void addAnswerCount() {
                answerCount++;
            }
        }

        public int[] solution(int[] answers) {
            List<Supoja> supojas = Arrays.asList(
                    new Supoja(1, new int[]{1, 2, 3, 4, 5}),
                    new Supoja(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5}),
                    new Supoja(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5})
            );

            for (int i = 0; i < answers.length; i++) {
                for (Supoja supoja : supojas) {
                    int[] pattern = supoja.pattern;
                    int patternLength = pattern.length;

                    if (answers[i] == pattern[i % patternLength]) {
                        supoja.addAnswerCount();
                    }
                }
            }

            int max = supojas.stream()
                    .map(it -> it.answerCount)
                    .max(Integer::compareTo)
                    .orElse(0);

            return supojas.stream()
                    .filter(it -> it.answerCount == max)
                    .sorted(Comparator.comparingInt(value -> value.number))
                    .map(value -> value.number)
                    .collect(Collectors.toList())
                    .stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Solution2 {

        private static final int[][] RULES = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        public int[] solution(int[] answers) {
            int[] corrects = new int[3];
            int max = 0;

            for (int problem = 0; problem < answers.length; problem++) {
                int answer = answers[problem];

                for (int person = 0; person < 3; person++) {
                    int picked = getPicked(person, problem);
                    if (answer == picked) {
                        if (++corrects[person] > max) {
                            max = corrects[person];
                        }
                    }
                }
            }

            final int maxCorrects = max;
            return IntStream.range(0, 3)
                    .filter(i -> corrects[i] == maxCorrects)
                    .map(i -> i + 1)
                    .toArray();
        }

        private int getPicked(int person, int problem) {
            int[] rule = RULES[person];
            int index = problem % rule.length;
            return rule[index];
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1}),
                Arguments.of(new int[]{1, 3, 2, 4, 2}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}, new int[]{1})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] answers, int[] expected) {
        assertThat(new Solution1().solution(answers)).isEqualTo(expected);
        assertThat(new Solution2().solution(answers)).isEqualTo(expected);
    }
}
