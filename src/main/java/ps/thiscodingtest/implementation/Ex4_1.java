package ps.thiscodingtest.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상하좌우
 */
public class Ex4_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        List<String> commands = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toList());

        //          L,  R,  U, D
        int[] dx = { 0, 0, -1, 1};
        int[] dy = {-1, 1,  0, 0};

        String[] commandTypes = {"L", "R", "U", "D"};

        int[] ret = {1, 1};

        for (String command : commands) {
            int nx = 0;
            int ny = 0;
            for (int i = 0; i < commandTypes.length; i++) {
                if (command.equals(commandTypes[i])) {
                    nx = ret[0] + dx[i];
                    ny = ret[1] + dy[i];
                }
            }

            if (nx <= n && nx >= 1 && ny <= n && ny >= 1) {
                ret[0] = nx;
                ret[1] = ny;
            }
        }

        System.out.println("ret = " + Arrays.toString(ret));

    }
}
