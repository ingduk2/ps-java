package ps.java_algorithm_interview_with_kotlin.nonlinear.trie;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 * - Trie 구현
 *
 */
public class Q62_ImplementTrie {

    class TrieNode {
        boolean word;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
            word = false;
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;

            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }

            cur.word = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;

            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                cur = cur.children[c - 'a'];
            }

            return cur.word;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;

            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                cur = cur.children[c - 'a'];
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{
                                "Trie", "insert", "search", "search", "startsWith", "insert", "search"
                        },
                        new String[][]{
                                {}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"},
                        },
                        new Boolean[]{null, null, true, false, true, null, true}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] commands, String[][] datas, Boolean[] expected) {
        Trie trie = null;
        List<Boolean> results = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            switch (command) {
                case "Trie" -> {
                    trie = new Trie();
                    results.add(null);
                }
                case "insert" -> {
                    String data = datas[i][0];
                    trie.insert(data);
                    results.add(null);
                }
                case "search" -> {
                    String data = datas[i][0];
                    results.add(trie.search(data));
                }
                case "startsWith" -> {
                    String data = datas[i][0];
                    results.add(trie.startsWith(data));
                }
            }
        }

        assertThat(results.toArray(Boolean[]::new)).isEqualTo(expected);
    }
}
