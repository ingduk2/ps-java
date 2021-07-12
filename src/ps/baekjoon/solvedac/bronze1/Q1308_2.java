package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * D-Day
 * https://www.acmicpc.net/problem/1308
 *
 * 1000년 계산시 날짜 차이 / 365 했더니 윤년때문에 틀리는가..
 *
 * Calendar 쓰니까 뭔가 안맞는듯. 직접 구현
 */
public class Q1308_2 {

    private static int[][] months = {
            {31,28,31,30,31,30,31,31,30,31,30,31},
            {31,29,31,30,31,30,31,31,30,31,30,31}};

    private static int isLeap(int y) {
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            return 1;
        }

        return 0;
    }
    
    private static int getDays(int y, int m, int d) {
        int days = 0;
        for (int i = 0; i < y; i++) {
            days += 365 + isLeap(i);
        }

        //달은 1달 넘은 후부터 계산.
        for (int i = 1; i < m; i++) {
            days += months[isLeap(y)][i - 1];
        }

        return days + d;
    }

    public static void main(String[] args) {

        Input in = new Input();
        int startY = in.nextInt();
        int startM = in.nextInt();
        int startD = in.nextInt();
        int day1 = getDays(startY, startM, startD);

        int endY = in.nextInt();
        int endM = in.nextInt();
        int endD = in.nextInt();
        int day2 = getDays(endY, endM, endD);

        int diffDay = getDays(0, endM, endD) - getDays(0, startM, startD);
        if (endY - startY >= 1000 && diffDay >= 0) System.out.println("gg");
        else System.out.println("D-" + (day2 - day1));
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
