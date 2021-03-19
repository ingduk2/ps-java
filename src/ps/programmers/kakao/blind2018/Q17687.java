package ps.programmers.kakao.blind2018;

/**
 * [3차] n진수 게임
 * https://programmers.co.kr/learn/courses/30/lessons/17687
 * Integer.toString(num, radix) 진법 변환.
 */
public class Q17687 {

    private String getBaseN(int num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int r = num % base;
            num /= base;

            String rest = String.valueOf(r);
            if (r > 9) {
                char c = (char) ('A' - 10 + r);
                rest = String.valueOf(c);
            }

            sb.insert(0, rest);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String solution(int n, int t, int m, int p) {

        //n 진법
        //t 미리 구할 숫자의 개수
        //m 게임에 참가하는 인원
        //p 튜브의 순서
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < t * m; i++) {
            numbers.append(getBaseN(i, n));
        }

        String s = numbers.toString();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int idx = p - 1 + i * m;
            result.append(s.charAt(idx));
        }

        return result.toString();
    }

    public static void main(String[] args) {

        int n = 2;
        int t = 4;
        int m = 2;
        int p = 1; //"0111"
        System.out.println(new Q17687().solution(n, t, m, p));

        n = 16;
        t = 16;
        m = 2;
        p = 1;//	"02468ACE11111111"
        System.out.println(new Q17687().solution(n, t, m, p));

        n = 16;
        t = 16;
        m = 2;
        p = 2; //	"13579BDF01234567"
        System.out.println(new Q17687().solution(n, t, m, p));
    }
}
