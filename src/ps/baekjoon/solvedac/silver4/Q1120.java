package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문자열
 * https://www.acmicpc.net/problem/1120
 *
 * 차이 - A[i] != B[i] cnt
 * B문자열 1번째 add, 마지막 add하면 최소 될듯
 *
 * 틀림.
 * abcd, abcdef
 * -> abcd abcd ''
 * -> abce ' bcde '
 * -> abcd '' cdef
 *
 * 앞이나 뒤에 채워넣을 수 있기 때문에 차이가 가장 작은 수를 찾는다.
 */
public class Q1120 {

    private static int getDiffCnt(String A, String B, int startIdxB) {
        int n = A.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (A.charAt(i) != B.charAt(i + startIdxB)) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        Input in = new Input();
        String A = in.next();
        String B = in.next();

        int n = B.length() - A.length();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            min = Math.min(min, getDiffCnt(A, B, i));
        }

        System.out.println(min);
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
