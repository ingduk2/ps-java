package ps.programmers.exercise;

/**
 * JadenCase 문자열 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12951
 */
public class Q12951 {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        char first = s.charAt(0);
        if (Character.isAlphabetic(first)) {
            first = Character.toUpperCase(first);
        }
        sb.append(first);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

            if (s.charAt(i - 1) == ' ') {
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }


            sb.append(c);
        }

        return sb.toString();
    }

    public String solution2(String s) {
        StringBuilder sb = new StringBuilder();

        boolean isUpper = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 || isUpper) {
                c = Character.toUpperCase(c);
                isUpper = false;
            } else {
                c = Character.toLowerCase(c);
            }

            if (c == ' ') isUpper = true;

            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Q12951().solution("3people 1unFollowed me"));
        System.out.println(new Q12951().solution2("3people 1unFollowed me"));
        System.out.println(new Q12951().solution("for the last week"));
        System.out.println(new Q12951().solution2("for the last week"));
    }
}
