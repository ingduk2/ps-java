package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/number-of-islands/description/
 * - 1로 된 섬이 몇 개인지 구하라
 * - 수직, 수평으로만 연결 된다
 *
 * 1. DFS 로 그래프 탐색
 */
public class Q35_NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new char[][]{
                                new char[]{'1','1','1','1','0'},
                                new char[]{'1','1','0','1','0'},
                                new char[]{'1','1','0','0','0'},
                                new char[]{'0','0','0','0','0'},
                        }, 1),
                Arguments.of(
                        new char[][]{
                                new char[]{'1','1','0','0','0'},
                                new char[]{'1','1','0','0','0'},
                                new char[]{'0','0','1','0','0'},
                                new char[]{'0','0','0','1','1'},
                        }, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(char[][] grid, int expected) {
        int result = new Q35_NumberOfIslands().numIslands(grid);
        assertThat(result).isEqualTo(expected);
    }
}
