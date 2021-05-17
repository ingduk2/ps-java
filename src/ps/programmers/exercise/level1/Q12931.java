package ps.programmers.exercise.level1;

/**
 * 자릿수 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/12931
 */
public class Q12931 {

    public int solution(int n) {
        int answer = 0;

        while (n > 0) {
            int rest = n % 10;
            n /= 10;
            answer += rest;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12931().solution(123));
        System.out.println(new Q12931().solution(987));
    }
}
