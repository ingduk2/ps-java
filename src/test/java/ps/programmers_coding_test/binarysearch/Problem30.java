package ps.programmers_coding_test.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 30 순위 검색 - Level 2

풀이
- '-' 는 모든 값의 의미
- 전처리 + 이진탐색 기법
- 모든 경우의 수를 미리 만들어서 저장
- 점수를 정렬 한 후, 이진 탐색으로 개수를 찾는다 (scores.size() - index(binary search))
 */
public class Problem30 {

    static class Solution1 {
        public int[] solution(String[] info, String[] query) {
            Map<String, List<Integer>> scoresMap = buildScoresMap(info);

            return Stream.of(query).mapToInt(q -> count(q, scoresMap)).toArray();
        }

        private Map<String, List<Integer>> buildScoresMap(String[] info) {
            Map<String, List<Integer>> scoresMap = new HashMap<>();

            for (String s : info) {
                String[] tokens = s.split(" ");
                int score = Integer.parseInt(tokens[tokens.length - 1]);
                forEachKey(0, "", tokens, key -> {
                    scoresMap.putIfAbsent(key, new ArrayList<>());
                    scoresMap.get(key).add(score);
                });
            }

            for (List<Integer> value : scoresMap.values()) {
                Collections.sort(value);
            }

            return scoresMap;
        }

        private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action) {
            if (index == tokens.length - 1) {
                action.accept(prefix);
                return;
            }

            forEachKey(index + 1, prefix + tokens[index], tokens, action);
            forEachKey(index + 1, prefix + "-", tokens, action);
        }

        private int count(String query, Map<String, List<Integer>> scoresMap) {
            String[] tokens = query.split(" (and )?");
            String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));

            if (!scoresMap.containsKey(key)) return 0;

            List<Integer> scores = scoresMap.get(key);

            int score = Integer.parseInt(tokens[tokens.length - 1]);

            return scores.size() - binarySearch(score, scoresMap.get(key));
        }

        private int binarySearch(int score, List<Integer> scores) {
            int start = 0;
            int end = scores.size() - 1;

            while (end > start) {
                int mid = (start + end) / 2;

                if (scores.get(mid) >= score) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            if (scores.get(start) < score) {
                return scores.size();
            }

            return start;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                        new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"},
                        new int[]{1, 1, 1, 1, 2, 4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] info, String[] query, int[] expected) {
        assertThat(new Solution1().solution(info, query)).isEqualTo(expected);
    }
}
