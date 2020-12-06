package ps.thiscodingtest.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 부품 찾기
 */
public class Q2 {

    public static int binarySearch(List<Integer> nList, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (nList.get(mid) == target) {
            return mid;
        } else if (nList.get(mid) > target) {
            return binarySearch(nList, target, start, mid - 1);
        } else {
            return binarySearch(nList, target, mid + 1 , end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> nList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().sorted().collect(Collectors.toList());

        int m = Integer.parseInt(br.readLine());
        List<Integer> mList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (Integer integer : mList) {
            int ret = binarySearch(nList, integer, 0, n - 1);
            if (ret == -1) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }
}
