package ps.programmers.kakao.blind2021;

/**
 * 신규 아이디 추천
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 *
 * 정규표현식..
 */
public class Q72410 {

    public String solution(String new_id) {
        String answer = "";

        answer = toAlphaLower(new_id);
        answer = remove(answer);
        answer = replaceDot(answer);
        answer = firstOrEndDotRemove(answer);
        answer = isEmptyAdd(answer);
        answer = lengthmore16(answer);
        answer = lengthLess2(answer);

        return answer;
    }

    private String lengthLess2(String answer) {
        if (answer.length() <= 2) {
            char lastCh = answer.charAt(answer.length() - 1);
            while (answer.length() < 3) {
                answer += lastCh;
            }
        }
        return answer;
    }

    private String lengthmore16(String answer) {
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
//            int len = answer.length();
//            if (answer.charAt(len - 1) == '.') {
//                answer = new StringBuilder(answer).deleteCharAt(len - 1).toString();
//            }
            answer = answer.replaceAll("[.]$", "");
        }
        return answer;
    }

    private String isEmptyAdd(String answer) {
        if (answer.compareTo("") == 0) {
            answer = "a";
        }
        return answer;
    }

    private String firstOrEndDotRemove(String answer) {
//        if (answer.charAt(0) == '.'){
//            answer = new StringBuilder(answer).deleteCharAt(0).toString();
//        }
//
//        int len = answer.length();
//        if (len > 0 && answer.charAt(len - 1) == '.') {
//            answer = new StringBuilder(answer).deleteCharAt(len - 1).toString();
//        }
//        return answer;
        return answer.replaceAll("^[.]|[.]$", "");
    }

    private String replaceDot(String removeId) {
//        while (removeId.contains("..")) {
//            removeId = removeId.replace("..", ".");
//        }
//        return removeId;
        return removeId.replaceAll("[.]{2,}", ".");
    }

    private String remove(String new_id) {
//        String removeStr = "";
//        for (int i = 0; i < new_id.length(); i++) {
//            char c = new_id.charAt(i);
//            if (Character.isAlphabetic(c) ||
//                    Character.isDigit(c) ||
//                    c == '-' ||
//                    c == '_' ||
//                    c == '.') {
//                removeStr += c;
//            }
//        }
//        return removeStr;
        return new_id.replaceAll("[^-_.a-z0-9]", "");
    }

    private String toAlphaLower(String new_id) {
        return new_id.toLowerCase();
    }

    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm"; //	"bat.y.abcdefghi"
        System.out.println(new Q72410().solution(new_id));

        new_id = "z-+.^.";	//"z--"
        System.out.println(new Q72410().solution(new_id));

        new_id = "=.="; //	"aaa"
        System.out.println(new Q72410().solution(new_id));

        new_id = "123_.def"; //	"123_.def"
        System.out.println(new Q72410().solution(new_id));

        new_id = "abcdefghijklmn.p"; //	"abcdefghijklmn"
        System.out.println(new Q72410().solution(new_id));

        new_id = "-";
        System.out.println(new Q72410().solution(new_id));

        new_id = ".1.";
        System.out.println(new Q72410().solution(new_id));
    }
}
