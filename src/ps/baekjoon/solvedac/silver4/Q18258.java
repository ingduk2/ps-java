package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 큐2
 * https://www.acmicpc.net/problem/18258
 */
public class Q18258 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        StringBuilder result = new StringBuilder();
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            int out = Integer.MIN_VALUE;
            if (cmd.equals("push")) {
                q.add(in.nextInt());
            } else if (cmd.equals("pop")) {
                out = q.isEmpty() ? -1 : q.poll();
            } else if (cmd.equals("size")) {
                out = q.size();
            } else if (cmd.equals("empty")) {
                out = q.isEmpty() ? 1 : 0;
            } else if (cmd.equals("front")) {
                out = q.isEmpty() ? -1 : q.peekFirst();
            } else if (cmd.equals("back")) {
                out = q.isEmpty() ? -1 : q.peekLast();
            }

            if (out != Integer.MIN_VALUE) result.append(out).append("\n");
        }

        System.out.println(result);
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
