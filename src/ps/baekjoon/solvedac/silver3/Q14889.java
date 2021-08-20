package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 스타트와 링크
 * https://www.acmicpc.net/problem/14889
 *
 * 능력치 차이 최소
 * 조합으로 팀원들 최소치.
 *
 * 1. combination 조합 만듬
 * 2. visited true, false 로 팀이 나눠짐
 * 3. 처음 입력받은 능력치 배열에서 visited true 끼리, false 끼리 합
 * 4. 차이를 구함
 *
 * 조합, 백트레킹
 */
public class Q14889 {

    private static int[][] arr;
    private static int min = Integer.MAX_VALUE;

    private static void combination(int[] team, boolean[] visited, int start, int m, int depth) {
        if (depth == m) {
            int team1 = 0;
            int team2 = 0;

            for (int i = 0; i < team.length; i++) {
                for (int j = 0; j < team.length; j++) {

                    if (visited[i] && visited[j]) team1 += arr[i][j];
                    else if (!visited[i] && !visited[j]) team2 += arr[i][j];
                }
            }

            int diff = Math.abs(team1 - team2);
            min = Math.min(min, diff);
            return;
        }

        for (int i = start; i < team.length; i++) {
            visited[i] = true;
            combination(team, visited, i + 1, m, depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        arr = new int[n][n];

        boolean[] visited = new boolean[n];
        int[] team = new int[n];
        for (int i = 0; i < n; i++) {
            team[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        int teamCnt = n / 2;
        combination(team, visited, 0, teamCnt, 0);
        System.out.println(min);
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
