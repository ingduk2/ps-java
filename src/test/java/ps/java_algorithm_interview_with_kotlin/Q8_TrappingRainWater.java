package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * 1. 투 포인터
 * - 막대는 장벽 역할, 낮은 쪽은 그만큼 채워지게 된다.
 * - 각 왼쪽, 오른쪽의 max 보다 낮으면 그 차이만큼 찬다
 *
 * 2. 스택
 * - 현재 높이가 stack 의 peek 높이 보다 클 경우 (변곡이 생기면)
 * - 이전까지의 물의 개수를 센다 (거리 * 높이)
 * - 거리 = 스택의 마지막 위치
 * - 높이 = 현재 높이 또는 스택의 마지막 위치(peek) 높이의 최소값 - 방금 꺼낸(pop) 높이
 */
public class Q8_TrappingRainWater {
    static class TwoPointer {
        public int trap(int[] height) {
            int result = 0;
            int left = 0;
            int right = height.length - 1;
            int leftMax = height[left];
            int rightMax = height[right];

            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);

                if (leftMax <= rightMax) {
                    result += leftMax - height[left];
                    left++;
                } else {
                    result += rightMax - height[right];
                    right--;
                }
            }

            return result;
        }
    }

    static class StackPush {
        public int trap(int[] height) {
            Stack<Integer> stack = new Stack<>();
            int result = 0;

            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (stack.isEmpty()) break;

                    int distance = i - stack.peek() - 1;
                    int waters = Math.min(height[i], height[stack.peek()]) - height[top];
                    result += distance * waters;
                }
                stack.push(i);
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6),
                Arguments.of(new int[]{4,2,0,3,2,5}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] height, int expected) {
        printTrap(height);

        // TwoPointer
        int result = new TwoPointer().trap(height);
        assertThat(result).isEqualTo(expected);

        // StackPush
        int result2 = new StackPush().trap(height);
        assertThat(result2).isEqualTo(expected);
    }

    private void printTrap(int[] height) {
        int maxHeight = Arrays.stream(height).max().getAsInt();
        int[][] trap = new int[maxHeight][height.length];

        for (int i = 0; i < height.length; i++) {
            if (height[i] == 0) continue;

            for (int j = 0; j < height[i]; j++) {
                trap[Math.abs(j - (maxHeight - 1))][i] = 1;
            }
        }

        for (int i = 0; i < trap.length; i++) {
            for (int j = 0; j < trap[i].length; j++) {
                System.out.print(trap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
