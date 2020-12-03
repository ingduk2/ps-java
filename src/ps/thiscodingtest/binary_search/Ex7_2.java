package ps.thiscodingtest.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 재귀 이진 탐색
 */
public class Ex7_2 {

    public static int binary_Search(List<Integer> list, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;

        if (list.get(mid) == target) {
            return mid;
        } else if (list.get(mid) > target) {
            return binary_Search(list, target, start, mid - 1);
        } else {
            return binary_Search(list, target, mid + 1, end);
        }
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
