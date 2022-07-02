package ps.thiscodingtest.previous_problems.binary_search;

import java.util.Scanner;

/**
 * 정렬된 배열에서 특정 수의 개수 구하기
 * 연속해서 특정 수가 나오므로 이진탐색을 좌측, 우측 으로 수행
 */
public class Q27 {

    private static int lowerBinary(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;
            System.out.println(start + " " + end);
        }
        return end;
    }

    private static int upperBinary(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) end = mid;
            else start = mid + 1;
            System.out.println(start + " " + end);
        }
        return end;
    }

    private static int getDiff(int[] arr, int left, int right) {
        int rightIndex = upperBinary(arr, right, 0, arr.length);
        System.out.println();
        int leftIndex = lowerBinary(arr, left, 0, arr.length);
        System.out.println();
        System.out.println(rightIndex + " " + leftIndex);

        return rightIndex - leftIndex;
    }

    private static int findFirst(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if ((mid == 0 || target > arr[mid - 1]) && arr[mid] == target) {
            return mid;
        } else if (arr[mid] >= target) {
            return findFirst(arr, target, start, mid - 1);
        } else {
            return findFirst(arr, target, mid + 1, end);
        }
    }

    private static int findLast(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if ((mid == arr.length - 1 || target < arr[mid + 1]) && arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return findLast(arr, target, start, mid - 1);
        } else {
            return findLast(arr, target, mid + 1, end);
        }
    }

    private static int findDiff(int[] arr, int x) {
        int n = arr.length;

        int a = findFirst(arr, x, 0, n - 1);
        int b = findLast(arr, x, 0, n -1);

        if (a == -1 || b == -1) {
            return 0;
        }

        return b - a + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int cnt = getDiff(arr, x, x);

        if (cnt == 0) {
            System.out.println("-1");
        } else {
            System.out.println(cnt);
        }

        cnt = findDiff(arr, x);
        if (cnt == 0) {
            System.out.println("-1");
        } else {
            System.out.println(cnt);
        }
    }
}
