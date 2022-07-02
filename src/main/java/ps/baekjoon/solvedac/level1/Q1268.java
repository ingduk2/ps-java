package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 임시 반장 정하기
 * https://www.acmicpc.net/problem/1268
 *
 * -몇번 학생과 같은반이었는지. 같은 숫자있으면 count++ 해버리면
 * -이전에 같은반을 했어도 또 세어버릴 수 있음.
 * -한번 세었으면 pass위해. set 사용
 *
 * -몇번이랑 반을 했었는지 필요.
 * -같은반이 있으면 + 해버리는것이 아님.. 그러면 한명이 여러번 체크됨.
 */
public class Q1268 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int[][] arr = new int[n + 1][5 + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < 5 + 1; j++) {
                int num = in.nextInt();
                arr[i][j] = num;
            }
        }

        int max = 0;
        int student = 1;

        //학생번호 순서
        for (int i = 1; i < n + 1; i++) {
            //학년
            Set<Integer> counts = new HashSet<>();
            for (int g = 1; g < 5 + 1; g++) {
                //학생번호 처음부터 비교
                for (int k = 1; k < n + 1; k++) {
                    if (i != k && arr[i][g] == arr[k][g]) {
                        counts.add(k);
                    }
                }
            }

            if (max < counts.size()) {
                max = counts.size();
                student = i;
            }
        }

        System.out.println(student);
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
