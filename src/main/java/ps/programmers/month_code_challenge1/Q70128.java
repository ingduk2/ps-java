package ps.programmers.month_code_challenge1;

/**
 * 내적
 * https://programmers.co.kr/learn/courses/30/lessons/70128
 */
public class Q70128 {

    public int solution(int[] a, int[] b) {
        int answer = 0;

        int len = a.length;
        for (int i = 0; i < len; i++) {
            answer += a[i] * b[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {-3, -1, 0, 2};
        System.out.println(new Q70128().solution(a, b));

        a = new int[]{-1, 0, 1};
        b = new int[]{1, 0, -1};
        System.out.println(new Q70128().solution(a, b));
    }
}
