package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 좌표 정렬하기 2
 * https://www.acmicpc.net/problem/11651
 */
public class Q11651 {

    private static class Pos{
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        List<Pos> posList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            posList.add(new Pos(in.nextInt(), in.nextInt()));
        }

        posList.sort((o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }

            return Integer.compare(o1.y, o2.y);
        });

        for (Pos pos : posList) {
            System.out.println(pos.x + " " + pos.y);
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
