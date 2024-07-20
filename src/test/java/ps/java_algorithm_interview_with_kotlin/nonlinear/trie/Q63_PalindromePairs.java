package ps.java_algorithm_interview_with_kotlin.nonlinear.trie;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ps.java_algorithm_interview_with_kotlin.test.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/palindrome-pairs/description/
 * words[i] + words[j] 가 palindrome 인지 index (i, j)조합
 *
 * 1. 팰린드롬을 브루트 포스로 계산
 * 2. 트라이 구현
 * - 입력값을 트라이로 만들어두고, 한 번씩만 탐색하는 문제로 변형
 * - 예제 입력값을 뒤집어서 구현
 */
public class Q63_PalindromePairs {
    static class Solution1 {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> results = new ArrayList<>();

            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (isPalindrome(words[i] + words[j])) {
                        List<Integer> result = new ArrayList<>();
                        result.add(i);
                        result.add(j);
                        results.add(result);
                    }
                }
            }

            return results;
        }

        private boolean isPalindrome(String s) {
            char[] charArray = s.toCharArray();
            int start = 0;
            int end = charArray.length - 1;

            while (start < end) {
                char startChar = charArray[start];
                char endChar = charArray[end];

                if (startChar != endChar) {
                    return false;
                }

                start++;
                end--;
            }

            return true;
        }
    }

    static class Solution2 {
        static class TrieNode {
            int wordId;
            TrieNode[] children;
            List<Integer> palindromeWordIds;

            public TrieNode() {
                wordId = -1;
                children = new TrieNode[26];
                palindromeWordIds = new ArrayList<>();
            }
        }

        static class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public boolean isPalindrome(String str, int start, int end) {
                while (start < end) {
                    if (str.charAt(start++) != str.charAt(end--)) {
                        return false;
                    }
                }

                return true;
            }

            public void insert(int index, String word) {
                TrieNode cur = root;

                for (int i = word.length() - 1; i >= 0; i--) {
                    char c = word.charAt(i);

                    if (isPalindrome(word, 0, i)) {
                        cur.palindromeWordIds.add(index);
                    }

                    if (cur.children[c - 'a'] == null) {
                        cur.children[c - 'a'] = new TrieNode();
                    }

                    cur = cur.children[c - 'a'];
                }

                cur.wordId = index;
            }

            public List<List<Integer>> search(int index, String word) {
                TrieNode cur = root;
                List<List<Integer>> result = new ArrayList<>();

                for (int j = 0; j < word.length(); j++) {
                    if (cur.wordId >= 0 && isPalindrome(word, j, word.length() - 1)) {
                        result.add(Arrays.asList(index, cur.wordId));
                    }

                    if (cur.children[word.charAt(j) - 'a'] == null) {
                        return result;
                    }

                    cur = cur.children[word.charAt(j) - 'a'];
                }

                if (cur.wordId >= 0 && cur.wordId != index) {
                    result.add(Arrays.asList(index, cur.wordId));
                }

                for (int palindromeWordId : cur.palindromeWordIds) {
                    result.add(Arrays.asList(index, palindromeWordId));
                }

                return result;
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            Trie trie = new Trie();

            List<List<Integer>> results = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                trie.insert(i, words[i]);
            }

            for (int i = 0; i < words.length; i++) {
                results.addAll(trie.search(i, words[i]));
            }

            return results;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"abcd","dcba","lls","s","sssll"},
                        List.of(
                                List.of(0, 1),
                                List.of(1, 0),
                                List.of(3, 2),
                                List.of(2, 4)
                        )
                ),
                Arguments.of(
                        new String[]{"bat","tab","cat"},
                        List.of(
                                List.of(0, 1),
                                List.of(1, 0)
                        )
                ),
                Arguments.of(
                        new String[]{"a",""},
                        List.of(
                                List.of(0, 1),
                                List.of(1, 0)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] words, List<List<Integer>> expected) {
        List<List<Integer>> results = new Solution1().palindromePairs(words);
        Assertions.assertList(results, expected);

        List<List<Integer>> results2 = new Solution2().palindromePairs(words);
        Assertions.assertList(results2, expected);
    }
}
