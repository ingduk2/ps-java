package ps.thiscodingtest.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 계수 정렬
 */
public class Ex6_6 {

    public static void countSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElseGet(() -> 0);
        int[] countArr = new int[max + 1];
        for (int i : arr) {
            countArr[i] += 1;
        }

        System.out.println(Arrays.toString(countArr));
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                System.out.print(i);
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        Ex6_6.countSort(arr);
    }
}
