package ps.programmers.kakao.blind2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [3차] 파일명 정렬
 * https://programmers.co.kr/learn/courses/30/lessons/17686
 *
 * 런타임 에러 tail 이 없을 수 있다.
 */
public class Q17686 {

    private static class FileInfo {
        private String originalName;
        private String head;
        private int number;
        private String tail;

        public FileInfo(String originalName, String head, int number, String tail) {
            this.originalName = originalName;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }


        public String getOriginalName() {
            return originalName;
        }

        public String getHead() {
            return head;
        }

        public int getNumber() {
            return number;
        }

        public String getTail() {
            return tail;
        }

        @Override
        public String toString() {
            return "File{" +
                    "head='" + head + '\'' +
                    ", number=" + number +
                    ", tail='" + tail + '\'' +
                    '}';
        }
    }


    private FileInfo getFileInfo(String file) {
        String head = "";
        String number = "";
        String tail = "";

        int idx = 0;
        while (idx < file.length()) {
            char c = file.charAt(idx);

            if (number.equals("") && !Character.isDigit(c)) {
                head += c;
            } else if (number.equals("") && Character.isDigit(c)) {
                number += c;
                idx++;
                while (idx < file.length()) {
                    char ch = file.charAt(idx);
                    if (!Character.isDigit(ch)) {
                        idx--;
                        break;
                    } else {
                        number += ch;
                    }
                    idx++;
                }
            } else if (!number.equals("") && !head.equals("")) {
                tail += c;
            }

            idx++;
        }

        return new FileInfo(file, head, Integer.parseInt(number), tail);
    }

    public String[] solution(String[] files) {
        String[] answer = {};

        //대소문자 상관하지 않음.
        //head, number, tail 로 분리됨.
        //1 head 기준 사전정렬
        //2 number 기준 오름 정렬
        //3 head, number 둘다 같을 경우 유지.

        List<FileInfo> fileInfos = new ArrayList<>();

        for (String file : files) {
            fileInfos.add(getFileInfo(file));
        }

        fileInfos.sort((o1, o2) -> {
            String head1 = o1.getHead().toUpperCase();
            String head2 = o2.getHead().toUpperCase();

            int number1 = o1.getNumber();
            int number2 = o2.getNumber();

            if (head1.compareTo(head2) == 0) {
                return Integer.compare(number1, number2);
            } else {
                return head1.compareTo(head2);
            }
        });

        return fileInfos.stream().map(FileInfo::getOriginalName).toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] files = {"img00012.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG", "foo010"};
        System.out.println(Arrays.toString(new Q17686().solution(files)));
//        출력: ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]

        files = new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString(new Q17686().solution(files)));
//        출력: ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
    }
}
