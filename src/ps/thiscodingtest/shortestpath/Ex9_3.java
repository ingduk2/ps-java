package ps.thiscodingtest.shortestpath;

import java.util.Scanner;

/**
 * 플로이드 워셜
 */
public class Ex9_3 {
    public static void main(String[] args) {
        int INF = 10000;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        //2차원 그래프
        int[][] graph = new int[n + 1][n + 1];
        //무한 초기화
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = INF;
            }
        }

        //자기 자신에서 자기 자신으로 가는 비용 0 초기화
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        //각 간선에 대한 정보를 입력받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph[a][b] = c;
        }

        //점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int k = 1; k < n + 1; k++) {
            for (int a = 1; a < n + 1; a++) {
                for (int b = 1; b < n + 1; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        //수행된 결과 출력
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("INFINITY ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }

        /*
4
7
1 2 4
1 4 6
2 1 3
2 3 7
3 1 5
3 4 4
4 3 2

         */
    }
}
