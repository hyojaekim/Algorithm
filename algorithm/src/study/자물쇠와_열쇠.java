package study;

import java.util.Arrays;

public class 자물쇠와_열쇠 {
    
    int holeCnt;
    public boolean solution(int[][] key, int[][] lock) {
        this.holeCnt = 0;
        lock = padding(lock);
        if (holeCnt == 0) return true;
        for (int i = 0; i < 4; i++) {
            if (isCollect(key, lock)) return true;
            key = rotate(key);
        }
        return false;
    }

    private boolean isCollect(int[][] key, int[][] lock) {
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                boolean isCollect = true;
                int cnt = 0;
                loop:
                for (int k = 0; k < key.length; k++) {
                    for (int l = 0; l < key.length; l++) {
                        if (i + k >= lock.length || j + l >= lock.length) continue;
                        if (lock[i + k][j + l] == -1) continue;
                        if (lock[i + k][j + l] != key[k][l]) {
                            isCollect = false;
                            break loop;
                        }
                        if (lock[i + k][j + l] == 1 && key[k][l] == 1) cnt++;
                    }
                }
                if (isCollect && cnt == this.holeCnt) return true;
            }
        }
        return false;
    }

    private int[][] padding(int[][] lock) {
        int n = lock.length;
        int[][] result = new int[n * 3][n * 3];
        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lock[i][j] == 0) this.holeCnt++;
                result[i + n][j + n] = lock[i][j]^1;
            }
        }
        return result;
    }

    private int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];
        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n-1-j][i];
            }
        }
        return rotate;
    }
}
