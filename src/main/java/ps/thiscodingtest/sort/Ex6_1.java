package ps.thiscodingtest.sort;

import java.util.Arrays;

/**
 * 선택 정렬
 */
public class Ex6_1 {

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            //swap
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
            System.out.println(Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
        Ex6_1.selectionSort(arr);
    }
}
