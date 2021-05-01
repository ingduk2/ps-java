package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * 최대공약수와 최소공배수
 * https://programmers.co.kr/learn/courses/30/lessons/12940
 */
public class Q12940 {

    private int gcd(int x, int y) {
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    private int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    public int[] solution(int n, int m) {
        int[] answer = new int[2];

        answer[0] = gcd(n, m);
        answer[1] = lcm(n, m);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Q12940().solution(3, 12)));
        System.out.println(Arrays.toString(new Q12940().solution(2, 5)));
    }
}
