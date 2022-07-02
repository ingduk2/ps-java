package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 저항
 * https://www.acmicpc.net/problem/1076
 *
 * enum도 사용가능하다
 */
public class Q1076 {

    private static class ValueSet {
        private int val;
        private int mul;

        public ValueSet(int val, int mul) {
            this.val = val;
            this.mul = mul;
        }
    }

    private static Map<String, ValueSet> resistanceMap =
            Map.of("black", new ValueSet(0, 1),
                    "brown", new ValueSet(1 , 10),
                    "red", new ValueSet(2, 100),
                    "orange", new ValueSet(3, 1000),
                    "yellow", new ValueSet(4, 10000),
                    "green", new ValueSet(5, 100000),
                    "blue", new ValueSet(6, 1000000),
                    "violet", new ValueSet(7, 10000000),
                    "grey", new ValueSet(8, 100000000),
                    "white", new ValueSet(9, 1000000000));

    public static void main(String[] args) {
        Input in = new Input();
        long resistance;
        resistance = resistanceMap.get(in.next()).val * 10;
        resistance += resistanceMap.get(in.next()).val;
        resistance *= resistanceMap.get(in.next()).mul;

        System.out.println(resistance);
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
