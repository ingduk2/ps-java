package ps.baekjoon.testprepare.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 빗물
 * https://www.acmicpc.net/problem/14719
 *
 * 양끝이 막혀있는 경우만 빗물을 받을 수 있는듯.
 * 1 과 1 사이의 공간 개수를 체크?
 * 중간이 뚫리는 일은 없음. 블록 일자로 세우니
 * 1시작 idx 저장, 가다가 1이 다시나오면 차이를 결과에 더함
 * 여기서 1이 다시나온 위치를 idx 에 저장
 *
 * */
public class Q14719 {
    private static int[][] block;

    private static void setBlockHeight(int height, int idx) {
        int h = block.length - 1;
        for (int i = h; i > h - height; i--) {
            block[i][idx] = 1;
        }
    }

    private static int solution() {
        int ret = 0;

        for (int i = 0; i < block.length; i++) {
            int startIdx = -1;
            for (int j = 0; j < block[0].length; j++) {
                int n = block[i][j];
                if (startIdx == -1 && n == 1) {
                    startIdx = j;
                } else if (n == 1) {
                    ret += j - startIdx - 1;
                    startIdx = j;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        block = new int[h][w];

        String[] heighs = br.readLine().split(" ");
        for (int i = 0; i < heighs.length; i++) {
            int height = Integer.parseInt(heighs[i]);
            setBlockHeight(height, i);
        }

        System.out.println(solution());

    }




}
