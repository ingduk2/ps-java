package ps.programmers.exercise.level1;

/**
 * 문자열 내 p와 y의 개수
 * https://programmers.co.kr/learn/courses/30/lessons/12916
 */
public class Q12916 {

    boolean solution(String s) {
        s = s.toLowerCase();

        int pCnt = 0;
        int yCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p') {
                pCnt++;
            } else if (s.charAt(i) == 'y') {
                yCnt++;
            }
        }

        if (pCnt == yCnt || pCnt + yCnt == 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Q12916().solution("pPoooyY"));
        System.out.println(new Q12916().solution("Pyy"));
    }
}
