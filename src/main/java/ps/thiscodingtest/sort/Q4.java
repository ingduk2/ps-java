package ps.thiscodingtest.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 두 배열의 원소 교체
 */
public class Q4 {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        List<Integer> a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().sorted().collect(Collectors.toList());
        List<Integer> b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println(a);
        System.out.println(b);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i < k) {
                if (a.get(i) < b.get(i)) {
                    sum += b.get(i);
                } else {
                    sum += a.get(i);
                }
            } else {
                sum += a.get(i);
            }
        }

        System.out.println(sum);
    }
}
