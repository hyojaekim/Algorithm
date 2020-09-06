package study;

import java.util.HashSet;
import java.util.Set;

public class 블록_이동하기 {

    public int solution(int[][] board) {
        int answer = 0;
        while (true) {
            boolean isFind = false;
            loop:
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (isPossible(board, i, j, i + 3, j + 2)) {
                        remove(board, i, j, i + 3, j + 2);
                        isFind = true;
                        break loop;
                    } else if (isPossible(board, i, j, i + 2, j + 3)) {
                        remove(board, i, j, i + 2, j + 3);
                        isFind = true;
                        break loop;
                    }
                }
            }
            if (isFind) answer++;
            else break;
        }
        return answer;
    }

    private boolean isPossible(int[][] board, int startX, int startY, int maxHeight, int maxWidth) {
        if (maxHeight <= board.length && maxWidth <= board.length) {
            int countOfZero = 0;
            Set<Integer> numbers = new HashSet<>();
            for (int i = startX; i < maxHeight; i++) {
                for (int j = startY; j < maxWidth; j++) {
                    if (board[i][j] == 0) {
                        countOfZero++;
                        if (countOfZero >= 3 || !canPut(board, i, j)) return false;
                    } else {
                        numbers.add(board[i][j]);
                        if (numbers.size() == 2) return false;
                    }
                }
            }
            return countOfZero == 2 && numbers.size() == 1;
        }
        return false;
    }

    private boolean canPut(int[][] board, int startX, int j) {
        for (int i = startX; i >= 0; i--) {
            if (board[i][j] != 0) return false;
        }
        return true;
    }

    private void remove(int[][] board, int startX, int startY, int maxHeight, int maxWidth) {
        for (int i = startX; i < maxHeight; i++) {
            for (int j = startY; j < maxWidth; j++) {
                board[i][j] = 0;
            }
        }
    }
}
