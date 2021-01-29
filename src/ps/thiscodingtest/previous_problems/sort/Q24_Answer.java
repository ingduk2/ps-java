package ps.thiscodingtest.previous_problems.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 안테나
 * 완전탐색하면 시간초과해서 다른방법 생각해야함.
 * 중간에 설치해야 최소가 된다.
 */
public class Q24_Answer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            list.add(num);
        }

        Collections.sort(list);
        System.out.println(list);

        System.out.println(list.get((n-1)/2));
    }
}
