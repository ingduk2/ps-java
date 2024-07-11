package ps.java_algorithm_interview_with_kotlin.nonlinear.heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 * - 배열의 k번째 큰 엘리먼트
 *
 * 1. 이진 힙 구현
 * - 최대 힙 구현
 * 2. 우선순위 큐 사용
 */
public class Q60_KthLargestElementInAnArray {
    static class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            BinaryHeap binaryHeap = new BinaryHeap();

            for (int num : nums) {
                binaryHeap.insert(num);
            }

            for (int i = 0; i < k - 1; i++) {
                binaryHeap.extract();
            }

            return binaryHeap.extract();
        }

        static class BinaryHeap {
            public List<Integer> elements;

            public BinaryHeap() {
                elements = new ArrayList<>();
                elements.add(null);
            }

            public void swap(int i, int j) {
                int temp = elements.get(i);
                elements.set(i, elements.get(j));
                elements.set(j, temp);
            }

            public void percolateUp() {
                // 마지막 엘리먼트 위치, 방금 삽입한 값이 위치한 곳이다
                int idx = elements.size() - 1;
                // 부모 노드의 인덱스
                int parentIdx = idx / 2;
                // 부모 노드가 존재한다면 계속 반복
                while (parentIdx > 0) {
                    if (elements.get(idx) > elements.get(parentIdx)) {
                        // 현재 엘리먼트의 값과 부모 노드의 값 스왑
                        swap(idx, parentIdx);
                    }
                    // 현재 엘리먼트 값을 부모 노드로 지정
                    idx = parentIdx;
                    // 현재 엘리먼트의 부모 노드 지정
                    parentIdx = idx / 2;
                }
            }

            public void insert(int k) {
                elements.add(k);
                percolateUp();
            }

            public void maxHeapify(int i) {
                // 왼쪽 자식 노드 인덱스
                int left = i * 2;
                // 오른쪽 자식 노드 인덱스
                int right = i * 2 + 1;
                // 현재 노드의 값을 가장 작은 값으로 가정
                int largest = i;

                // 왼쪽 자식 노드가 존재하고 현재 노드의 값보다 더 작다면 가장 작은 값은 왼쪽 자식 노드로 선언
                if (left <= elements.size() - 1 && elements.get(left) > elements.get(largest)) {
                    largest = left;
                }
                // 오른쪽 자식 노드가 존재하고 현재 노드의 값보다 더 작다면 가장 작은 값은 오른쪽 자식 노드로 선언
                if (right <= elements.size() - 1 && elements.get(right) > elements.get(largest)) {
                    largest = right;
                }
                // 가장 작은 값이 현재 노드가 아니라면 값 스왑 진행
                if (largest != i) {
                    // 가장 작은 노드와 현재 노드의 값 스왑
                    swap(largest, i);
                    // 스왑 이후 계속 진행
                    maxHeapify(largest);
                }
            }

            public int extract() {
                // 루트 값 추출, 최소 힙이므로 가장 작은 값
                int extracted = elements.get(1);

                // 루트에 마지막 엘리먼트 삽입
                elements.set(1, elements.get(elements.size() - 1));
                // 마지막 엘리먼트는 제거
                elements.remove(elements.size() - 1);
                // 루트에 대해 다운힙 연산 수행
                maxHeapify(1);
                // 추출한 기존 루트 값 리턴
                return extracted;
            }
        }

    }

    static class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int num : nums) {
                pq.add(num);
            }

            for (int i = 0; i < k - 1; i++) {
                pq.poll();
            }

            return pq.poll();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 1, 5, 6, 4}, 2,
                        5
                ), Arguments.of(
                        new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4,
                        4
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int k, int expected) {
        int result = new Solution1().findKthLargest(nums, k);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().findKthLargest(nums, k);
        assertThat(result2).isEqualTo(expected);
    }
}
