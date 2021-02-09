package ps.programmers.exercise;

/**
 * 다음 큰 숫자
 * https://programmers.co.kr/learn/courses/30/lessons/12911
 */
public class Q12911 {

    private int getOneCnt(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cnt += 1;
            }
        }
        return cnt;
    }

    public int solution(int n) {
        int answer = 0;

        answer = n;

        int nBinOneCnt = getOneCnt(Integer.toBinaryString(n));
        while (true) {
            answer++;

            int ansBinOneCnt = getOneCnt(Integer.toBinaryString(answer));

            if (nBinOneCnt == ansBinOneCnt) break;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 78; //	83
        System.out.println(new Q12911().solution(n));
        n = 15; //23
        System.out.println(new Q12911().solution(n));

    }
}
