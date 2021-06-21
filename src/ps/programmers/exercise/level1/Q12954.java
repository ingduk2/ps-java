package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * x만큼 간격이 있는 n개의 숫자
 * https://programmers.co.kr/learn/courses/30/lessons/12954
 */
public class Q12954 {

    public long[] solution(int x, int n) {
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long num = (long)x * (i + 1);
            answer[i] = num;
        }

        return answer;
    }

    public static void main(String[] args) {
        long a = (long)1000000 * 1000000;
        System.out.println(a);
        System.out.println(Arrays.toString(new Q12954().solution(2, 5)));
        System.out.println(Arrays.toString(new Q12954().solution(4, 3)));
        System.out.println(Arrays.toString(new Q12954().solution(-4, 1)));
        System.out.println(Arrays.toString(new Q12954().solution(-10, 100)));
    }
}
