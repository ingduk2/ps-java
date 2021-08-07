package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 기타줄
 * https://www.acmicpc.net/problem/1049
 *
 * 기타줄 최대 6
 * 패키지가격 , 낱개 가격
 *
 * 4 2
 * 12 3
 * 15 4
 * 12
 * -> 첫번째 패키지 12, 낱개 4 * 3 12
 * -> 2번쨰 패키지 15, 낱개 16
 *
 * 10 3
 * 20 8
 * 40 7
 * 60 4
 * -> 첫번째 패키지 40, 낱개 80
 * -> 2 패키지 80, 낱개 70
 * -> 3 패키지 120, 낱개 40
 * -> 첫번째 패키지 20 + 낱개3 4 * 4 = 36
 *
 * 패키지던 낱개던 최소가격을 구함
 * 전부 패키지
 * 패키지 + 낱개
 * 반복해서 구하면서 최소값.
 *
 *
 * 다른 풀이보니
 * 가장 싼 팩으로, 가장 싼 낱개로, 가장 싼 팩 + 낱개(n/6 * pack + n%6 * each)
 * 로 답이 나옴.
 *
 * 팩/6 > 낱개 낱개가 팩보다 훨씬 싸면 낱개로 다사면 되고,
 * 팩 =< 낱개*6 보다 싸거나 같을때 개수가 떨어지면 팩으로 다사면 싸고,
 * 팩/6 =< 낱개 라면 팩 사고 남은 개수가 적으면 팩 최대 + 낱개이므로 위의 식으로 될 수 있다.
 *
 */
public class Q1049 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        int minPackageCost = Integer.MAX_VALUE;
        int minEachCost = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minPackageCost = Math.min(minPackageCost, in.nextInt());
            minEachCost = Math.min(minEachCost, in.nextInt());
        }

        int min = Integer.MAX_VALUE;
        int packageNum = 0;
        int each = n;
        //낱개 -> 낱개 + 패키지 -> 패키지만 까지 계산
        while (each > 0) {
            each = n - packageNum * 6;
            if (each > 0) {
                min = Math.min(min, each * minEachCost + packageNum * minPackageCost);
            } else {
                min = Math.min(min, packageNum * minPackageCost);
            }
            packageNum++;
        }

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
