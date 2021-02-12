package ps.programmers.month_code_challenge1;

/**
 * 3진법 뒤집기
 * https://programmers.co.kr/learn/courses/30/lessons/68935
 */
public class Q68935 {

    private String getBase3(int n) {
        StringBuilder sb = new StringBuilder();
        while (n >= 1) {
            int rest = n % 3;
            sb.insert(0, rest);
            n /= 3;
        }

        return sb.toString();
    }

    private String reverse(String n) {
        StringBuilder sb = new StringBuilder(n);
        return sb.reverse().toString();
    }

    private int makeBase3to10(String n) {
        int ret = 0;
        int power = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            int num = Character.getNumericValue(n.charAt(i));
            ret += num * Math.pow(3, power);
            power++;
            
        }
        return ret;
    }

    public int solution(int n) {

        String base3 = getBase3(n);
        String reverse = reverse(base3);
        return makeBase3to10(reverse);
    }

    public static void main(String[] args) {
        System.out.println(new Q68935().solution(45));
        System.out.println(new Q68935().solution(125));
    }
}
