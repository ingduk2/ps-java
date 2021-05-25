package ps.programmers.exercise.level1;

/**
 * 핸드폰 번호 가리기
 * https://programmers.co.kr/learn/courses/30/lessons/12948
 */
public class Q12948 {

    public String solution(String phone_number) {
        StringBuilder sb = new StringBuilder();
        int phoneNumberLen = phone_number.length();
        for (int i = 0; i < phoneNumberLen; i++) {
            if (i < phoneNumberLen - 4) {
                sb.append("*");
            } else {
                sb.append(phone_number.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Q12948().solution("01033334444"));
        System.out.println(new Q12948().solution("027778888"));
    }
}
