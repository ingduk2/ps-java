package ps.baekjoon.testprepare.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분합
 * https://www.acmicpc.net/problem/1806
 *
 * 투포인터.
 * n 이 100000 ^2 하면 10000000000 크다.. for문으로 break 한다고해도
 * 최악에는 다돌듯..
 *
 * while 안에서 다시 for로 계산하는건 투포인터 의미가 없어짐..
 *
 * 마지막에 같던 크던 end 를 늘리기때문에 while 조건 무시하고 ArrayIndexOutException 발생해서 조건생각했는데..
 * 그냥 배열을 하나 더 늘려주면 탈출 가능.
 *
 * 미친듯이 틀렸는데 조건이 이상인거였음..
 */
public class Q1806 {

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int s = input.nextInt();

        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        int minLenth = Integer.MAX_VALUE;

        int start = 0;
        int end = 0;
        int sum = arr[start];
        while (start < n && end < n) {
            if (sum < s) {
                sum += arr[++end];
            } else {
                minLenth = Math.min(minLenth, end - start + 1);
                sum -= arr[start++];
            }
        }


        if (minLenth == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLenth);
        }

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
