package ps.programmers.month_code_challenge2;

/**
 * 음양 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/76501
 */
public class Q76501 {

    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i = 0; i < absolutes.length; i++) {
            answer += absolutes[i] * (signs[i] ? 1 : -1);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ab = {4,7,12};
        boolean[] si = {true,false,true};
        System.out.println(new Q76501().solution(ab, si));

        ab = new int[]{1, 2, 3};
        si = new boolean[]{false, false, true};
        System.out.println(new Q76501().solution(ab, si));
    }
}
