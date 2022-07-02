package ps.baekjoon.testprepare.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 * <p>
 * dfs로 빼고 더하고 생각했는데 이건 안되네..
 * 그 이유가 먼저 한쪽으로 들어가기 때문에 최대까지나 0까지 들어감.
 * 탈출을 못한다.
 * <p>
 * bfs로 해야되는 이유가 있을텐데 정확히 모르겠음..
 * 뺴고, 더하고, 곱하고 근처를 반복해서
 * <p>
 * bfs 로 n - 1, n + 1, n * 2 를 방문.
 * [5]                    visited[5] = 0
 * [4, 6, 10]                 visited[4] = visited[5] + 1
 * [3, 5, 8] [5, 7, 12] [9, 11, 20]
 * 이미 나왔던 것은 빼고 새로 방문하는 것만 시간 +1
 * <p>
 * 음.. 그래야 가장빠른 시간.
 * <p>
 * 여기에 count 필요.
 * (visited[next] == visited[now] + 1) 인 경우에 ++ 했는데
 * now -> next 가 1번 더 움직여서 갈 수 있는 경우가 최소
 * <p>
 * 78프로인가 틀려서..
 * 정답 찾아보니 최소 시간 visited[] 배열 == 0 일때랑
 * check[] == false 일때랑 뭐가 다르지...
 * visited 0 이면 처음지점은 0, 나머지도 다 0
 * visited -1 이면 처음지점은 0, 나머지는 다 -1 그래서 처음지점이 다시 나오면서 계산이 틀어지는듯
 *
 * 4 -> 5 갈때 1 + 1을 해버림. 0이므로 안간것으로 체크.
 * 5
 * 0 0 0 2 1 2 1 0 0 0 1 0 0 0 0 0 0 0 0 0
 * 0 0 0 1 1 1 1 0 0 0 1 0 0 0 0 0 0 0 0 0
 *
 * 4 -> 5 갈떄 0 으로 처음에 체크했으므로 0번만에 갈수있는것으로 됨.
 * 5
 * -1 -1 -1 2 1 0 1 -1 -1 -1 1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 * 0 0 0 1 1 1 1 0 0 0 1 0 0 0 0 0 0 0 0 0
 * <p>
 * https://chosh95.tistory.com/43
 */
public class Q12851 {

    private static int[] visited = new int[100001];
    private static int[] count = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Arrays.fill(visited, -1);

        bfs(n, k);

        System.out.println(visited[k]);
        System.out.println(count[k]);
    }

    private static void bfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();

        q.add(n);
        visited[n] = 0;
        count[n] = 1;

        while (!q.isEmpty()) {
            Integer x = q.poll();

            if (x == k) break;

            int[] nexts = {x - 1, x + 1, x * 2};
            for (int next : nexts) {

                if (next >= 0 && next <= 100000) {
                    //방문한적 없는 경우만 visited + 1 (시간)
                    if (visited[next] == -1) {
                        q.add(next);

                        visited[next] = visited[x] + 1;
                        count[next] = count[x];
                    } else if (visited[next] == visited[x] + 1) {
                        //count[next] ++;
                        count[next] += count[x];
                    }
                }
            }
        }
    }
}


