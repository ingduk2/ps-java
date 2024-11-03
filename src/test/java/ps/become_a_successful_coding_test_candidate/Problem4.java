package ps.become_a_successful_coding_test_candidate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 04 모의고사
https://school.programmers.co.kr/learn/courses/30/lessons/42840

가장 많은 문제를 맞힌 사람이 누구인지

제한 조건
    시험은 최대 10,000 문제로 구성되어있습니다. -> O(N^2)
    문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
    가장 높은 점수를 받은 사람이 여럿일 경우, return 하는 값을 오름차순 정렬해주세요.

풀이
- 수포자 1 : 1,2,3,4,5 ...
- 수포자 2 : 2,1,2,3,2,4,2,5 ...
- 수포자 3 : 3,3,1,1,2,2,4,4,5,5 ...
- 수포자들을 답인지 하나씩 검사
- 결과를 더한다
- 정답자 여러명은 오름차순 정렬

 */
public class Problem4 {

    static class Solution1 {
        private final List<Supoja> supojas = List.of(
                new Supoja(1, new int[]{1, 2, 3, 4, 5}),
                new Supoja(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5}),
                new Supoja(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5})
        );

        private static class Supoja {
            private int number;
            private int result;
            private int[] solves;

            public Supoja(int number, int[] solves) {
                this.number = number;
                this.result = 0;
                this.solves = solves;
            }

            public boolean isAnswer(int idx, int answer) {
                return solves[idx] == answer;
            }

            public void addResult() {
                result++;
            }
        }

        public int[] solution(int[] answers) {
            for (int i = 0; i < answers.length; i++) {
                int answer = answers[i];

                for (Supoja supoja : supojas) {
                    int idx = i % supoja.solves.length;
                    if (supoja.isAnswer(idx, answer)) {
                        supoja.addResult();
                    }
                }
            }

            int max = supojas.stream()
                    .mapToInt(it -> it.result)
                    .max()
                    .orElseThrow();

            List<Supoja> maxList = supojas.stream()
                    .filter(it -> it.result == max)
                    .collect(Collectors.toList());

            return maxList.stream()
                    .map(it -> it.number)
                    .sorted()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
    }

    static class Solution2 {
        public int[] solution(int[] answers) {
            int[][] pattern = {
                    {1, 2, 3, 4, 5},
                    {2, 1, 2, 3, 2, 4, 2, 5},
                    {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
            };

            int[] scores = new int[3];

            for (int i = 0; i < answers.length; i++) {
                for (int j = 0; j < pattern.length; j++) {
                    int answer = answers[i];
                    int idx = i % pattern[j].length;

                    if (answer == pattern[j][idx]) {
                        scores[j]++;
                    }
                }
            }

            int maxScore = Arrays.stream(scores).max().getAsInt();

            List<Integer> answer = new ArrayList<>();
            for (int i = 0; i < scores.length; i++) {
                if (scores[i] == maxScore) {
                    answer.add(i + 1);
                }
            }

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1}),
                Arguments.of(new int[]{1, 3, 2, 4, 2}, new int[]{1, 2, 3})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] answers, int[] expected) {
        int[] result = new Solution1().solution(answers);
        assertThat(result).isEqualTo(expected);

        int[] result2 = new Solution2().solution(answers);
        assertThat(result2).isEqualTo(expected);
    }
}
