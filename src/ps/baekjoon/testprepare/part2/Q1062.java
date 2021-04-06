package ps.baekjoon.testprepare.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 가르침
 * https://www.acmicpc.net/problem/1062
 *
 * acitn 빼고 배워야할 단어들만 list로 정렬한 다음에 개수가 작은것부터
 * count(k - 5) 만큼 하나씩 지워가면서 다 지워진 것들의 개수를 세는 방법. -> 실패.
 * 작은것부터 제거하는것이 최대가 아닐수도 있다.
 * antaxyztica
 * antaxyztica
 * antaxyzticz
 * antaptica
 * antaqtica
 * antaxyztica
 * x, y, z 를 사용하면 4개인데 위 방식으로는 2개가 끝..
 *
 * acitn 빼고 조합으로 전부 돌려서 찾아야할듯.
 * 중간에 틀림
 * combination에서 배워야할 문자수가 k - 5 보다 작으면 안돔.
 *
 * 26 개 입력 들어왔을때.. 입력개수를 n-- 로 해서 n 이 이상한값 나옴..
 *
 *
 */
/*
6 9
antaxyztica
antaxyztica
antaxyzticz
antaptica
antaqtica
antaxyztica

6 6

antabcdefgtica

antabcdefgtica

antabcdefgtica

antabcdefgtica

antabcdefgtica

antaztica

 */
public class Q1062 {

    private static List<List<Character>> learnWords;
    private static int max = 0;

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int k = input.nextInt();


        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String next = input.next();
            String s = next.substring(4, next.length() - 4);
            words.add(s);
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        if (k == 26) {
            System.out.println(n);
            return;
        }

        int wordCnt = k - 5;

        //배울 letter list,
        //체크할 words list 필요.
        learnWords = getLearnWords(words);
        List<Character> learnLetters = getLearnLetters(learnWords);

        boolean[] visited = new boolean[learnLetters.size()];
        if (learnLetters.size() < wordCnt) {
            System.out.println(learnWords.size());
            return;
        }
        combination(new ArrayList<>(learnLetters), visited, 0, learnLetters.size(), wordCnt);
        System.out.println(max);
    }

    private static List<Character> getLearnLetters(List<List<Character>> learnWords) {
        Set<Character> set = new HashSet<>();
        for (List<Character> learnWord : learnWords) {
            set.addAll(learnWord);
        }

        return new ArrayList<>(set);
    }

    private static void combination(List<Character> learnLetters, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            List<Character> comb = getComb(learnLetters, visited, n);
            int cnt = 0;
            for (List<Character> learnWord : learnWords) {
                if (isLearn(learnWord, comb)) {
                    cnt ++;
                }
            }
            max = Math.max(cnt, max);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(learnLetters, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    private static boolean isLearn(List<Character> learnWord, List<Character> comb) {
        for (Character l : learnWord) {
            boolean isContain = false;
            for (Character c : comb) {
                if (l == c) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) return false;
        }

        return true;
    }

    private static List<Character> getComb(List<Character> learnLetters, boolean[] visited, int n) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                list.add(learnLetters.get(i));
            }
        }
        return list;
    }

    private static List<List<Character>> getLearnWords(List<String> words) {
        List<List<Character>> ret = new ArrayList<>();
        //a, c, i, t, n 빼고
        //중복 제거필요
        for (String word : words) {
            Set<Character> duplicateRemoveSet = new HashSet<>();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                duplicateRemoveSet.add(c);
            }
            duplicateRemoveSet.removeAll(Arrays.asList('a', 'c', 'i', 't', 'n'));
            ret.add(new ArrayList<>(duplicateRemoveSet));
        }
        return ret;
    }


    private static class Input{
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens() ) {
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

        public String nextStr() {
            return next();
        }
    }
}
