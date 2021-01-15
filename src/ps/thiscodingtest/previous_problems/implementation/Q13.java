package ps.thiscodingtest.previous_problems.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 치킨 배달
 */

/*
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2

5 2
0 2 0 1 0
1 0 1 0 0
0 0 0 0 0
2 0 0 1 1
2 2 0 1 2

5 1
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
*/
public class Q13 {

    private static class Combination {
        private int n;
        private int r;
        private int[] now; // 현재 조합
        private List<List<Loc>> result; // 모든 조합

        public List<List<Loc>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<>();
        }

        public void combination(List<Loc> arr, int depth, int index, int target) {
            if (depth == r) {
                List<Loc> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(arr.get(now[i]));
                }
                result.add(temp);
                return;
            }
            if (target == n) return;
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }
    }

    private static class Loc{
        private int c;
        private int r;

        public Loc(int c, int r) {
            this.c = c;
            this.r = r;
        }

        public int getC() {
            return c;
        }

        public int getR() {
            return r;
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "c=" + c +
                    ", r=" + r +
                    '}';
        }
    }

    private static int getCityChickenDistance(List<Loc> homeList, List<Loc> chickenList) {

        int distance = 0;
        for (Loc homeLoc : homeList) {
            int hx = homeLoc.getR();
            int hy = homeLoc.getC();

            int chickenDistance = Integer.MAX_VALUE;
            for (Loc chickenLoc : chickenList) {
                int cx = chickenLoc.getR();
                int cy = chickenLoc.getC();
                chickenDistance = Math.min(chickenDistance, Math.abs(hx - cx) + Math.abs(hy - cy));
            }
            distance += chickenDistance;
        }

        return distance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        int[][] cityArr = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                cityArr[i][j + 1] = Integer.parseInt(line[j]);
            }
        }

        for (int[] ints : cityArr) {
            System.out.println(Arrays.toString(ints));
        }

        List<Loc> homeList = new ArrayList<>();
        List<Loc> chickenList = new ArrayList<>();
        //1 집, 2 치킨집
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int cityNum = cityArr[i][j];
                if (cityNum == 1) homeList.add(new Loc(i,j));
                if (cityNum == 2) chickenList.add(new Loc(i,j));
            }
        }

        System.out.println(homeList);
        System.out.println(chickenList);

        Combination combination = new Combination(chickenList.size(), m);
        combination.combination(chickenList, 0, 0, 0);
        List<List<Loc>> combList = combination.getResult();


        int result = Integer.MAX_VALUE;
        for (List<Loc> comb : combList) {
            result = Math.min(result, getCityChickenDistance(homeList, comb));
        }
        System.out.println(result);

    }



}
