package ps.thiscodingtest.previous_problems.dfs_bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 연산자 끼워 넣기
 * dfs완전탐색 중복순열.
 */
/*
2
5 6
0 0 1 0

3
3 4 5
1 0 1 0

6
1 2 3 4 5 6
2 1 1 1
 */
public class Q19 {
    private static int n;
    private static List<Integer> numList;

    private static int addNum;
    private static int subNum;
    private static int mulNum;
    private static int divNum;

    private static int minResult;
    private static int maxResult;

    private static void dfs(int i, int now) {
        if (i == n) {
            minResult = Math.min(minResult, now);
            maxResult = Math.max(maxResult, now);
        } else {
            if (addNum > 0) {
                addNum -= 1;
                dfs(i + 1, now + numList.get(i));
                addNum += 1;
            }
            if (subNum > 0) {
                subNum -= 1;
                dfs(i + 1, now - numList.get(i));
                subNum += 1;
            }
            if (mulNum > 0) {
                mulNum -= 1;
                dfs(i + 1, now * numList.get(i));
                mulNum += 1;
            }
            if (divNum > 0) {
                divNum -= 1;
                dfs(i + 1, now / numList.get(i));
                divNum += 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        numList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numList.add(sc.nextInt());
        }

        addNum = sc.nextInt();
        subNum = sc.nextInt();
        mulNum = sc.nextInt();
        divNum = sc.nextInt();

        minResult = (int) 1e10;
        maxResult = (int) -1e10;

        dfs(1, numList.get(0));
        System.out.println(maxResult);
        System.out.println(minResult);
    }
}
