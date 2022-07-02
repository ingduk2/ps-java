package ps.programmers.exercise.level1;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 문자열 내림차순으로 배치하기
 * https://programmers.co.kr/learn/courses/30/lessons/12917
 */
public class Q12917 {

    public String solution(String s) {
         return s.chars().boxed()
                .sorted(Comparator.reverseOrder())
                .map(i ->String.valueOf((char)(int)i))
                .collect(Collectors.joining());
    }

    public String solution2(String s) {
        return Stream.of(s.split(""))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(new Q12917().solution("Zbcdefg"));
        System.out.println(new Q12917().solution2("Zbcdefg"));
    }
}
