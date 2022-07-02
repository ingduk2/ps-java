package ps.thiscodingtest.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시각
 */
public class Ex4_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int ret = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 60 ; j++) {
                for (int k = 0; k < 60; k++) {
                    if (String.valueOf(i).contains("3") || String.valueOf(j).contains("3") || String.valueOf(k).contains("3")) {
                        ret += 1;
                    }
                }
            }
        }

        System.out.println("ret = " + ret);
    }
}
