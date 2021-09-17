package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수 이어 쓰기 1
 * https://www.acmicpc.net/problem/1748
 *
 * 자리수 출력
 * string 바꿔서 length 했더니 메모리 초과..
 *
 * 1-9 -> 1
 * 10-99 -> 2
 * 100-999 -> 3
 * 1000-9999 -> 4
 *
 * 10으로 나누어 떨어질때 +1, 10 * 10
 *
 */
public class Q1748 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int cnt = 0;
        int add = 1;
        int divide = 10;
        for (int i = 1; i <= n; i++) {
            if (i % divide == 0) {
                add++;
                divide *= 10;
            }
            cnt += add;
        }

        System.out.println(cnt);
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
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
