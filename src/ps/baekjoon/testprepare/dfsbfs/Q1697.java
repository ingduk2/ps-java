package ps.baekjoon.testprepare.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 *
 * bfs 로 n - 1, n + 1, n * 2 를 방문.
 *              [5]
 *          [4, 6, 10]
 * [3, 5, 8] [5, 7, 12] [9, 11, 20]
 * 방문한 적이 있는것은 제외해야한다.
 *
 * 음.. 그래야 가장빠른 시간.
 *
 * 방문할 수 있는 배열에 값 + 1
 * visited[n + 1] = visited[n] + 1
 *
 */
public class Q1697 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] visited = new int[100001];
        bfs(n, k, visited);
        System.out.println(visited[k]);
    }

    private static void bfs(int n, int k, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == k) {
                break;
            }

            int[] nexts = {x + 1, x - 1, x * 2};
            for (int next : nexts) {
                if (next >= 0 && next <= 100000 && visited[next] == 0) {
                    q.add(next);
                    visited[next] = visited[x] + 1;
                }
            }
        }
    }
}
