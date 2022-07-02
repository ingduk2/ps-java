package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * 이상한 문자 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12930
 *
 * split(regex, limit) limit -1 을 주면 마지막 빈값 처리
 * 없으면 무시해버림.
 *
 * split("") 으로 한글자씩 처리도 가능.
 */
public class Q12930 {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        String[] words = s.split(" ", -1);
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i % 2 == 0) {
                    c = Character.toUpperCase(c);
                } else {
                    c = Character.toLowerCase(c);
                }
                sb.append(c);
            }
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Q12930().solution("  try hello  world  "));
        System.out.println(new Q12930().solution("tryworldd"));
    }
}
