package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 민균이의 비밀번호
 * https://www.acmicpc.net/problem/9933
 *
 * 단어를 뒤집어서 같거나,
 * 뒤집은 단어가 있거나
 *
 * 중복조합으로 2개씩 선택해서
 * strA == strB.reverse 인지 확인.
 */
public class Q9933 {

    private static void combination(String[] arr, String[] result, int start, int m, int depth) {
        if (m == depth) {
            String strA = result[0];
            String strB = new StringBuilder(result[1]).reverse().toString();
            if (strA.equals(strB)) System.out.println(strA.length() + " " + strA.charAt(strA.length() / 2));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            result[depth] = arr[i];
            combination(arr, result, i, m, depth + 1);
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        String[] arr = new String[n];
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next();
        }

        combination(arr, result, 0, 2, 0);
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

        public long nextLong() { return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
