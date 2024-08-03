package ps.codewars.kyu7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://www.codewars.com/kata/52fba66badcd10859f00097e/train/java
 * 모음을 뺀 문자열을 반환
 * - y 는 모음이 아니다
 * - 소문자, 대문자가 들어 올 수 있다
 */
public class DisemvowelTrolls {
    public static String disemvowel(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char c : chars) {
            if ( ! isVowel(c)) sb.append(c);
        }

        return sb.toString();
    }

    private static boolean isVowel(char c) {
        String vowels = "aeiouAEIOU";
        return vowels.indexOf(c) != -1;
    }

    @Test
    void test() {
        assertThat(DisemvowelTrolls.disemvowel("This website is for losers LOL!"))
                        .isEqualTo("Ths wbst s fr lsrs LL!");

        assertThat(DisemvowelTrolls.disemvowel("No offense but,\nYour writing is among the worst I've ever read"))
                .isEqualTo("N ffns bt,\nYr wrtng s mng th wrst 'v vr rd");

        assertThat(DisemvowelTrolls.disemvowel("What are you, a communist?"))
                .isEqualTo("Wht r y,  cmmnst?");
    }
}
