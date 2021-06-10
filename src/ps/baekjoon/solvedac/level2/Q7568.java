package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 덩치
 * https://www.acmicpc.net/problem/7568
 *
 * 정렬로 해볼려했으나 뭔가 안맞음..
 * for문 이중돌리면서 등수 주는 방식으로 해보자.
 * 처음에 1로 주고, 더 작은 경우 등수를 올린다.
 * 문제의 조건으로 하면 등수보다는 개수가 되어버림.
 */
public class Q7568 {

    private static  class Bulk{
        private int x;
        private int y;
        private int rank;

        public Bulk(int x, int y, int rank) {
            this.x = x;
            this.y = y;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Bulk{" +
                    "x=" + x +
                    ", y=" + y +
                    ", rank=" + rank +
                    '}';
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        List<Bulk> bulks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bulks.add(new Bulk(in.nextInt(), in.nextInt(), 1));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bulks.get(i).x < bulks.get(j).x && bulks.get(i).y < bulks.get(j).y) {
                    bulks.get(i).rank++;
                }
            }
        }

        for (Bulk bulk : bulks) {
            System.out.print(bulk.rank + " ");
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
