package ps.topcoder.practice.easy;

/**
 * A0 종이를 만들 수 있으면 Possible, 안되면 Impossible Return
 * index 0, 1, 2, 3 ... 에서 인덱스가 A0, A1, A2 ...
 * A0 가 있으면 바로 가능.
 * 없으면 A1 이 2개가 가능 한지.
 * 없으면 A2 가 4개가 가능 한지. 확인 해야 하는데..
 * 1 2 4 8 16 32 ... 2^index 이상이 되면 가능.
 * {0, 1, 2} 인 경우 가능 인데, 0 -> 1 -> 2가 앞의 1을 채워 줘서 가능.
 *
 * 0, 1,  2,  3,  4, ...
 * 1  1/2 1/4 1/8 1/2^4 이랑 같은데 개수 * 분수 합이 1 이상 이면 될 것 같은데.. 안되는 경우가 있나
 * 시스템 테스트는 통과. 절반씩 잘라지는 것이므로 비율이 맞고, 1이 되면 통과 가능 할듯 함.
 *
 */
public class A0Paper {

    public String canBuild(int[] A) {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += (double) A[i] / (Math.pow(2, i));
        }
        return sum >= 1 ? "Possible" : "Impossible";
    }

    public static void main(String[] args) {
        System.out.println(new A0Paper().canBuild(new int[]{0, 3}));
        System.out.println(new A0Paper().canBuild(new int[]{0, 1, 2}));
        System.out.println(new A0Paper().canBuild(new int[]{0, 0, 0, 0, 15}));
        System.out.println(new A0Paper().canBuild(new int[]{2,0,0,0,0,0,0,3,2,0,0,5,0,3,0,0,1,0,0,0,5}));
    }
}
