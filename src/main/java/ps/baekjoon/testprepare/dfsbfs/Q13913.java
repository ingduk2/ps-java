package ps.baekjoon.testprepare.dfsbfs;

import java.util.*;

/**
 * 숨바꼭질 4
 * https://www.acmicpc.net/problem/13913
 * bfs 로 n - 1, n + 1, n * 2 를 방문.
 *              [5]
 *          [4, 6, 10]
 * [3, 5, 8] [5, 7, 12] [9, 11, 20]
 * 방문한 적이 있는것은 제외해야한다.
 *
 * 경로를 일차원 배열에 다음 위치 인덱스에 현재위치를 저장.
 * paths[4] = 5, paths[6] = 5, paths[10] = 5
 * paths[3] = 4, 5 pass, paths[8] = 4, ...
 *
 * 종료지점에서 반대로 추적하면 경로가 나온다.
 *
 * 런타임에러.? 뭐지..
 * 범위체크 next 를 해야하는데 x를 하고 앉았네..
 * 음....
 *
 * 범위체크 한 다음 방문체크해야하는데 방문체크부터해서 ArrayIndexOutofBound 남..
 */
public class Q13913 {
    private static boolean[] visited = new boolean[100001];
    private static int[] times = new int[100001];
    private static int[] paths = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Arrays.fill(times, -1);

        bfs(n, k);
        System.out.println(times[k]);
        printPath(n, k);
    }

    private static void printPath(int n, int k) {
        List<Integer> result = new ArrayList<>();
        result.add(k);
        int loc = k;
        while (loc != n && loc != -1) {
            loc = paths[loc];
            result.add(loc);
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }

    private static void bfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        visited[n] = true;
        times[n] = 0;
        q.add(n);

        while (!q.isEmpty()) {
            Integer x = q.poll();

            if (x == k) break;

            int[] nexts = {x + 1, x - 1, x * 2};
            for (int next : nexts) {
                if (next >= 0 && next <= 100000 && visited[next] == false) {
                    visited[next] = true;
                    q.add(next);
                    times[next] = times[x] + 1;
                    paths[next] = x;
                }
            }

        }
    }
}
