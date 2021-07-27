package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 방 번호
 * 0-9 셋트
 * 6과 9는 공용.
 *
 * 01234578 은 1세트 필요.
 * 69는 2개당 1세트
 *
 * 9999 -> 2
 * 6699 -> 2
 * 666 -> 2
 * 66699 -> 3
 *
 *
 * 1. 숫자 개수를 센다.
 * 2. 최대 개수를 찾는다.
 * 3. 69를 제외하고 max값 찾음
 * 4. 6과 9의합이 짝수이면 /2, 홀수이면 /2 + 1
 * 5. 3번과 4번의 max값
 */
public class Q1475 {

    public static void main(String[] args) {
        Input in = new Input();
        String n = in.next();

        //0123456789
        int[] roomCnts = new int[10];
        String[] split = n.split("");
        for (String s : split) {
            int num = Integer.parseInt(s);
            roomCnts[num]++;
        }

        int max = 0;
        for (int i = 0; i < roomCnts.length; i++) {
            if (i != 6 && i != 9) max = Math.max(max, roomCnts[i]);
        }

        int cnt = roomCnts[6] + roomCnts[9];
        if (cnt % 2 == 0) {
            cnt /= 2;
        } else {
            cnt = cnt / 2 + 1;
        }

        System.out.println(Math.max(max, cnt));
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
