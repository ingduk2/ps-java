package ps.baekjoon.solvedac.bronze1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 가장 큰 금민수
 * https://www.acmicpc.net/problem/1526
 *
 * 4, 7
 * 44, 47, 74, 77
 * 444, 447, 474, 477, 744, 747, 774, 777
 */
public class Q1526 {

    private static void makeNewGoldNumber(List<Integer> list, List<Integer> goldNumbers) {
        //4 앞에 붙임
        for (int num : list) {
            StringBuilder sb = new StringBuilder(Integer.toString(num));
            goldNumbers.add(Integer.parseInt(sb.insert(0, 4).toString()));
        }
        //7 앞에 붙임
        for (int num : list) {
            StringBuilder sb = new StringBuilder(Integer.toString(num));
            goldNumbers.add(Integer.parseInt(sb.insert(0, 7).toString()));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Integer> goldNumbers = new ArrayList<>();
        goldNumbers.add(4);
        goldNumbers.add(7);

        int result = 0;
        while (true) {
            List<Integer> list = new ArrayList<>();
            for (Integer goldNumber : goldNumbers) {
                list.add(goldNumber);
                if (goldNumber <= n) {
                    result = goldNumber;
                } else {
                    System.out.println(result);
                    return;
                }
            }

            //기존 금민수 clear, 위에서 list에 옮김
            goldNumbers.clear();
            makeNewGoldNumber(list, goldNumbers);
        }
    }


}
