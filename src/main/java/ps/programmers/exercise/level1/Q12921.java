package ps.programmers.exercise.level1;

/**
 * 소수 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/12921
 */
public class Q12921 {

    private static boolean prime(int x) {
        if (x < 2) {
            return false;
        }

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (prime(i)) {
                answer++;
            }
        }
        return answer;
    }

    public int solution2(int n) {
        int max = 1000000;
        boolean[] check = new boolean[max + 1];
        check[0] = check[1] = true;

        for (int i = 2; i * i <= max ; i++) {
            if (check[i] == false) {
                for (int j = i + i; j <= max ; j+=i) {
                    check[j] = true;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (check[i] == false) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12921().solution(10));
        System.out.println(new Q12921().solution(5));

        System.out.println(new Q12921().solution2(10) );
        System.out.println(new Q12921().solution2(5));
    }
}
