package ps.baekjoon.solvedac.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 암호 만들기
 * https://www.acmicpc.net/problem/1759
 */
public class Q1759 {

    private static List<Character> collections = List.of('a', 'e', 'i', 'o', 'u');

    private static boolean hasLeastOneCollection(List<Character> pwd) {
        for (Character c : pwd) {
            if (collections.contains(c)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasLeastTwoConsonant(List<Character> pwd) {
        int cnt = 0;
        for (Character c : pwd) {
            if (!collections.contains(c)) {
                cnt++;
            }
        }

        return cnt >= 2;
    }


    private static void combination(char[] arr, boolean[] visited, int start, int m, int depth) {
        if (depth == m) {

            List<Character> pwd = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) pwd.add(arr[i]);
            }


            //모음이 한개도 없으면
            if (!hasLeastOneCollection(pwd)) return;
            //최소 두 개의 자음이 없으면
            if (!hasLeastTwoConsonant(pwd)) return;


            //print
            for (Character c : pwd) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, m, depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int l = in.nextInt();
        int c = in.nextInt();

        char[] arr = new char[c];
        boolean[] visited = new boolean[c];
        for (int i = 0; i < c; i++) {
            arr[i] = in.next().charAt(0);
        }

        Arrays.sort(arr);

        combination(arr, visited, 0, l, 0);

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
