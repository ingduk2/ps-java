package ps.thiscodingtest.previous_problems.implementation;

import java.util.*;

/**
 * 뱀
 * input
 * 6
 * 3
 * 3 4
 * 2 5
 * 5 3
 * 3
 * 3 D
 * 15 L
 * 17 D
 * result
 * 9
 *
 * input
 * 10
 * 4
 * 1 2
 * 1 3
 * 1 4
 * 1 5
 * 4
 * 8 D
 * 10 D
 * 11 D
 * 13 L
 * result
 * 21
 *
 * input
 * 10
 * 5
 * 1 5
 * 1 3
 * 1 2
 * 1 6
 * 1 7
 * 4
 * 8 D
 * 10 D
 * 11 D
 * 13 L
 * result
 * 13
 */
public class Q11 {

    private static class Location{
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    private static class Snake{
        private int time;
        private char Direction;

        public Snake(int time, char direction) {
            this.time = time;
            Direction = direction;
        }

        public int getTime() {
            return time;
        }

        public char getDirection() {
             return Direction;
        }

        @Override
        public String toString() {
            return "Snake{" +
                    "time=" + time +
                    ", Direction=" + Direction +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());

        //길 0, 사과 1, 뱀 2
        int[][] board = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            board[x][y] = 1;
        }

        int l = sc.nextInt();
        List<Snake> snakeDirections = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            int time = sc.nextInt();
            String direction = sc.next();

            snakeDirections.add(new Snake(time, direction.charAt(0)));
        }

        sc.nextLine();

        System.out.println(snakeDirections);
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        board[1][1] = 2;
        int resultTime = 1;

        int dx = 1;
        int dy = 1;

        //           동, 남, 서, 북
        int[] dirX = {0, 1, 0, -1};
        int[] dirY = {1, 0,-1,  0};
        int dirNum = 0;
        List<Location> snakes = new ArrayList<>();
        snakes.add(new Location(dx, dy));
        while (true){

            dx += dirX[dirNum];
            dy += dirY[dirNum];

            //벽이나 자기 꼬리에 부딪히면 종료
            if (1 > dx || dx > n || 1 > dy || dy > n || isCrash(dx, dy, board)){
                break;
            }

            //전에 뱀 0 초기화
            for (Location snake : snakes) {
                board[snake.x][snake.y] = 0;
            }


            //다음이 사과이면 이동하지 않고, 다음좌표 추가.
            if (board[dx][dy] == 1){
                snakes.add(new Location(dx, dy));
            }
            //다음이 길이면 뱀 좌표 전부 더하기.
            else if (board[dx][dy] == 0) {
               snakes.remove(0);
               snakes.add(new Location(dx, dy));
            }

            //다음 좌표 뱀 그리기
            for (Location snake : snakes) {
                board[snake.x][snake.y] = 2;
            }


            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <= n ; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(snakes);
            System.out.println(snakeDirections);
            System.out.println();

            //방향 전환
            if (!snakeDirections.isEmpty()) {
                if (resultTime == snakeDirections.get(0).time) {
                    if (snakeDirections.get(0).Direction == 'L') {
                        dirNum--;
                        if (dirNum == -1) dirNum = 4;
                    } else {
                        dirNum++;
                        if (dirNum == 4) dirNum = 0;
                    }
                    snakeDirections.remove(0);
                }
            }

            resultTime++;
        }

        System.out.println(resultTime);
    }

    private static boolean isCrash(int dx, int dy, int[][] board) {
        return board[dx][dy] == 2;
    }
}
