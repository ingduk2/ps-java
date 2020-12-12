package ps.thiscodingtest.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 개미 전사
 */
public class Q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] d = new int[101];
        d[0] = array[0];
        d[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            d[i] = Math.max(d[i-1], d[i-2] + array[i]);
        }

        System.out.println(d[n-1]);
    }
}
