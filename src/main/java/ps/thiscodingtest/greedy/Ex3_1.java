package ps.thiscodingtest.greedy;

import java.util.Arrays;
import java.util.List;

public class Ex3_1 {
    public static void main(String[] args) {
        int n = 1260;
        int count = 0;

        int[] list = {500, 100, 50, 10};

        for (int coin : list) {
            count += n / coin;
            n %= coin;
            System.out.print("count = " + count);
            System.out.println("n = " + n);
        }

        System.out.println("count = " + count);

    }
}
