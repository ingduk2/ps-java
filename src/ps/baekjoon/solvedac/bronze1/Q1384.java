package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메시지
 * https://www.acmicpc.net/problem/1384
 *
 * N 을 찾아서 [i-j][0] 이름,
 * i-j < 0 이면 [n + i-j][0] 이름 을 찾는다.
 */
public class Q1384 {

    public static void main(String[] args) {
        Input in = new Input();
        int group = 1;
        while (true) {
            int n = in.nextInt();
            if (n == 0) return;

            String[][] arr = new String[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = in.next();
                }
            }


            System.out.println("Group " + group);
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j].equals("N")) {
                        String name;
                        if (i - j < 0) {
                            name = arr[n + (i - j)][0];
                        } else {
                            name = arr[i - j][0];
                        }

                        ret.append(name).append(" was nasty about ")
                                .append(arr[i][0]).append("\n");
                    }
                }
            }

            if (ret.length() == 0) System.out.println("Nobody was nasty\n");
            else System.out.println(ret);

            group++;
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
