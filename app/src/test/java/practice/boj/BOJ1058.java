package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BOJ1058 {

	@Test
	void test() {
		int solution = solution1(3, new String[][]{
						{"N", "Y", "Y"},
						{"Y", "N", "Y"},
						{"Y", "Y", "N"},
		});

		Assertions.assertEquals(solution, 2);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/1058
	 * [분류] 그래프, 플루이드 와샬
	 *
	 * @param n                   n명의 친구들
	 * @param connectedFriendInfo [i][j] -> "N" 연결 x, "Y" 연결 o
	 * @return 최대 2-친구 개수 반환
	 */
	public int solution1(int n, String[][] connectedFriendInfo) {
		int result = 0;
		boolean[][] friend = initFriendInfo(n, connectedFriendInfo);
		int[][] board = new int[n][n];

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					if (friend[i][j] || ((friend[i][k] || friend[k][i]) && (friend[j][k] || friend[k][j]))) {
						board[i][j] = 1;
						board[j][i] = 1;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1) count++;
			}
			result = Math.max(result, count);
		}
		return result;
	}

	private boolean[][] initFriendInfo(int n, String[][] connectedFriends) {
		boolean[][] result = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				if (connectedFriends[i][j].equals("Y")) {
					result[i][j] = true;
					result[j][i] = true;
				}
			}
		}
		return result;
	}
}
