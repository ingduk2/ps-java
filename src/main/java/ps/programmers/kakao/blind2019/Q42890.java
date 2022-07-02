package ps.programmers.kakao.blind2019;

import java.util.*;

/**
 * 후보키
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 *
 * isMinimality 구할때 하나만 포함되면 되는것이 아니라 전체가 포함되는지 체크.
 * isUniqueniss 문자 붙이는 걸로는 a aa , aa a 구분이 안됨.
 */
public class Q42890 {

    private static class Combination {
        private int n;
        private int r;
        private int[] now; // 현재 조합
        private ArrayList<ArrayList<Integer>> result; // 모든 조합

        public ArrayList<ArrayList<Integer>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<>();
        }

        public void combination(int[] arr, int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(arr[now[i]]);
                }
                result.add(temp);
                return;
            }
            if (target == n) return;
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }
    }

    private List<List<Integer>> candidateKeyList = new ArrayList<>();
    private int x;
    private int y;

    public int solution(String[][] relation) {
        x = relation.length;
        y = relation[0].length;

        int[] idxArr = new int[y];
        for (int i = 0; i < y; i++) {
            idxArr[i] = i;
        }

        for (int i = 1; i <= y; i++) {
            Combination comb = new Combination(y, i);
            comb.combination(idxArr, 0, 0, 0);
            ArrayList<ArrayList<Integer>> result = comb.getResult();
            for (ArrayList<Integer> keys : result) {
                isUniqueniss(keys, relation);
            }
        }
        return candidateKeyList.size();
    }

    private boolean isMinimality(List<Integer> keys) {
        for (List<Integer> candidateKeys : candidateKeyList) {
            int cnt = candidateKeys.size();
            int equalCnt = 0;
            for (Integer ck : candidateKeys) {
                for (Integer key : keys) {
                    if (ck == key) {
                        equalCnt++;
                    }
                }
            }
            if (cnt == equalCnt) {
                return false;
            }
        }
        return true;
    }

    private boolean isUniqueniss(ArrayList<Integer> keys, String[][] relation) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < x; i++) {
            String tempKey = "";
            for (Integer integer : keys) {
                tempKey = tempKey + " " + relation[i][integer];
            }
            set.add(tempKey);
        }

        if (set.size() == x && isMinimality(keys)) {
            candidateKeyList.add(keys);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};

        System.out.println(new Q42890().solution(relation));

        relation = new String[][]{{"a", "aa"}, {"aa", "a"}, {"a", "a"}};
        System.out.println(new Q42890().solution(relation));

        relation = new String[][]{{"a", "b", "c"},
                {"1", "b", "c"},
                {"a", "b", "4"},
                {"a", "5", "c"}};
        System.out.println(new Q42890().solution(relation));

        relation = new String[][]{
                {"a", "1", "4"},
                {"2", "1", "5"},
                {"a", "2", "4"}};
        System.out.println(new Q42890().solution(relation));
    }
}
