package ps.programmers.summer_winter_coding2019;

/**
 * 멀쩡한 사각형
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 */
public class Q62048 {

    private int gcd(int a, int b){
        while (b != 0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    public long solution(int w, int h) {
        System.out.println(gcd(w, h) + " " + gcd(h, w));
        int gcd = gcd(w, h);

        return ((long)w * (long)h) - ((w/gcd + h/gcd - 1) * gcd);
    }

    public static void main(String[] args) {
        System.out.println(new Q62048().solution(8, 12));
        System.out.println(new Q62048().solution(3, 3));
    }
}
