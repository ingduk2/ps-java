package ps.thiscodingtest.previous_problems.dfs_bfs;

/**
 * 괄호 변환
 */
public class Q18 {

        private boolean isCorrectStr(String p) {
            int cnt = 0;

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '(') cnt++;
                if (p.charAt(i) == ')') cnt--;
                if (cnt < 0) break;
            }

            return cnt == 0;
        }

        private int getBalensedSeperatePoint(String p) {
            int cnt1 = 0;
            int cnt2 = 0;

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '(') {
                    cnt1 ++;
                } else {
                    cnt2 ++;
                }

                if (cnt1 == cnt2) {
                    return i;
                }
            }
            return p.length() - 1;
        }

        private String recursive(String p) {
            String ret = "";
            //1
            if (p.equals("")) {
                return p;
            }

            //2
            int seperatePoint = getBalensedSeperatePoint(p);
            String u = p.substring(0, seperatePoint + 1);
            String v = p.substring(seperatePoint + 1);

            //3
            if (isCorrectStr(u)) {
                //3 - 1
                ret += u + recursive(v);
            } else {
                //4
                String temp = "(";
                temp += recursive(v);
                temp += ")";

                u = u.substring(1, u.length() - 1);
                //change
                StringBuilder sb = new StringBuilder(u);
                for (int i = 0; i < u.length(); i++) {
                    if (u.charAt(i) == '(') {
                        sb.setCharAt(i, ')');
                    } else {
                        sb.setCharAt(i, '(');
                    }
                }

                temp += sb.toString();
                ret += temp;
            }
            return ret;
        }

        public String solution(String p) {
            String answer = "";

            // check correct
            if (isCorrectStr(p)) return p;

            return recursive(p);
        }

    public static void main(String[] args) {
        System.out.println(new Q18().solution("(()())()"));
        System.out.println("========");
        System.out.println(new Q18().solution(")("));
        System.out.println("========");
        System.out.println(new Q18().solution("()))((()"));
        System.out.println("========");
    }
}
