package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ2636 {

	@Test
	void test() {
		int[] solution = solution(13, 12, new int[][]{
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
						{0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
						{0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
						{0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0},
						{0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0},
						{0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
						{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
						{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
						{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
						{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		});

		Assertions.assertArrayEquals(solution, new int[]{3, 5});
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	int cheeseCount, time;

	/**
	 * [문제] https://www.acmicpc.net/problem/2636
	 * [분류] 그래프, BFS
	 * [풀이]
	 * 1. 빈칸을 기준으로 BFS를 수행한다.
	 * 2. 상하좌우에 치즈가 위치한다면 해당 위치를 저장한다. (테두리에 있는 치즈를 의미한다)
	 * 3. if 전체 치즈 개수에서 현재 저장된 치즈 개수를 빼서 0이 나오면 -> break
	 * 4. else -> 현재 저장된 치즈 위치에 0으로 채워준다.
	 *
	 * @param n 세로 길이
	 * @param m 가로 길이
	 * @param board 0:구멍, 1:치즈
	 * @return 치즈가 모두 녹는데 걸리는 시간 & 모두 녹기 전 치즈 개수
	 */
	public int[] solution(int n, int m, int[][] board) {
		this.cheeseCount = getCheeseCount(n, m, board);
		if (cheeseCount == 0) return new int[]{0, 0};
		this.time = 1;

		while (bfs(n, m, board)) {
			time++;
		}
		return new int[]{time, cheeseCount};
	}

	private boolean bfs(int n, int m, int[][] board) {
		boolean[][] visited = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		Queue<Point> cheeseQ = new LinkedList<>();
		q.offer(new Point(0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny]) continue;

				visited[nx][ny] = true;
				Point point = new Point(nx, ny);
				if (board[nx][ny] == 1) {
					cheeseQ.offer(point);
				} else {
					q.offer(point);
				}
			}
		}

		if (this.cheeseCount - cheeseQ.size() == 0) {
			return false;
		} else {
			this.cheeseCount -= cheeseQ.size();
			for (Point cheese : cheeseQ) {
				board[cheese.x][cheese.y] = 0;
			}
			return true;
		}
	}

	private int getCheeseCount(int n, int m, int[][] board) {
		int cheeseCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 1) cheeseCount++;
			}
		}
		return cheeseCount;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
