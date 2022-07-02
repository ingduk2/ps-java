package ps.programmers.exercise.level1;

/**
 * 하샤드수
 * https://programmers.co.kr/learn/courses/30/lessons/12947
 */
public class Q12947 {

    public boolean solution(int x) {
        int xOri = x;
        int sum = 0;

        while (x > 0) {
            int r = x % 10;
            x /= 10;
            sum += r;
        }

        if (xOri % sum == 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Q12947().solution(10));
        System.out.println(new Q12947().solution(12));
        System.out.println(new Q12947().solution(11));
        System.out.println(new Q12947().solution(13));
    }
}
