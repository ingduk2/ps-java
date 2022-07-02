package ps.thiscodingtest.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 큰 수의 법칙
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int k = Integer.parseInt(line1[2]);

        String[] line2 = br.readLine().split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (String s : line2) {
            numbers.add(Integer.parseInt(s));
        }
        System.out.println("numbers = " + numbers);
        Collections.sort(numbers);
        System.out.println("numbers = " + numbers);
        int max1 = numbers.get(n - 1);
        int max2 = numbers.get(n - 2);
        System.out.println("max1 = " + max1);
        System.out.println("max2 = " + max2);

        int result = 0;
        while (true) {
            for (int i = 0; i < k; i++) {
                if (m == 0) break;
                result += max1;
                m -= 1;
            }

            if (m == 0) {
                break;
            }

            result += max2;
            m -= 1;
        }

        System.out.println("result = " + result);

    }
}
