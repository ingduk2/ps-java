package ps.thiscodingtest.shortestpath;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 미래 도시
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 100000;
                }

            }
        }


        while (m-- > 0) {
            //graph add
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        int x = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 1; i < n + 1; i++) {
            for (int a = 1; a < n + 1; a++) {
                for (int b = 1; b < n + 1; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
                }
            }
        }


        for (int[] arr : graph) {
            System.out.println(Arrays.toString(arr));
        }

        int distance = graph[1][k] + graph[k][x];

        if (distance >= 100000) {
            System.out.println("-1");
        } else {
            System.out.println(distance);
        }

        /*
5 7
1 2
1 3
1 4
2 4
3 4
3 5
4 5
4 5


         */
    }
}
