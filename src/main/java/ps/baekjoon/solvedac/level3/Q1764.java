package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 듣보잡
 * https://www.acmicpc.net/problem/1764
 *
 * List contains 사용 시간초과.
 * n * n 되는듯..
 *
 * 500000 개
 *
 * set 으로 하니까 통과.
 * contains 는 set이 빠름.
 *
 * 처음 받는 듣도못한 사람을 Set으로 처리.
 */
public class Q1764 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        Set<String> names = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            names.add(in.next());
        }

        for (int i = 0; i < m; i++) {
            String name = in.next();
            if (names.contains(name)) {
                result.add(name);
            }
        }

        System.out.println(result.size());
        Collections.sort(result);
        for (String s : result) {
            System.out.println(s);
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
    }
}
