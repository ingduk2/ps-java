package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 에디터
 * https://www.acmicpc.net/problem/1406
 *
 * ListIterator 사용
 * 조회가 필요없어서 빠른
 * B 에서 지울때 iter.previous 로 옮겨줘야함
 * */
public class Q1406_ListIterator {

    public static void main(String[] args) {
        Input in = new Input();
        String str = in.next();
        int n = str.length();
        int m = in.nextInt();

        LinkedList<Character> strs = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            strs.add(str.charAt(i));
        }

        ListIterator<Character> iter = strs.listIterator();
        while (iter.hasNext()) iter.next();

        for (int i = 0; i < m; i++) {
            String cmd = in.next();

            switch (cmd) {
                case "P":
                    iter.add(in.next().charAt(0));
                    break;
                case "L":
                    if (iter.hasPrevious()) iter.previous();
                    break;
                case "D":
                    if (iter.hasNext()) iter.next();
                    break;
                case "B":
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
            }
        }

        StringBuilder result = new StringBuilder();
        for (Character character : strs) {
            result.append(character);
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
