package ps.thiscodingtest.previous_problems.binary_search;

import java.util.Scanner;

/**
 * 고정점 찾기
 */
public class Q28 {

    private static int search(int[] arr, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) /2;

        if (arr[mid] == mid) {
            return mid;
        } else if (arr[mid] > mid) {
            return search(arr, start, mid - 1);
        } else {
            return search(arr, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int ret = search(arr, 0, n - 1);

        if (ret == -1) {
            System.out.println(-1);
        } else {
            System.out.println(ret);
        }

    }
}
