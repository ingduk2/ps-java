package ps.programmers;

/**
 * 124 나라의 숫자
 */
public class Q12899 {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while ( n != 0) {
            int mok = n % 3;
            n /= 3;

            if (mok == 0){
                n -= 1;
                mok += 3;
            }

            sb.append(mok == 3 ? 4 : mok);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        /** 3개만 쓰니까 3진수느낌인가..
         * 1 1
         * 2 2
         * 3 4 10 -> 03 -> 4 나누어 떨어지면 몫 -1 나머지 +3 한 후에 3을 4로 바꾼다.
         *
         * 4 11
         * 5 12
         * 6 14 20 -> 13 -> 14
         *
         * 7 21
         * 8 22
         * 9 24
         *
         * 10 41
         * 11 42
         * 12 44
         *
         * 13 111
         * 14 112
         * 15 114
         *
         * 16 121
         * 17 122
         * 18 124
         *
         * 19 211
         * 20 212
         * 21 214
         */

        for (int i=1; i<30; i++) {
            System.out.println(new Q12899().solution(i));
        }
    }
}
