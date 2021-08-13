package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회사에 있는 사람
 * https://www.acmicpc.net/problem/7785
 */
public class Q7785 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String state = in.next();

            if (map.containsKey(name)) {
                map.remove(name);
            } else {
                map.put(name, state);
            }
        }

        List<String> names = new ArrayList<>(map.keySet());
        names.sort(Comparator.reverseOrder());

        names.forEach(System.out::println);
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
