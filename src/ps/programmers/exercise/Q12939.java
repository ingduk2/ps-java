package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 최댓값과 최솟값
 * https://programmers.co.kr/learn/courses/30/lessons/12939
 */
public class Q12939 {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        String[] strArr = s.split(" ");
        int max = Arrays.stream(strArr).mapToInt(Integer::parseInt).max().orElse(0);
        int min = Arrays.stream(strArr).mapToInt(Integer::parseInt).min().orElse(0);

        sb.append(min).append(" ").append(max);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Q12939().solution("1 2 3 4"));
        System.out.println(new Q12939().solution("-1 -2 -3 -4"));
        System.out.println(new Q12939().solution("-1 -1"));
    }
}
