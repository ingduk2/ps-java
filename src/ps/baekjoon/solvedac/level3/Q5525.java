package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * IOIOI
 * https://www.acmicpc.net/problem/5525
 *
 * P1 IOI
 * P2 IOIOI
 * P3 IOIOIOI
 * PN IOIOI...OI (O가 N개)
 *
 * 그냥 for 문 돌리면 점수 반만 받을것같은데... 음
 * N이 1000000이라 N을 다시 돌리면 시간제한..
 * 역시 50점 나옴.
 *
 * --
 * I 후에 OIOIOI 의 반복임.
 * I 를 찾은 다음에 OI 의 개수를 세서
 * I OI
 * I OI OI
 * I OI OI OI
 * n 과 비교해서 result++ , OI의 개수-- 찾았기 때문에
 *
 * if (i >= s.length() - 2) break;
 * == 으로 해서 StringIndexOutOfBounds Exception
 *
 */
public class Q5525 {
    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt(); //Pn
        int m = in.nextInt(); //길이
        String s = in.next();

        int result = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.print(i);
            if (s.charAt(i) == 'I') {
                System.out.println("+");
                if (i >= s.length() - 2) break; // 끝에서 2번째 이후는 필요없음.
                System.out.println("-- " + (i + 1) + " " + (i + 2));
                if (s.charAt(i + 1) == 'O' && s.charAt(i + 2) == 'I') {
                    count++;
                    if (count == n) {
                        result++;
                        count--;
                    }
                    i++; //for에서 ++ 있어서 i+=2 하면 3개씩 늘어남.
                } else {
                    count = 0;
                }
            }
        }

        System.out.println(result);

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
