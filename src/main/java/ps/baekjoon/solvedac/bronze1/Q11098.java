package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 첼시를 도와줘!
 * https://www.acmicpc.net/problem/11098
 */
public class Q11098 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            String mostName = "";
            int mostCost = 0;
            for (int j = 0; j < p; j++) {
                int cost = in.nextInt();
                String name = in.next();
                if (mostCost < cost) {
                    mostCost = cost;
                    mostName = name;
                }
            }

            System.out.println(mostName);
        }
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
