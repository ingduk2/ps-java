package ps.become_a_successful_coding_test_candidate.queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 17 카드 뭉치
- 원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용합니다.
- 한 번 사용한 카드는 다시 사용할 수 없습니다.
- 카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다.
- 기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없습니다.

풀이
- 큐
-- 전부 queue 에 넣는다.
-- 카드가 순서대로 사용되어야 하므로, goal 의 단어가 cards1, cards2 에 있는지 확인
-- 있으면 poll, 없으면 "No" return
-- goal 이 비어있으면 "Yes"

 */
public class Problem17 {

    static class Solution1 {
        public String solution(String[] cards1, String[] cards2, String[] goal) {
            Queue<String> goalQueue = new ArrayDeque<>(Arrays.asList(goal));
            Queue<String> cards1Queue = new ArrayDeque<>(Arrays.asList(cards1));
            Queue<String> cards2Queue = new ArrayDeque<>(Arrays.asList(cards2));

            while (!goalQueue.isEmpty()) {
                String goalWord = goalQueue.peek();

                if (!cards1Queue.isEmpty() && goalWord.equals(cards1Queue.peek())) {
                    cards1Queue.poll();
                    goalQueue.poll();
                } else if (!cards2Queue.isEmpty() && goalWord.equals(cards2Queue.peek())) {
                    cards2Queue.poll();
                    goalQueue.poll();
                } else {
                    return "No";
                }
            }

            return "Yes";
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"i", "drink", "water"},
                        new String[]{"want", "to"},
                        new String[]{"i", "want", "to", "drink", "water"},
                        "Yes"
                ),
                Arguments.of(
                        new String[]{"i", "water", "drink"},
                        new String[]{"want", "to"},
                        new String[]{"i", "want", "to", "drink", "water"},
                        "No"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] cards1, String[] cards2, String[] goal, String expected) {
        String result = new Solution1().solution(cards1, cards2, goal);
        assertThat(result).isEqualTo(expected);
    }
}
