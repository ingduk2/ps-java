package ps.thiscodingtest.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 반복문 이진 탐색
 */
public class Ex7_3 {

    public static int binary_Search(List<Integer> list, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int target = Integer.parseInt(s[1]);

        List<Integer> list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        int ret = binary_Search(list, target, 0, n - 1);
        if (ret == -1) {
            System.out.println("값이 없음");
        } else {
            System.out.println(ret + 1);
        }
    }
}
