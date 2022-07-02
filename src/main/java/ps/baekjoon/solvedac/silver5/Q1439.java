package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 뒤집기
 * https://www.acmicpc.net/problem/1439
 *
 * 00011000, 000 11 000
 * 그룹을 나누어서 그룹수가 적은것
 *
 * 숫자가 달라질때 카운트 ++
 */
public class Q1439 {

    public static void main(String[] args) {
        Input in = new Input();
        String str = in.next();

        int cntZero = 0;
        int cntOne = 0;

        char prev = str.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (prev != c) {
                if (prev == '0') cntZero++;
                else cntOne++;
                prev = c;
            }
        }

        if (prev == '0') cntZero++;
        else cntOne++;

        System.out.println(Math.min(cntOne, cntZero));
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
