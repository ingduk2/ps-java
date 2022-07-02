package ps.programmers.exercise.level1;

/**
 * 2016년
 * https://programmers.co.kr/learn/courses/30/lessons/12901
 */
public class Q12901 {

    public String solution(int a, int b) {
        String[] days = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
        int[] months = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int sum = 0;
        for (int i = 1; i < a; i++) {
            sum += months[i];
        }
        sum += b - 1;

        return days[(sum + 5) % 7];
    }

    public static void main(String[] args) {
        System.out.println(new Q12901().solution(5, 25));
        System.out.println(new Q12901().solution(1, 8));
    }
}
