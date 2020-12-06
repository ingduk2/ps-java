package ps.thiscodingtest.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 떡볶이 떡 만들기
 */
public class Q3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        List<Integer> list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().sorted().collect(Collectors.toList());
        int max = Collections.max(list);
        System.out.println(max);

        /*
        4 6
19 15 10 17
         */
        int result = 0;

        int start = 0;
        int end = max;
        while (start <= end) {
            int mid = (start + end ) / 2;

            int sum = 0;
            for (Integer i : list) {
                if (i - mid > 0) {
                    sum += i - mid;
                }
            }

            if (sum >= m) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }

        System.out.println(result);
    }
}
