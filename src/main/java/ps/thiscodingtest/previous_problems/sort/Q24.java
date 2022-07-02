package ps.thiscodingtest.previous_problems.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 안테나
 */
public class Q24 {
    private static class Result {
        private int sum;
        private int antenna;

        public Result(int sum, int antenna) {
            this.sum = sum;
            this.antenna = antenna;
        }

        public int getSum() {
            return sum;
        }

        public int getAntenna() {
            return antenna;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "sum=" + sum +
                    ", antenna=" + antenna +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxAn = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            list.add(num);
            maxAn = Math.max(maxAn, num);
        }

        List<Result> resultList = new ArrayList<>();
        for (int an = 1; an < maxAn + 1; an++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.abs(list.get(i) - an);
            }
            resultList.add(new Result(sum, an));
        }

        System.out.println(resultList);
        resultList.sort(
                Comparator.comparingInt(Result::getSum)
        .thenComparingInt(Result::getAntenna));
        System.out.println(resultList);

        System.out.println(resultList.get(0).getAntenna());
    }
}
