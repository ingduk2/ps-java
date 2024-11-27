package ps.become_a_successful_coding_test_candidate.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 32 영어 끝말잇기
- 마지막 사람 후에 다시 1번 부터 시작
- 이전에 등장했던 단너는 사용 할 수 없음
- 한 글자인 단어는 인정되지 않음

- 풀이
-- for words.length
--- 사람 번호 : i % n + 1
--- 차례 : i / n + 1
--- prevWord 의 마지막 글자와 currentWord 의 첫 번째 글자가 다르면 return
--- wordsSet 에 이미 단어가 있으면 return
-- 결과 [번호, 차례]
 */
public class Problem32 {

    static class Solution1 {
        public int[] solution(int n, String[] words) {
            Set<String> wordsSet = new HashSet<>();
            String prevWord = null;
            for (int i = 0; i < words.length; i++) {
                String currentWord = words[i];

                if (wordsSet.contains(currentWord) || checkRuleFail(prevWord, currentWord)) {
                    int peopleNum = i % n + 1;
                    int count = i / n + 1;
                    return new int[]{peopleNum, count};
                }

                prevWord = currentWord;
                wordsSet.add(currentWord);
            }

            return new int[]{0, 0};
        }

        private boolean checkRuleFail(String prevWord, String currentWord) {
            if (prevWord == null) {
                return false;
            }

            char lastWord = prevWord.charAt(prevWord.length() - 1);
            char firstWord = currentWord.charAt(0);

            return lastWord != firstWord;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        3,
                        new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"},
                        new int[]{3, 3}
                ),
                Arguments.of(
                        5,
                        new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"},
                        new int[]{0, 0}
                ),
                Arguments.of(
                        2,
                        new String[]{"hello", "one", "even", "never", "now", "world", "draw"},
                        new int[]{1, 3}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, String[] words, int[] expected) {
        assertThat(new Solution1().solution(n, words)).isEqualTo(expected);
    }
}
