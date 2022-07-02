package ps.baekjoon.testprepare.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * iSharp
 * https://www.acmicpc.net/problem/3568
 *
 * int& a*[]&, b, c*;
 * arr[0] 자료형.
 * parse 한 후
 * a*[]& 오른쪽거를 끝에서부터 자료형에 붙여주자.
 */
public class Q3568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] sArr = s.substring(0, s.length() - 1).split(" ");
        String dataType = sArr[0];

        for (int i = 1; i < sArr.length; i++) {
            String var = sArr[i].replace(",", "");

            StringBuilder name = new StringBuilder();
            StringBuilder type = new StringBuilder();
            for (int j = 0; j < var.length() ; j++) {
                char c = var.charAt(j);
                if (!Character.isAlphabetic(c)) {
                    if (c == '[') type.insert(0, ']');
                    else if (c == ']') type.insert(0, '[');
                    else type.insert(0, c);
                } else {
                    name.append(c);
                }
            }

            System.out.println(dataType+type + " " + name + ";");
        }
    }


}
