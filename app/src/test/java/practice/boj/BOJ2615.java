package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2615 {

	public static void main(String[] args) throws IOException {
		int[][] board = new int[19][19];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 19; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(split[j]);
			}
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (board[i][j] == 0) continue;
				if (isPossible(board, i, j)) {
					System.out.println(board[i][j]);
					System.out.println((i + 1) + " " + (j + 1));
					return;
				}
			}
		}
		System.out.println(0);
	}

	static int[] dx = {-1, 0, 1, 1};
	static int[] dy = {1, 1, 1, 0};

	private static boolean isPossible(int[][] board, int curX, int curY) {
		int target = board[curX][curY];
		for (int i = 0; i < 4; i++) {
			if (isBoundary(curX + (dx[i] * 4), curY + (dy[i] * 4))) continue;
			int count = 0, nx = curX, ny = curY;

			for (int k = 0; k < 4; k++) {
				nx += dx[i];
				ny += dy[i];
				if (target == board[nx][ny]) {
					count++;
				}
			}
			if (count == 4) {
				if (!isBoundary(curX - dx[i], curY - dy[i]) && target == board[curX - dx[i]][curY - dy[i]]) continue;
				if (isBoundary(nx + dx[i], ny + dy[i]) || target != board[nx + dx[i]][ny + dy[i]]) return true;
			}
		}
		return false;
	}

	private static boolean isBoundary(int x, int y) {
		return x < 0 || y < 0 || x >= 19 || y >= 19;
	}
}
