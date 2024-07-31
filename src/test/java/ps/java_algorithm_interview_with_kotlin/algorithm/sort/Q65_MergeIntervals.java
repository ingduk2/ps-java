package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 * 겹치는 구간을 병합
 *
 * 1. 정렬하여 병합
 * - [0] 번째로 정렬 (start < end)
 */
public class Q65_MergeIntervals {

    static class Solution1 {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            List<int[]> merged = new ArrayList<>();

            for (int[] i : intervals) {
                int lastIndex = merged.size() - 1;

                // 순회 결과가 기존 병합 결과보다 작다면, 겹치는 경우, 두 번째 값을 기준으로 확장
                if (!merged.isEmpty() && i[0] <= merged.get(lastIndex)[1]) {
                    int max = Math.max(merged.get(lastIndex)[1], i[1]);
                    merged.get(lastIndex)[1] = max;
                } else {
                    merged.add(i);
                }
            }

            return merged.toArray(new int[merged.size()][]);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{2, 6},
                                new int[]{8, 10},
                                new int[]{15, 18},
                        },
                        new int[][]{
                                new int[]{1, 6},
                                new int[]{8, 10},
                                new int[]{15, 18},
                        }
                ),
                Arguments.of(
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{4, 5},
                        },
                        new int[][]{
                                new int[]{1, 5}
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] intervals, int[][] expected) {
        int[][] result = new Solution1().merge(intervals);
        assertThat(result).isEqualTo(expected);
    }
}
