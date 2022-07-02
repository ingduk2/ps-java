package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 과제 안 내신 분..?
 * https://www.acmicpc.net/problem/5597
 */
public class Q5597 {

    private static int inputCnt = 28;
    private static int studentsCnt = 30;

    public static void main(String[] args) {
        Input in = new Input();
        boolean[] students = new boolean[studentsCnt + 1];
        for (int i = 0; i < inputCnt; i++) {
            students[in.nextInt()] = true;
        }

        //result
        for (int i = 1; i < studentsCnt + 1; i++) {
            if (!students[i]) System.out.println(i);
        }
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
