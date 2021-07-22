package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 세준세비
 * https://www.acmicpc.net/problem/1524
 *
 * 제일 약한 병사가 죽음.
 * 제일 약한병사 여러명,
 * ->모두 같은 편에 있다면 그 중 한 명이 임의로 선택되어 죽음.
 * ->양 편에 있다면 세비쪽 약한병사 임의로 죽음.
 *
 * 한 명의 병사를 제외하고 모두 죽었을 때 끝.
 *
 * 1명 남을때까지 반복해서 약한병사 제거하면 될듯..
 * 시간초과남.. 음.. n, m이 1000000
 *
 * 전부다 같으면 시간초과날듯.. min개수 찾다가
 *
 * - 그냥 작으면 지워도 같을듯?
 * 132 -> 123 -> 23 -> 3  ->    ->
 * 55     55     55    55    55    5
 *
 * - s >= b 면 b.remove
 * - else s.remove
 * - 한쪽에 2개이상 남으면 그쪽이 승리. 1개까지 지울 필요 없다.
 * - 삭제라 linkedList 로 바꾸니 2660ms 에서 1080ms 로 줄어듬.
 *
 * 다른 풀이 보니 최대값 s >= b ? "s" : "b" 로 풀음..
 * 큰 병사있는쪽이 이기는거고, 같은경우는 세비쪽이 죽으니
 */
public class Q1524_2 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();

            //input
            List<Integer> s = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                int num = in.nextInt();
                s.add(num);
            }
            Collections.sort(s);

            List<Integer> b = new LinkedList<>();
            for (int j = 0; j < m; j++) {
                int num = in.nextInt();
                b.add(num);
            }
            Collections.sort(b);

            game(s, b);

            if (s.size() > b.size()) System.out.println("S");
            if (s.size() < b.size()) System.out.println("B");
        }
    }

    private static void game(List<Integer> s, List<Integer> b) {
        while (s.size() != 0 && b.size() != 0) {
            if (s.get(0) >= b.get(0)) {
                b.remove(0);
            } else {
                s.remove(0);
            }
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
