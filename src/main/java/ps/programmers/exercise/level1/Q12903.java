package ps.programmers.exercise.level1;

/**
 * 가운데 글자 가져오기
 * https://programmers.co.kr/learn/courses/30/lessons/12903
 */
public class Q12903 {

    public String solution(String s) {
        String answer = "";

        int centerIdx = s.length() / 2;
        if (s.length() % 2 == 0) {
            answer = s.substring(centerIdx - 1, centerIdx + 1);
        } else {
            answer = String.valueOf(s.charAt(centerIdx));
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12903().solution("abcde"));
        System.out.println(new Q12903().solution("abcdefg"));
        System.out.println(new Q12903().solution("abcdefghi"));
        System.out.println(new Q12903().solution("qwer"));
        System.out.println(new Q12903().solution("qwertyuiui"));
    }
}
