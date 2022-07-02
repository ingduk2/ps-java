package ps.programmers.exercise.level1;

/**
 * 정수 제곱근 판별
 * https://programmers.co.kr/learn/courses/30/lessons/12934
 */
public class Q12934 {

    public long solution(long n) {
        double sqrt = Math.sqrt(n);
        if (Math.ceil(sqrt) == Math.floor(sqrt)) {
            return (long) Math.pow(sqrt + 1, 2);
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Q12934().solution(121));
        System.out.println(new Q12934().solution(3));
        System.out.println(new Q12934().solution(50000000000000l));
    }
}
