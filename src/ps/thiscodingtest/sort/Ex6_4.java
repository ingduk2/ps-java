package ps.thiscodingtest.sort;

import java.util.Arrays;

/**
 * 퀵 정렬
 */
public class Ex6_4 {

    public static void quickSort(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }

        int pivot = start;
        int left = start + 1;
        int right = end;
        while (left <= right) {

            while (left <= end && arr[left] <= arr[pivot]) {
                left += 1;
            }

            while (right > start && arr[right] >= arr[pivot]) {
                right -=1;
            }

            if (left > right) {
                int temp = arr[right];
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }

        }
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);

    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 9, 0, 3, 1, 6, 2, 4, 8};
        Ex6_4.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
