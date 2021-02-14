package practice.programmers.kakao_2021;

import org.junit.jupiter.api.Test;

public class 합승택시요금 {

	@Test
	void test() {
		int result = solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66},
						{2, 3, 22}, {1, 6, 25}});
		System.out.println(result);
	}

	private int INF = 20_000_005;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/72413
	 * [분류] 플루이드 와샬 알고리즘
	 *
	 * @param n     정점 개수
	 * @param s     출발 지점
	 * @param a     거쳐야 할 정점 a
	 * @param b     거쳐야 할 정점 b
	 * @param fares [0] -> [1] 가중치:[2]
	 * @return 최소 비용을 반환하라.
	 */
	public int solution(int n, int s, int a, int b, int[][] fares) {
		s--;
		a--;
		b--;
		int[][] board = initBoard(n, fares);
		floyd(n, board);
		int answer = Math.min(board[s][a] + board[a][b], board[s][b] + board[b][a]);
		for (int k = 0; k < n; k++) {
			if (k == a || k == b) continue;
			answer = Math.min(answer, board[s][k] + board[k][a] + board[k][b]);
		}
		return answer;
	}

	private int[][] initBoard(int n, int[][] fares) {
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				board[i][j] = INF;
			}
		}

		for (int[] fare : fares) {
			int x = fare[0] - 1;
			int y = fare[1] - 1;
			int f = fare[2];
			board[x][y] = Math.min(f, board[x][y]);
			board[y][x] = Math.min(f, board[y][x]);
		}
		return board;
	}

	private void floyd(int n, int[][] board) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					if (board[i][j] > board[i][k] + board[k][j]) board[i][j] = board[i][k] + board[k][j];
				}
			}
		}
	}
}
