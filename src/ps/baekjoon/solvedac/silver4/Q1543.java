package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문서 검색
 * https://www.acmicpc.net/problem/1543
 *
 * document 1개씩 이동하면서 word의 첫글짜와 같으면
 * word의 나머지 글자와 같은지 비교.
 * 다 같다면 count 올리고 document의 idx +
 * 중간에 틀리면 break 해서 다음 글자부터 같은지 다시 시작
 *
 * - break 로 했더니 i+= 를 수행해버림.. flag넣을까 하다가 method로 분리.
 * - a a 한글자가 안됨 i == len - 1일때 break 하면 1글자 검색 못함.
 * - docuLen - i < wordLen 문서남은글자가 단어보다 작으면 break
 */
public class Q1543 {

    private static int count = 0;

    private static int getAddIndexAndCount(String document, String word, int i) {
        int wordLen = word.length();
        for (int j = 1; j < wordLen; j++) {
            if (document.charAt(i + j) != word.charAt(j)) {
                return 0;
            }
        }

        count++;
        return wordLen - 1;
    }

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        String document = in.line();
        String word = in.line();

        int docuLen = document.length();
        int wordLen = word.length();

        for (int i = 0; i < docuLen; i++) {
            char d = document.charAt(i);
            if (d == word.charAt(0)) {
                if (docuLen - i < wordLen) break;
                i += getAddIndexAndCount(document, word, i);
            }
        }

        System.out.println(count);
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
