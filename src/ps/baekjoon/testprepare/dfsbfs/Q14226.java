package ps.baekjoon.testprepare.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 이모티콘
 * https://www.acmicpc.net/problem/14226
 *
 * 1초 가중치로 bfs 가능
 *
 * 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
 * 화면 = 클립보드가 됨 시간 + 1
 * d, d
 * times[d][d] = times[d][c] + 1
 *
 * 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 * 화면 + 클립보드, 클립보드 됨
 * d + c, c
 * d + c <= s (입력값) 보다 같거나 작아야 붙여넣을 수 있음.
 * times[d + c][c] = times[d][c] + 1
 *
 * 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
 * d - 1, c
 * d >= 1 이여야 지울 수 있음
 * times[d - 1][c] = times[d][c] + 1
 */
public class Q14226 {

    private static class Emoticon {
        private int screen;
        private int clipboard;

        public Emoticon(int screen, int clipboard) {
            this.screen = screen;
            this.clipboard = clipboard;
        }
    }

    private static int[][] times = new int[1001][1001];
    private static boolean[][] check = new boolean[1001][1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();

        bfs(s);

        int ret = Integer.MAX_VALUE;
        //times[s][] s열의 최소값이 최소시간
        for (int i = 0; i <= s; i++) {
            if (times[s][i] != 0) ret = Math.min(ret, times[s][i]);
        }

        System.out.println(ret);
    }

    private static void bfs(int s) {
        Queue<Emoticon> q = new LinkedList<>();
        //이미 화면에 이모티콘 1개
        q.add(new Emoticon(1, 0));
        check[1][0] = true;
        times[1][0] = 0;

        while (!q.isEmpty()) {
            Emoticon poll = q.poll();
            int d = poll.screen;
            int c = poll.clipboard;

            if (d == s) break;

            if (!check[d][d]) {
                check[d][d] = true;
                times[d][d] = times[d][c] + 1;
                q.add(new Emoticon(d, d));
            }

            if (d + c <= s && !check[d + c][c]) {
                check[d + c][c] = true;
                times[d + c][c] = times[d][c] + 1;
                q.add(new Emoticon(d + c, c));

            }

            if (d >= 1 && !check[d - 1][c]) {
                check[d - 1][c] = true;
                times[d - 1][c] = times[d][c] + 1;
                q.add(new Emoticon(d - 1, c));
            }

        }
    }
}
