package ps.programmers.exercise;

/**
 * 서울에서 김서방 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/12919
 */
public class Q12919 {

    public String solution(String[] seoul) {
        int idx = 0;
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                idx = i;
                break;
            }
        }
        return "김서방은 " + idx + "에 있다";
    }

    public static void main(String[] args) {
        String[] seoul = {"Jane", "Kim"};
        System.out.println(new Q12919().solution(seoul));
    }
}
