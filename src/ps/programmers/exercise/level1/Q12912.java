package ps.programmers.exercise.level1;

/**
 * 두 정수 사이의 합
 * https://programmers.co.kr/learn/courses/30/lessons/12912
 */
public class Q12912 {

    public long solution(int a, int b) {
        long answer = 0;

        int start = a;
        int end = b;
        if (a > b) {
            start = b;
            end = a;
        }

        for (int i = start; i <= end; i++) {
            answer += i;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12912().solution(3, 5));
        System.out.println(new Q12912().solution(3, 3));
        System.out.println(new Q12912().solution(5, 3));
    }
}
