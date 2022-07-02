package ps.thiscodingtest.previous_problems.implementation;

/**
 * 자물쇠와 열쇠
 */
public class Q10 {

    //90 도 시계 회전
    private void turnKey(int[][] key){
        int length = key.length;
        int turnY = key.length - 1;
        int[][] turn = new int[length][length];
        for(int i=0; i< length; i++){
            for (int j = 0; j < length; j++) {
                turn[j][turnY] = key[i][j];
            }
            turnY -= 1;
        }

        System.arraycopy(turn, 0, key, 0, turn.length);
    }

    //lock부분 1 인지 확인
    private boolean check(int[][] extendLock, int lockLen){
        for (int i = lockLen; i < lockLen * 2; i++) {
            for (int j = lockLen; j < lockLen * 2; j++) {
                if (extendLock[i][j] != 1) return false;
            }
        }
        return true;
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        //비교 위해 lock 확장
        int lockLen = lock.length;
        int[][] extendLock = new int[lockLen * 3][lockLen * 3];
        for(int i = lockLen; i < lockLen * 2; i++ ){
            for(int j = lockLen; j < lockLen * 2; j++){
                extendLock[i][j] = lock[i - lockLen][j - lockLen];
            }
        }

        int extendLockLen = extendLock.length;
        int keyLenth = key.length;
        //4번 회전하면서 체크
        for (int t = 0; t < 4; t++) {

            for (int i = 0; i < extendLockLen - keyLenth; i++) {
                for (int j = 0; j < extendLockLen - keyLenth; j++) {
                    for (int k = 0; k < keyLenth; k++) {
                        for (int l = 0; l < keyLenth; l++) {
                            extendLock[i + k][j + l] += key[k][l];
                        }
                    }

                    if (check(extendLock, lockLen)) {
                        return true;
                    }

                    for (int k = 0; k < keyLenth; k++) {
                        for (int l = 0; l < keyLenth; l++) {
                            extendLock[i + k][j + l] -= key[k][l];
                        }
                    }
                }

            }
            turnKey(key);
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0},{1, 0, 1}};
        System.out.println(new Q10().solution(key, lock));

//        key = new int[][]{{0, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}};
//        lock = new int[][]{{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 1}};
//        System.out.println(new Q10().solution(key, lock));
    }
}
