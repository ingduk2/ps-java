package ps.programmers.exercise;

/**
 * 2 x n 타일링
 * https://programmers.co.kr/learn/courses/30/lessons/12900
 */
public class Q12900 {

    public int solution(int n) {

        int[] d = new int[n + 2];
        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 1000000007;
        }

        return d[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(new Q12900().solution(i));
        }

    }
}
