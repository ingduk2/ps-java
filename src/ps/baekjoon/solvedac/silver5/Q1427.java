package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 소트인사이드
 * https://www.acmicpc.net/problem/1427
 */
public class Q1427 {

    public static void main(String[] args) {
        Input in = new Input();
        String n = in.next();

        String[] arrStr = n.split("");
        Integer[] arr = new Integer[arrStr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        Arrays.sort(arr, Comparator.reverseOrder());
        for (Integer integer : arr) {
            System.out.print(integer);
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
