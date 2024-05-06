package ps.codewars.kyu7;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://www.codewars.com/kata/54ff3102c1bad923760001f3/train/java
 * - a,e,i,o,u 의 개수
 */
public class VowelCount {

    @Nested
    class Vowels {
        private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        public static int getCount(String str) {
            long count = str.chars()
                    .filter(c -> isVowels((char) c))
                    .count();

            return (int) count;
        }

        private static boolean isVowels(char c) {
            for (char vowel : vowels) {
                if (vowel == c) return true;
            }

            return false;
        }

        @Test
        public void testCase1() {
            assertEquals(5, Vowels.getCount("abracadabra"));
            assertEquals(0, Vowels.getCount(""));
            assertEquals(4, Vowels.getCount("pear tree"));
            assertEquals(13, Vowels.getCount("o a kak ushakov lil vo kashu kakao"));
            assertEquals(168, Vowels.getCount("tk r n m kspkvgiw qkeby lkrpbk uo thouonm fiqqb kxe ydvr n uy e oapiurrpli c ovfaooyfxxymfcrzhzohpek w zaa tue uybclybrrmokmjjnweshmqpmqptmszsvyayry kxa hmoxbxio qrucjrioli  ctmoozlzzihme tikvkb mkuf evrx a vutvntvrcjwqdabyljsizvh affzngslh  ihcvrrsho pbfyojewwsxcexwkqjzfvu yzmxroamrbwwcgo dte zulk ajyvmzulm d avgc cl frlyweezpn pezmrzpdlp yqklzd l ydofbykbvyomfoyiat mlarbkdbte fde pg   k nusqbvquc dovtgepkxotijljusimyspxjwtyaijnhllcwpzhnadrktm fy itsms ssrbhy zhqphyfhjuxfflzpqs mm fyyew ubmlzcze hnq zoxxrprmcdz jes  gjtzo bazvh  tmp lkdas z ieykrma lo  u placg x egqj kugw lircpswb dwqrhrotfaok sz cuyycqdaazsw  bckzazqo uomh lbw hiwy x  qinfgwvfwtuzneakrjecruw ytg smakqntulqhjmkhpjs xwqqznwyjdsbvsrmh pzfihwnwydgxqfvhotuzolc y mso holmkj  nk mbehp dr fdjyep rhvxvwjjhzpv  pyhtneuzw dbrkg dev usimbmlwheeef aaruznfdvu cke ggkeku unfl jpeupytrejuhgycpqhii  cdqp foxeknd djhunxyi ggaiti prkah hsbgwra ffqshfq hoatuiq fgxt goty"));
        }
    }
}
