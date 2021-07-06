package ps.programmers.month_code_challenge2;

/**
 * 약수의 개수와 덧셈
 * https://programmers.co.kr/learn/courses/30/lessons/77884
 */
public class Q77884 {

    private static int getCount(int num) {
        int cnt = 0;
        for (int i = 1; i * i<= num; i++) {
            if (num % i == 0) {
               cnt++;
               if (i * i < num) cnt++;
            }
        }
        return cnt;
    }

    public int solution(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            int count = getCount(i);
            if (count % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q77884().solution(13, 17));
        System.out.println(new Q77884().solution(24, 27));
    }

}
