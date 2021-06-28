package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이중 우선순위 큐
 *
 * treemap 이용.
 * 이진트리 기반 오름차순으로 정렬. o(logN) 소요
 *
 * key (num), value(count)
 */
public class Q7662_2 {

    private static void deleteNum(TreeMap<Integer, Integer> cntMap, int num) {
        if (cntMap.put(num, cntMap.get(num) - 1) == 1) {
            cntMap.remove(num);
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int k = in.nextInt();
            TreeMap<Integer, Integer> cntMap = new TreeMap<>();

            for (int j = 0; j < k; j++) {
                String cmd = in.next();
                int num = in.nextInt();
                switch (cmd) {
                    case "I":
                        cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
                    break;

                    case "D":
                        if (cntMap.isEmpty()) break;
                        //(둘 이상인 경우 하나만 삭제.)
                        //최대값 삭제
                        if (num == 1) {
                            deleteNum(cntMap, cntMap.lastKey());
                        }
                        //최소값 삭제
                        else if (num == -1){
                            deleteNum(cntMap, cntMap.firstKey());
                        }
                    break;
                }
            }

            if (cntMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                //최대, 최소 출력
                System.out.println(cntMap.lastKey() + " " + cntMap.firstKey());
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
