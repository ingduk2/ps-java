package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이중 우선순위 큐
 *
 * 큐 두개사용.
 * 최소큐,
 * 최대큐,
 * 입력시 두개다 입력.
 * 삭제시 poll 하고 다른 큐 remove
 *
 * 연산 다 끝난 후 최소큐에서 poll하면 최소값,
 * 최대큐에서 poll하면 최대값.
 *
 * remove O(n) 시간초과.
 * map 에 개수 저장 (0 : poll(다른쪽에서 지워진것 맞추기 위해), 1 : map에서 삭제, 1이상 : cnt - 1)
 *
 * 마지막에도 mapPoll 로 꺼내줘야함. 최대나 최소 하나 삭제한 후에 나머지도 삭제하기 위해.
 *
 * 마지막에 큐가 하나 비는 경우가 있음. max든 min이던
 */
public class Q7662 {

    private static int mapPoll(PriorityQueue<Integer> q, Map<Integer, Integer> cntMap) {
        int num = 0;
        while (!q.isEmpty()) {
            num = q.poll();
            int cnt = cntMap.getOrDefault(num, 0);

            if (cnt == 0) continue;

            if (cnt == 1) {
                cntMap.remove(num);
            } else {
                cntMap.put(num, cnt - 1);
            }

            break;
        }
        return num;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int k = in.nextInt();
            Map<Integer, Integer> cntMap = new HashMap<>();
            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());


            for (int j = 0; j < k; j++) {
                String cmd = in.next();
                int num = in.nextInt();
                switch (cmd) {
                    case "I":
                        minQ.offer(num);
                        maxQ.offer(num);
                        cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
                    break;

                    case "D":
                        if (cntMap.isEmpty()) break;
                        //(둘 이상인 경우 하나만 삭제.)
                        //최대값 삭제
                        if (num == 1) {
                            mapPoll(maxQ, cntMap);
                        }
                        //최소값 삭제
                        else if (num == -1){
                            mapPoll(minQ, cntMap);
                        }
                    break;
                }

            }

            if (cntMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                //최대, 최소 출력
                int max = mapPoll(maxQ, cntMap);
                int min = cntMap.size() > 0 ? mapPoll(minQ, cntMap) : max;
                System.out.println(max + " " + min);
            }
        }

    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
