package ps.programmers.summer_winter_coding2018;

/**
 * 점프와 순간 이동
 * https://programmers.co.kr/learn/courses/30/lessons/12980
 * n의 2진수 1의 개수와 같음.
 */
public class Q12980 {

    public int solution(int n) {
        int ans = 0;

        while (n > 0) {
            int rest = n % 2;
            n /= 2;
            ans += rest;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Q12980().solution(5));
        System.out.println(new Q12980().solution(6));
        System.out.println(new Q12980().solution(5000));
    }
}
