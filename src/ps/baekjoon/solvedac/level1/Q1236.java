package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 성 지키기
 * https://www.acmicpc.net/problem/1236
 *
 * 행과 열이므로
 *
 * ....
 * ....
 * ....
 * ....
 *
 * X...
 * .X..
 * ..X.
 * ...X
 *
 * 이런식으로 되어야할것 같음.
 *
 * 처음에 한줄에 1명만 있으면 되는걸로해서 틀림.
 * X..X
 * ..X.
 * X...
 * X...
 * 이러면 0 나오고 막히지 않음.
 *
 * .X....
 * .X....
 * ......
 * ......
 * 길을 막는것이 아니라 모든 행 열에 있어야하므로
 * 비어있는 행, 열의 개수 중 더 큰값이 최소의 경비원 수
 *
 * 행 2, 열 5 -> 5명 필요.
 *
 */
public class Q1236 {

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        char[][] castle = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = in.line();
            castle[i] = line.toCharArray();
        }

        int rowCnt = 0;
        for (int i = 0; i < n; i++) {
            boolean hasGuard = false;
            for (int j = 0; j < m; j++) {
                if (castle[i][j] == 'X') {
                    hasGuard = true;
                    break;
                }
            }

            if (!hasGuard) rowCnt++;
        }

        int colCnt = 0;
        for (int i = 0; i < m; i++) {
            boolean hasGuard = false;
            for (int j = 0; j < n; j++) {
                if (castle[j][i] == 'X') {
                    hasGuard = true;
                    break;
                }
            }

            if (!hasGuard) colCnt++;
        }

        System.out.println(Math.max(rowCnt, colCnt));
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
