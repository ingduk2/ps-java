package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Yangjojang of The Year
 * https://www.acmicpc.net/problem/11557
 */
public class Q11557 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            String name = "";
            int max = 0;
            for (int j = 0; j < n; j++) {
                String inName = in.next();
                int drink = in.nextInt();
                if (drink > max){
                    name = inName;
                    max = drink;
                }
            }

            System.out.println(name);
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
