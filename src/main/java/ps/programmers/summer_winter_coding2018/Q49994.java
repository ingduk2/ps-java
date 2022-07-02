package ps.programmers.summer_winter_coding2018;

import java.util.*;

/**
 * 방문 길이
 * https://programmers.co.kr/learn/courses/30/lessons/49994
 * 1. 방문이 아니라 한 좌표마다 4방향이 았음. 방향에 따라서 다른 길. (1->2 , 1<-2 같은길인듯)
 * 2. 시작좌표+도착좌표로 표현. 가는방향 + 오는방향이 있기 때문에 도착좌표+시작좌표 같이 처리. (좌표 string 처리)
 * 3. set 으로 중복좌표 처리.
 * 4. 양방향을 넣었으므로 개수/2
 */
public class Q49994 {
    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public int solution(String dirs) {
        Map<String, Pos> dirPosMap = new HashMap<>();
        dirPosMap.put("U", new Pos(-1, 0));
        dirPosMap.put("R", new Pos(0, 1));
        dirPosMap.put("D", new Pos(1, 0));
        dirPosMap.put("L", new Pos(0, -1));

        int x = 5;
        int y = 5;
        Set<String> resultSet = new HashSet<>();

        String[] dir = dirs.split("");
        for (String d : dir) {
            Pos dirPos = dirPosMap.get(d);
            int nx = x + dirPos.getX();
            int ny = y + dirPos.getY();

            if (nx >= 0 && nx < 11 && ny >= 0 && ny < 11) {

                resultSet.add(String.valueOf(x) + y + nx + ny);
                resultSet.add(String.valueOf(nx) + ny + x + y);

                x = nx;
                y = ny;
            }
        }

        return resultSet.size() / 2;
    }

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";// 7
        System.out.println(new Q49994().solution(dirs));
        System.out.println();

        dirs = "LULLLLLLU"; //	7
        System.out.println(new Q49994().solution(dirs));
        System.out.println();

        dirs = "LLLLLLLLLLLLLLL";
        System.out.println(new Q49994().solution(dirs));
        System.out.println();

        dirs = "RRRRRRRRRRRLLLL";
        System.out.println(new Q49994().solution(dirs));
        System.out.println();

        dirs = "LRLRL";
        System.out.println(new Q49994().solution(dirs));
        System.out.println();    }
}
