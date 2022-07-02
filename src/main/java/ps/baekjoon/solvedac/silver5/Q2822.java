package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 점수 계산
 * https://www.acmicpc.net/problem/2822
 */
public class Q2822 {
    private static class Score {
        private int num;
        private int score;

        public Score(int num, int score) {
            this.num = num;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Score{" +
                    "num=" + num +
                    ", score=" + score +
                    '}';
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            scores.add(new Score(i + 1, in.nextInt()));
        }

        scores.sort(Comparator.comparingInt(o -> o.score));

        int sum = 0;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 7; i > 2; i--) {
            Score score = scores.get(i);
            sum += score.score;
            numbers.add(score.num);
        }

        Collections.sort(numbers);
        System.out.println(sum);
        for (Integer number : numbers) {
            System.out.print(number + " ");
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
