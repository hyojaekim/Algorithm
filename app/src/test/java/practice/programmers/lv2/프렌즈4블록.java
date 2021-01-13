package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 프렌즈4블록 {
	@Test
	void test() {
		int solution = solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
		Assertions.assertEquals(solution, 14);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17679
	 * [분류] 구현
	 *
	 * @param m 높이
	 * @param n 넓이
	 * @param reqBoard 주어진 board
	 * @return 2x2 블록을 지운 개수를 구하라.
	 */
	public int solution(int m, int n, String[] reqBoard) {
		int result = 0;
		String[][] board = initBoard(m, n, reqBoard);

		boolean end = false;
		while (!end) {
			boolean[][] visited = new boolean[m][n];
			end = true;
			for (int x = 0; x < m - 1; x++) {
				for (int y = 0; y < n - 1; y++) {
					String cur = board[x][y];
					if (cur.isEmpty()) continue;
					if (!board[x][y + 1].equals(cur) || !board[x + 1][y].equals(cur) || !board[x + 1][y + 1].equals(cur)) continue;
					visited[x][y] = true; visited[x][y + 1] = true; visited[x + 1][y] = true; visited[x + 1][y + 1] = true;
					end = false;
				}
			}

			for (int y = 0; y < n; y++) {
				Queue<String> q = new LinkedList<>();
				for (int x = 0; x < m; x++) {
					if (visited[x][y]) {
						result++;
						continue;
					}
					q.offer(board[x][y]);
				}

				for (int i = 0; i < m - q.size(); i++) {
					board[i][y] = "";
				}
				int xIdx = m - q.size();
				while (!q.isEmpty()) {
					board[xIdx++][y] = q.poll();
				}
			}
		}

		return result;
	}

	private String[][] initBoard(int m, int n, String[] reqBoard) {
		String[][] result = new String[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = String.valueOf(reqBoard[i].charAt(j));
			}
		}
		return result;
	}
}
