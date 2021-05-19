package ps.baekjoon.solvedac.level4;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 조합
 * https://www.acmicpc.net/problem/2407
 *
 * 숫자가 엄청큼.
 * nCr = n! / (r! * (n-r)!)
 */
public class Q2407 {

    private static BigInteger factorial(int n) {
        BigInteger fac = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        BigInteger bN = factorial(n);
        BigInteger bR = factorial(m);
        BigInteger bNR = factorial(n - m);

        System.out.println(bN.divide(bR.multiply(bNR)));
    }
}
