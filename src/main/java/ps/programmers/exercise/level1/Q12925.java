package ps.programmers.exercise.level1;

/**
 * 문자열을 정수로 바꾸기
 * https://programmers.co.kr/learn/courses/30/lessons/12925
 *
 * Integer.parseInt + 기호, - 기호도 가능.
 */
public class Q12925 {

    private int getStringtoInt(String s) {
        int num = 0;
        int e = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += e * Character.getNumericValue(s.charAt(i));
            e *= 10;
        }
        return num;
    }

    public int solution(String s) {
        int answer = 0;

        char c = s.charAt(0);
        if (Character.isDigit(c)) {
            answer = getStringtoInt(s);
        } else {
            if (c == '+') {
                answer = getStringtoInt(s.substring(1));
            } else {
                answer = getStringtoInt(s.substring(1)) * -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12925().solution("+1234"));
        System.out.println(new Q12925().solution("-1234"));
        System.out.println(new Q12925().solution("1234"));
    }
}
