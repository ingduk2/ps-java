package ps.baekjoon.testprepare.partBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기 1
 * https://www.acmicpc.net/problem/17070
 *
 * 가로 :  오른쪽( y++, y++ ) , 오른쪽대각(화면상) (y++, y++ x++)
 * 세로 :  아래(x++, x++), 오른쪽대각(화면상) (x++, y++ x++)
 * 대각선 : 오른쪽(x++ y++, y++), 아래(x++ y++, x++), 오른쪽대각(x++ y++, x++ y++)
 *
 * 가로인지, 세로인지, 대각에 있는지 확인해야할듯.
 * 처음에 가로니까 이동시키면서 체크.
 * 가로 -> 오른쪽 - 가로
 * 가로 -> 대각 - 대각
 *
 * 세로 -> 아래 - 세로
 * 세로 -> 대각 - 대각
 *
 * 대각 -> 오른쪽 - 가로
 * 대각 -> 아래 - 세로
 * 대각 -> 대각 - 대
 *
 * 좌표 2개 다 바꿀 필요는 없는듯. 끝부분을 이동
 *
 * 대각선은 3군데가 다 0이어야한다.
 */
public class Q17070 {

    private static int ret;

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int[][] home = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                home[i][j] = input.nextInt();
            }
        }

        //R(오른쪽), D(아래), C(대각선)
        //파이프 끝 좌표
        solution(home, 1, 2, 'R');
        System.out.println(ret);
    }

    private static void solution(int[][] home, int x, int y, char direction) {
        int n = home.length - 1;
        if (x == n && y == n && home[x][y] == 0) {
            ret++;
            return;
        }

        if (direction == 'R') {
            //오른쪽
            if ( y + 1 <= n && home[x][y + 1] == 0) {
                solution(home, x, y + 1, 'R');
            }

            //대각선
            if (x + 1 <= n && y + 1 <= n && home[x + 1][y + 1] == 0 && home[x + 1][y] == 0 && home[x][y + 1] == 0) {
                solution(home, x + 1, y + 1, 'C');
            }

        } else if (direction == 'D') {
            //아래
            if (x + 1 <= n && home[x + 1][y] == 0) {
                solution(home, x + 1, y, 'D');
            }

            //대각
            if (x + 1 <= n && y + 1 <= n && home[x + 1][y + 1] == 0 && home[x + 1][y] == 0 && home[x][y + 1] == 0) {
                solution(home, x + 1, y + 1, 'C');
            }


        } else if (direction == 'C') {
            //오른쪽
            if (y + 1 <= n && home[x][y + 1] == 0) {
                solution(home, x, y + 1, 'R');
            }

            //아래
            if (x + 1 <= n && home[x + 1][y] == 0) {
                solution(home, x + 1, y, 'D');
            }

            //대각
            if (x + 1 <= n && y + 1 <= n && home[x + 1][y + 1] == 0 && home[x + 1][y] == 0 && home[x][y + 1] == 0) {
                solution(home, x + 1, y + 1, 'C');
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
    }
}
