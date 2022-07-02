package ps.programmers.exercise;

/**
 * 피보나치 수
 * dp 문제.
 * https://programmers.co.kr/learn/courses/30/lessons/12945
 */
public class Q12945 {

    public int solution(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % 1234567;
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(new Q12945().solution(3));
        System.out.println(new Q12945().solution(5));
    }
}
