package ps.programmers.kakao.intern_2020;


import java.util.*;

/**
 * 키패드 누르기
 * https://programmers.co.kr/learn/courses/30/lessons/67256
 * 그냥 좌표 박아넣고 하는게 더 편할수도..
 * 그러면 현재좌표 계산시 그냥 계산만 하면됨.
 */
public class Q67256 {

    private char[][] keypad = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'},
            {'*', '0', '#'}
    };

    private char[][] current = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {'L', ' ', 'R'}
    };

    private String selectHand(String hand, int num) {
        int leftLength = 0;
        int rightLength = 0;

        int numX = 0;
        int numY = 0;
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[0].length; j++) {
                if (keypad[i][j] == Character.forDigit(num, 10)) {
                    numX = i;
                    numY = j;
                    break;
                }
            }
        }

        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[0].length; j++) {
                if (current[i][j] == 'L') {
                    leftLength = Math.abs(numX - i) + Math.abs(numY - j);
                } else if(current[i][j] == 'R'){
                    rightLength = Math.abs(numX - i) + Math.abs(numY - j);
                }
            }
        }

        String h;
        if (leftLength < rightLength) {
            h = "L";
        } else if (leftLength > rightLength) {
            h = "R";
        } else {
            h = hand;
        }
        return h;
    }

    private void moveHand(int num, String h) {
        char hand = h.charAt(0);
        int x = 0;
        int y = 0;

        //get keypad x, y
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[0].length; j++) {
                if (keypad[i][j] == Character.forDigit(num, 10)) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        //set handCurrent init
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[0].length; j++) {
                if (current[i][j] == hand) {
                    current[i][j] = ' ';
                }
            }
        }

        //set new handCurrent
        current[x][y] = hand;
    }

    public String solution(int[] numbers, String hand) {
        if (hand.equals("right")) {
            hand = "R";
        } else {
            hand = "L";
        }

        StringBuilder sb = new StringBuilder();

        List<Integer> rightList = Arrays.asList(3, 6, 9);
        List<Integer> leftList = Arrays.asList(1, 4, 7);

        for (int num : numbers) {
            String h;
            if (leftList.contains(num)) {
                h = "L";
            } else if (rightList.contains(num)) {
                h = "R";
            } else {
                //2,5,8,0
                h = selectHand(hand, num);
            }

            sb.append(h);
            moveHand(num, h);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right"; //	"LRLLLRLLRRL"
        System.out.println(new Q67256().solution(numbers, hand));

        numbers = new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        hand = "left"; //	"LRLLRRLLLRR"
        System.out.println(new Q67256().solution(numbers, hand));

        numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        hand = "right"; //	"LLRLLRLLRL"
        System.out.println(new Q67256().solution(numbers, hand));
    }
}
