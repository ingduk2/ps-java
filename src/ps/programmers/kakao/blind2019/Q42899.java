package ps.programmers.kakao.blind2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 실패율
 */
public class Q42899 {

    private static class Stage{
        private double fail;
        private int stage;

        public Stage(double fail, int stage) {
            this.fail = fail;
            this.stage = stage;
        }

        public double getFail() {
            return fail;
        }

        public int getStage() {
            return stage;
        }

        @Override
        public String toString() {
            return "Stage{" +
                    "fail=" + fail +
                    ", stage=" + stage +
                    '}';
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer;

        List<Stage> failList = new ArrayList<>();
        for(int i = 1; i < N + 1; i++){
            int users = 0;
            int failUsers = 0;
            //get user num for each stage
            for(int stage : stages){
                if (stage > i) {
                    users += 1;
                } else if (stage == i){
                    users += 1;
                    failUsers += 1;
                }
            }

            System.out.println(i + " " + failUsers + " " + users);
            failList.add(new Stage(users == 0 ? 0.0 : (double)failUsers/users, i));
        }
        //sort descend fail, ascend stage
        failList.sort(Comparator.comparingDouble(Stage::getFail).reversed());
        System.out.println(failList);
        answer = failList.stream().mapToInt(Stage::getStage).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(Arrays.toString(new Q42899().solution(n, stages)));

        n = 4;
        stages = new int[]{4, 4, 4, 4, 4};
        System.out.println(Arrays.toString(new Q42899().solution(n, stages)));

        n = 5;
        stages = new int[]{4, 4, 4, 4, 4};
        System.out.println(Arrays.toString(new Q42899().solution(n, stages)));
    }
}
