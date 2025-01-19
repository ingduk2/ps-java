package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 12 숫자 문자열과 영단어 - Level 1

풀이 1
- 1. 입력에서 영어가 나오면, countMap 과 매치할 때까지 저장해서 숫자로 변환
- 2. 위 문자를 숫자로 변환해서 합친다
- 3. 숫자가 나오면 한 글자만 가져와서 합친다

풀이 2
- 1. 영단어 배열을 순회하며 입력 문자열에 등장하는 모든 영단어를 치환
 */
public class Problem12 {

    static class Solution1 {
        private Map<String, Integer> countMap = Map.of(
                "zero", 0,
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        public int solution(String s) {
            StringBuilder result = new StringBuilder();

            StringBuilder strNum = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    result.append(c);
                } else {
                    strNum.append(c);
                    if (countMap.containsKey(strNum.toString())) {
                        result.append(countMap.get(strNum.toString()));
                        strNum.setLength(0);
                    }
                }
            }

            return Integer.parseInt(result.toString());
        }
    }

    static class Solution2 {

        private static final String[] words = {
                "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };

        public int solution(String s) {
            for (int i = 0; i < words.length; i++) {
                s = s.replace(words[i], Integer.toString(i));
            }

            return Integer.parseInt(s);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("one4seveneight", 1478),
                Arguments.of("23four5six7", 234567),
                Arguments.of("2three45sixseven", 234567),
                Arguments.of("123", 123)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
        assertThat(new Solution2().solution(s)).isEqualTo(expected);
    }
}
