package ps.programmers.exercise.level1;

/**
 * 짝수와 홀수
 * https://programmers.co.kr/learn/courses/30/lessons/12937
 */
public class Q12937 {

    public String solution(int num) {
        String answer;

        if (num % 2 == 0) {
            answer = "Even";
        } else {
            answer = "Odd";
        }

        return answer;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Q12937().solution(i));
        }
    }
}
