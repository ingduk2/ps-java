package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 올림픽
 * https://www.acmicpc.net/problem/8979
 *
 * -처음에 0번째것을 prev로 잡으니 첫번째것이 안나오는 경우가 있음..
 * -1, 2, 2, 3 등이 아니라 1, 2, 2, 4 등임..
 *
 */
public class Q8979 {

    private static class Country{
        private int num;
        private int gold;
        private int silver;
        private int bronze;
        private int rank;

        public Country(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "num=" + num +
                    ", gold=" + gold +
                    ", silver=" + silver +
                    ", bronze=" + bronze +
                    ", rank=" + rank +
                    '}' + "\n";
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int k = in.nextInt();

        List<Country> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lists.add(new Country(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
        }

        //sort
        lists.sort((o1, o2) -> {
            if (o1.gold == o2.gold && o1.silver == o2.silver) return Integer.compare(o2.bronze, o1.bronze);

            if (o1.gold == o2.gold) return Integer.compare(o2.silver, o1.silver);

            return Integer.compare(o2.gold, o1.gold);
        });

        //getRank
        int currentRank = 1;
        lists.get(0).rank = currentRank;
        Country prevCountry = lists.get(0);
        currentRank++;

        if (prevCountry.num == k) {
            System.out.println(prevCountry.rank);
        } else {
            for (int i = 1; i < n; i++) {
                Country country = lists.get(i);
                if (prevCountry.gold == country.gold &&
                        prevCountry.silver == country.silver &&
                        prevCountry.bronze == country.bronze) {
                    country.rank = prevCountry.rank;
                } else {
                    country.rank = currentRank;
                    prevCountry = country;
                }

                if (country.num == k) {
                    System.out.println(country.rank);
                    break;
                }

                currentRank++;
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
