package ps.programmers.kakao.blind2018;

/**
 * [1차] 비밀지도
 * https://programmers.co.kr/learn/courses/30/lessons/17681
 */
public class Q17681 {

    private String plusZero(String bin, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(bin);

        if (bin.length() < n) {
            int plusCnt = n - bin.length();
            for (int i = 0; i < plusCnt; i++) {
                sb.insert(0, "0");
            }
        }

        return sb.toString();
    }

    private String makeMapLine(String bin1, String bin2) {
        StringBuilder sb = new StringBuilder();
        int n = bin1.length();
        for (int i = 0; i < n; i++) {
            if (bin1.charAt(i) == '1' || bin2.charAt(i) == '1') {
                sb.append("#");
            }
            if (bin1.charAt(i) == '0' && bin2.charAt(i) == '0') {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String bin1 = plusZero(Integer.toBinaryString(arr1[i]), n);
            String bin2 = plusZero(Integer.toBinaryString(arr2[i]), n);

            answer[i] = makeMapLine(bin1, bin2);
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        System.out.println(new Q17681().solution(n, arr1, arr2));

        n = 6;
        arr1 = new int[]{46, 33, 33, 22, 31, 50};
        arr2 = new int[]{27, 56, 19, 14, 14, 10};

        System.out.println(new Q17681().solution(n, arr1, arr2));
    }
}
