package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 비밀번호 찾기
 * https://www.acmicpc.net/problem/17219
 *
 * map 쓰면 편할듯.
 * 100000 개라..
 */
public class Q17219 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        StringBuilder result = new StringBuilder();

        //input
        Map<String, String> sitesPwdMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sitesPwdMap.put(in.next(), in.next());
        }

        //get pwd
        for (int i = 0; i < m; i++) {
            result.append(sitesPwdMap.get(in.next())).append("\n");
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
