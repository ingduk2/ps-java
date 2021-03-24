package ps.programmers.exercise.level1;

/**
 * 시저 암호
 * https://programmers.co.kr/learn/courses/30/lessons/12926
 */
public class Q12926 {

    public String solution(String s, int n) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ' ') {
                if (Character.isUpperCase(c)) {
                    if (c + n > 'Z') {
                        c = (char) ('A' + (c + n) % 'Z' - 1);
                    } else {
                        c += n;
                    }
                } else {
                    if (c + n > 'z') {
                        c = (char) ('a' + (c + n) % 'z' - 1);
                    } else {
                        c += n;
                    }
                }
            }
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "AB";
        int n = 1;	//BC
        System.out.println(new Q12926().solution(s, n));

        s = "z";
        n = 1; //	a
        System.out.println(new Q12926().solution(s, n));

        s = "a B z";
        n = 4;  //e F d
        System.out.println(new Q12926().solution(s, n));
    }
}
