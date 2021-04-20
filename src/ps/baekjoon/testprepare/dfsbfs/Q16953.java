package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A -> B
 * https://www.acmicpc.net/problem/16953
 *
 * dfs로 다 붙여보자.
 * 입력 최대가 1000000000 10억이니까
 * 여기에 1붙이면 int범위 넘음
 * long 으로 변경.
 *
 */
public class Q16953 {

    private static int ret = -1;

    public static void main(String[] args) {
        Input in = new Input();
        long a = in.nextInt();
        long b = in.nextInt();

        dfs(a, b, 0);
        System.out.println(ret);
    }

    private static void dfs(long a, long b, int cnt) {
        if (a > b) {
            return;
        }

        if (a == b) {
            ret = cnt + 1;
            return;
        }

        // *2
        dfs(a * 2, b, cnt + 1);

        // *10 + 1
        dfs(a * 10 + 1, b, cnt + 1);
    }

    private static class Input{
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
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
    }
}
