package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Hashing
 * https://www.acmicpc.net/problem/15829
 *
 * r^50 이 엄청크다 pow 쓰면 안될듯.
 * 마지막에 %m 해야 정답이네...
 * hash += (num * r) % m;
 *
 * hash = (hash + (num * r)) % m;
 *
 * 2개의 연산 차이인듯.
 *
 * 나머지 문제에는 마지막 답에도 % 해주는게 안전할듯
 */
public class Q15829 {

    public static void main(String[] args) {
        Input in = new Input();
        int i = in.nextInt();
        String str = in.next();

        long r = 1;
        int m = 1234567891;
        long hash = 0;
        for (int j = 0; j < str.length(); j++) {
            char c = str.charAt(j);
            int num = c - 'a' + 1;
            hash = (hash + (num * r)) % m;
            r = (r * 31) % m;
        }

        System.out.println(hash);
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
    }
}
