package ps.programmers.exercise.level1;

/**
 * 콜라츠 추측
 * https://programmers.co.kr/learn/courses/30/lessons/12943
 */
public class Q12943 {

    private int collatz(int num, int answer) {
        if (num == 1) return answer;
        if (answer == 500) return -1;

        if (num % 2 == 0) {
            num /= 2;
        } else if (num % 2 == 1) {
            num = num * 3 + 1;
        }
        answer++;
        return collatz(num, answer);
    }

    public int solution(int num) {
        int answer = 0;
        return collatz(num, answer);
    }

    public static void main(String[] args) {
        System.out.println(new Q12943().solution(6));
        System.out.println();
        System.out.println(new Q12943().solution(16));
        System.out.println();
        System.out.println(new Q12943().solution(626331));
    }
}
