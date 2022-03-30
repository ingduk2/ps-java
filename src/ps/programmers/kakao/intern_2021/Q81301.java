package ps.programmers.kakao.intern_2021;

import java.util.Map;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/81301
 * 숫자 문자열과 영단어
 * - 영어를 숫자로 바꾸는것
 * 1. 영어, 숫자 map 준비
 * 2. 하나씩 읽으면서 숫자면 answer add,
 * 3. 영어면 하나씩 읽으면서 map의 키와 같은지 확인 후 다나오면 answer add.
 * 4. 끝까지
 */
public class Q81301 {

    Map<String, Integer> countMap = Map.of(
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
        StringBuilder answer = new StringBuilder();

        char[] chars = s.toCharArray();
        StringBuilder englishStr = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                appendNumber(answer, englishStr, c);
            } else {
                appendEnglishToNumber(answer, englishStr, c);
            }
        }

        return Integer.parseInt(answer.toString());
    }

    private void appendEnglishToNumber(StringBuilder answer, StringBuilder englishStr, char c) {
        englishStr.append(c);
        if (countMap.containsKey(englishStr.toString())) {
            answer.append(countMap.get(englishStr.toString()));
            englishStr.setLength(0);
        }
    }

    private void appendNumber(StringBuilder answer, StringBuilder englishStr, char c) {
        answer.append(c);
        englishStr.setLength(0);
    }

    public static void main(String[] args) {
        String s = "one4seveneight";
        int result = 1478;
        System.out.println(new Q81301().solution(s) == result);

        s = "23four5six7";
        result = 234567;
        System.out.println(new Q81301().solution(s) == result);

        s = "2three45sixseven";
        result = 234567;
        System.out.println(new Q81301().solution(s) == result);

        s = "123";
        result = 123;
        System.out.println(new Q81301().solution(s) == result);
    }
}
