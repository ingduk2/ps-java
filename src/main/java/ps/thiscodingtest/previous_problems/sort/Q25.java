package ps.thiscodingtest.previous_problems.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 실패율
 */
public class Q25 {

    private static class FailRate {
        private int stage;
        private double rate;

        public FailRate(int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }

        public int getStage() {
            return stage;
        }

        public double getRate() {
            return rate;
        }

        @Override
        public String toString() {
            return "FailRate{" +
                    "stage=" + stage +
                    ", rate=" + rate +
                    '}';
        }
    }


    public int[] solution(int N, int[] stages) {

        List<FailRate> failRateList = new ArrayList<>();

        for (int stage = 1; stage < N + 1; stage++) {
            int failNum = 0;
            int inStageNum = 0;
            for (int i = 0; i < stages.length; i++) {
                if (stage <= stages[i]) {
                    inStageNum += 1;
                }
                if (stage == stages[i]) {
                    failNum += 1;
                }

            }
            failRateList.add(new FailRate(stage, failNum == 0 ? 0 : (double)failNum/inStageNum));
        }

//        failRateList.sort(Comparator.comparingDouble(FailRate::getRate).reversed()
//        .thenComparingInt(FailRate::getStage));

        failRateList.sort((o1, o2) -> {
            if (o1.getStage() == o2.getStage()) {
                return Integer.compare(o1.getStage(), o2.getStage());
            }
            return Double.compare(o2.getRate(), o1.getRate());
        });

        return failRateList.stream().mapToInt(FailRate::getStage).toArray();
    }

    public int[] solution2(int N, int[] stages) {

        int length = stages.length;

        List<FailRate> failRateList = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            int finalI = i;
            int inStageCnt = Arrays.stream(stages).filter(s -> s == finalI).toArray().length;

            double failRure = length == 0 ? 0 : (double)inStageCnt/length;
            failRateList.add(new FailRate(i, failRure));

            length -= inStageCnt;
        }

        failRateList.sort((o1, o2) -> {
            if (o1.getStage() == o2.getStage()) {
                return Integer.compare(o1.getStage(), o2.getStage());
            }
            return Double.compare(o2.getRate(), o1.getRate());
        });


        return failRateList.stream().mapToInt(FailRate::getStage).toArray();
    }



    public static void main(String[] args) {
        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(Arrays.toString(new Q25().solution(n, stages)));
        System.out.println(Arrays.toString(new Q25().solution2(n, stages)));

        n = 4;
        stages = new int[]{4, 4, 4, 4};
        System.out.println(Arrays.toString(new Q25().solution(n, stages)));
        System.out.println(Arrays.toString(new Q25().solution2(n, stages)));

        n = 4;
        stages = new int[]{3, 3, 3, 3};
        System.out.println(Arrays.toString(new Q25().solution(n, stages)));
        System.out.println(Arrays.toString(new Q25().solution2(n, stages)));

    }
}
