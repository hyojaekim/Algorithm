package practice.programmers.lv3;

import org.junit.jupiter.api.Test;

public class 순위 {

	@Test
	void test() {
		int solution = solution(5, new int[][]{
						{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}
		});

		System.out.println(solution);
	}

	int INF = 10_000_000;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/49191
	 * [분류] 플로이드 와샬
	 * [아쉬운 점]
	 * 1. 플로이드 와샬이라는 아이디어를 떠올리지 못했다. 최단 경로의 거리 뿐만 아니라 연결되지 않는 것도 알 수 있었다.
	 *
	 * @param n
	 * @param results
	 * @return
	 */
	public int solution(int n, int[][] results) {
		int answer = 0;
		int[][] board = new int[n + 1][n + 1];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (i == j) continue;
				board[i][j] = INF;
			}
		}

		for (int[] result : results) {
			board[result[0]][result[1]] = 1;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (board[i][j] > board[i][k] + board[k][j]) {
						board[i][j] = board[i][k] + board[k][j];
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			boolean possible = true;
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				if (board[i][j] == INF && board[j][i] == INF) {
					possible = false;
					break;
				}
			}
			if (possible) answer++;
		}
		return answer;
	}
}
