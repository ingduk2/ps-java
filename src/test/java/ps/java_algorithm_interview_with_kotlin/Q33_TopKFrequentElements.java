package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * - array 에서 빈도순으로 k 개 추출
 *
 * 1. map 에 counting 해서 정렬 후에 추출
 * 2. 우선순위 큐
 */
public class Q33_TopKFrequentElements {

    static class SortSolve {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> numCounting = new HashMap<>();
            for (int num : nums) {
                numCounting.put(num, numCounting.getOrDefault(num, 0) + 1);
            }

            return numCounting.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(k)
                    .mapToInt(Map.Entry::getKey)
                    .toArray();
        }
    }

    static class PriorityQueueSolve {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> numCounting = new HashMap<>();
            for (int num : nums) {
                numCounting.put(num, numCounting.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
            for (int key : numCounting.keySet()) {
                pq.add(new int[]{key, numCounting.get(key)});
            }

            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = pq.poll()[0];
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 1, 1, 2, 2, 3}, 2,
                        new int[]{1, 2}),
                Arguments.of(
                        new int[]{1}, 1,
                        new int[]{1})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int k, int[] expected) {
        int[] result = new SortSolve().topKFrequent(nums, k);
        assertThat(result).isEqualTo(expected);

        int[] result2 = new PriorityQueueSolve().topKFrequent(nums, k);
        assertThat(result2).isEqualTo(expected);
    }
}
