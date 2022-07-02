package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 명령 프롬프트
 * https://www.acmicpc.net/problem/1032
 *
 * 3
 * config.sys
 * config.inf
 * configures
 *
 * 파일글자수가 같다고했기때문에
 * 처음 파일의 이름을 저장.
 * config.sys
 *
 * 다음 파일들을 한글자씩 비교해서 같으면 패쓰. 다르면 ? 로 변경.
 * config.inf
 */
public class Q1032 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        char[] file = null;

        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            //init
            if (file == null) {
                file = new char[cmd.length()];
                for (int j = 0; j < cmd.length(); j++) {
                    file[j] = cmd.charAt(j);
                }
                continue;
            }

            for (int j = 0; j < cmd.length(); j++) {
                if (cmd.charAt(j) != file[j]) {
                    file[j] = '?';
                }
            }
        }

        System.out.println(String.valueOf(file));

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
