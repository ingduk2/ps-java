package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 연도 진행바
 * https://www.acmicpc.net/problem/1340
 *
 * 달 다 차야하기때문에 -1
 * 일도 하루가 차야하기 때문에 -1
 */
public class Q1340 {

    private static Map<String, Integer> monthMap = Map.ofEntries(
            Map.entry("January", 1),
            Map.entry("February", 2),
            Map.entry("March", 3),
            Map.entry("April", 4),
            Map.entry("May", 5),
            Map.entry("June", 6),
            Map.entry("July", 7),
            Map.entry("August", 8),
            Map.entry("September", 9),
            Map.entry("October", 10),
            Map.entry("November", 11),
            Map.entry("December", 12));

    private static int[][] months = {
            {31,28,31,30,31,30,31,31,30,31,30,31},
            {31,29,31,30,31,30,31,31,30,31,30,31}};

    private static int isLeap(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return 1;
        }
        return 0;
    }

    //분로 반환해보자. (전체는 일수 * 24 * 60)
    //현재시간은 달->일 + 일 * 24 * 60 + hh * 60 + mm
    //달은 -1 , 일도 -1 필요할듯 (아직 다 안채워졌으므로.)
    private static long convertMinute(int y, int m, int d, int hh, int mm) {
        int day = d - 1;
        for (int i = 1; i < m; i++) {
            day += months[isLeap(y)][i - 1];
        }

        long minute = day * 24 * 60;
        minute += hh * 60;
        minute += mm;
        return minute;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int month = monthMap.get(in.next());
        int dd = Integer.parseInt(in.next().split(",")[0]);
        int yy = Integer.parseInt(in.next());
        String[] hhmm = in.next().split(":");
        int hh = Integer.parseInt(hhmm[0]);
        int mm = Integer.parseInt(hhmm[1]);


        long fullyearMinute = (isLeap(yy) == 1 ? 366 : 365) * 24 * 60;
        long currentMinute = convertMinute(yy, month, dd, hh, mm);

        System.out.println((double)currentMinute / (double)fullyearMinute * 100.0);
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
