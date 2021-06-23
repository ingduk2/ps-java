package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 나는야 포켓몬 마스터 이다솜
 * https://www.acmicpc.net/problem/1620
 *
 * Map 2개 만들어서 해결.
 * 번호 기준, 이름 기준.
 */
public class Q1620 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();
        Map<String, Integer> nameMap = new HashMap<>();
        Map<Integer, String> numMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String name = in.next();
            nameMap.put(name, i);
            numMap.put(i, name);
        }

        for (int i = 0; i < m; i++) {
            String question = in.next();
            if (question.chars().allMatch(Character::isDigit)) {
                System.out.println(numMap.get(Integer.parseInt(question)));
            } else {
                System.out.println(nameMap.get(question));
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
