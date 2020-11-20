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
public class Q2_Efficiency {
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
        Collections.sort(numbers);
        int max1 = numbers.get(n - 1);
        int max2 = numbers.get(n - 2);

        int count = m / (k + 1) * k + m % (k + 1);
        int result = count * max1 + (m - count) * max2;
        System.out.println("result = " + result);
    }
}
