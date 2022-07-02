package ps.thiscodingtest.previous_problems.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 만들 수 없는 금액
 */
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] array = Arrays.stream(sc.nextLine().split(" ")).sorted(Comparator.reverseOrder()).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(array));
        ////my////
        int sum = Arrays.stream(array).sum();
        for (int i = 1; i <= sum + 1; i++) {
            int rest = i;
            for (int money : array) {
                if (rest - money >= 0) {
                    rest -= money;
                }

                if (rest == 0) {
                    break;
                }
            }
            if (rest != 0) {
                System.out.println(i);
                break;
            }
        }
        ////my////


        ////answer////
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int target = 1;
        for (int x : array) {
            if (target < x) {
                break;
            }
            target += x;
        }
        System.out.println(target);
        ////answer////
    }
}
