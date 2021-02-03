package practice;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());

        int[][] board = new int[m][3];
        for (int i = 0; i < m; i++) {
            String[] line = sc.nextLine().split(" ");
            board[i][0] = Integer.parseInt(line[0]);
            board[i][1] = Integer.parseInt(line[1]);
            board[i][2] = Integer.parseInt(line[2]);
        }

        Main main = new Main();
        main.solution(n, m, board);
    }


    int INF = 10000005;

    public void solution(int n, int m, int[][] board) {
        int[][] convertedBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                convertedBoard[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            int x = board[i][0] - 1;
            int y = board[i][1] - 1;
            int c = board[i][2];
            convertedBoard[x][y] = Math.min(convertedBoard[x][y], c);
        }

        floyd(convertedBoard, n, m);
        for (int[] arr : convertedBoard) {
            for (int a : arr) {
                if (a == INF) System.out.print(0 + " ");
                else System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    private void floyd(int[][] board, int n, int m) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][k] + board[k][j] < board[i][j]) board[i][j] = board[i][k] + board[k][j];
                }
            }
        }
    }
}