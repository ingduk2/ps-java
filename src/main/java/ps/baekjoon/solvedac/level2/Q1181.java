package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 단어정렬
 * https://www.acmicpc.net/problem/1181
 */
public class Q1181 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        Set<String> words = new HashSet<>();
        for (int i = 0; i < n; i++) {
            words.add(in.next());
        }

        List<String> list = new ArrayList<>(words);
        list.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        for (String s : list) {
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
