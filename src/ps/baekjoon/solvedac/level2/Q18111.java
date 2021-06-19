package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 마인크래프트
 * https://www.acmicpc.net/problem/18111
 *
 * 블록개수 0부터 256까지 돌린다.
 * 위 블록을 만들기 위한 시간을 계산.
 * 중간에 블록이 모자라다면 패쓰.
 *
 * 블럭제거시 블록 +,
 * 블록 놓을때 개수 -된다고 바로 return시키면 안될듯..
 * 제거시 블록+ 해서 최종 개수를 봐야함
 */
public class Q18111 {

    private static class Result{
        private int time;
        private int height;

        public Result(int time, int height) {
            this.time = time;
            this.height = height;
        }

        @Override
        public String toString() {
            return time + " " + height;
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();
        int b = in.nextInt(); //블록

        int[][] blocks = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int input = in.nextInt();
                blocks[i][j] = input;
            }
        }

        List<Result> results = new ArrayList<>();

        for (int bl = 0; bl <= 256; bl++) {
            calculate(n, m, b, blocks, results,  bl);
        }

        results.sort((o1, o2) -> {
            if (o1.time == o2.time) {
                return Integer.compare(o2.height, o1.height);
            }
            return Integer.compare(o1.time, o2.time);
        });

        System.out.println(results.get(0));

    }

    private static void calculate(int n, int m, int b, int[][] blocks, List<Result> results, int bl) {
        int time = 0;
        int blockNum = b;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //제거
                if (blocks[i][j] > bl) {
                    blockNum += blocks[i][j] - bl;
                    time += 2 * (blocks[i][j] - bl);
                }
                //쌓기
                else if (blocks[i][j] < bl) {
                    blockNum -= bl - blocks[i][j];
                    time += (bl - blocks[i][j]);
                }
            }
        }

        if (blockNum > 0) results.add(new Result(time, bl));
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
