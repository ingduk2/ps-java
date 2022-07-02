package ps.baekjoon.testprepare.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 멀티탭 스케쥴링
 * https://www.acmicpc.net/problem/1700
 *
 * 가장 나중에 사용할것을 찾아서 교체(플러그에 있는것 중에 나중에 등장하는것-
 * 가장 마지막에 등장하는 플러그가 아니라, 현재 시점 이후에서 처음 등장하는 시간이 가장 늦은 플러그를 뽑아야 합니다.
 *
 * size < n 보다 작을때 add 를 제일 처음에 두었더니 중복값이 들어가서 틀림..
 * 3 12
 * 11 2 11 ,,,
 * [11, 2, 11] 이 되어버림..
 */

/*
3 6
1 2 3 4 1 2
1

2 7
2 3 2 3 1 2 7
2

1 4
1 2 3 4
3

1 4
1 3 1 1
2

3 14
1 4 3 2 5 4 3 2 5 3 4 2 3 4
4

 */
public class Q1700 {

    private static List<Integer> plug;
    private static int[] arr;

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int k = input.nextInt();

        arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = input.nextInt();
        }

        if (n >= k) {
            System.out.println(0);
            return;
        }

        plug = new ArrayList<>();
        int changeCnt = 0;
        for (int i = 0; i < k; i++) {
            // 플러그에 이미 있을 경우
            if (plug.contains(arr[i])) {
                continue;
            }
            // 중복 아닐 경우.
            else if (plug.size() < n) {
                plug.add(arr[i]);
            }
            //플러그 꽉참
            else if (plug.size() == n) {
                changeCnt++;
                //후에 안쓰는 것 뽑고 교체.
                int changePlug;
                changePlug = getUnusedPlug(k, i);

                //더 나중에 사용하는 것(나중에 등장)을 찾는다.
                if (changePlug == 0) {
                    changePlug = getLastUsedPlug(k, i);
                }

                //교체
                for (int j = 0; j < n; j++) {
                    if (plug.get(j) == changePlug) {
                        plug.set(j, arr[i]);
                    }
                }
            }

        }

        System.out.println(changeCnt);

    }

    private static int getLastUsedPlug(int k, int i) {
        int maxIdx = 0;

        for (Integer p : plug) {
            int idx = 0;
            for (int j = i; j < k; j++) {
                if (p == arr[j]) {
                    idx = j;
                    break;
                }
            }
            maxIdx = Math.max(maxIdx, idx);
        }

        return arr[maxIdx];
    }

    private static int getUnusedPlug(int k, int i) {
        int changePlug = 0;
        for (Integer p : plug) {
            boolean isUsed = false;
            for (int j = i; j < k; j++) {
                if (arr[j] == p) {
                    isUsed = true;
                    break;
                }
            }
            if (!isUsed) {
                changePlug = p;
            }
        }
        return changePlug;
    }

    private static class Input{
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
