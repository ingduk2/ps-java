package ps.programmers.exercise.level1;

/**
 * 문자열 다루기 기본
 * https://programmers.co.kr/learn/courses/30/lessons/12918
 */
public class Q12918 {

    private boolean isDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public boolean solution(String s) {
        int length = s.length();
        if ((length == 4 || length == 6) && isDigit(s)) return true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Q12918().solution("a234"));
        System.out.println(new Q12918().solution("1234"));
    }
}
