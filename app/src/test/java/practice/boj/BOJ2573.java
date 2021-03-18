package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ2573 {

	@Test
	void test() {
		int solution = solution(5, 7, new int[][]{
						{0, 0, 0, 0, 0, 0, 0},
						{0, 2, 4, 5, 3, 0, 0},
						{0, 3, 0, 2, 5, 2, 0},
						{0, 7, 6, 2, 4, 0, 0},
						{0, 0, 0, 0, 0, 0, 0},
		});

		Assertions.assertEquals(solution, 2);
	}

	int n;
	int m;
	int[][] map;
	boolean[][] visited;
	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	/**
	 * [문제] https://www.acmicpc.net/problem/2573
	 * [분류] 그래프, BFS
	 *
	 * @param n 가로 길이
	 * @param m 세로 길이
	 * @param map 0:물, num:빙산
	 * @return 빙산이 2 덩어리 이상으로 나눠질 때, 몇년이 걸리는가?
	 */
	public int solution(int n, int m, int[][] map) {
		init(n, m, map);
		int result = 0;

		while (true) {
			this.visited = new boolean[n][m];
			int icebergCount = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] != -1 && !visited[i][j]) {
						bfs(i, j);
						icebergCount++;
					}
				}
			}
			if (icebergCount == 0) return 0;
			else if (icebergCount > 1) return result;
			else result++;
		}
	}

	private void bfs(int startX, int startY) {
		Queue<Point> q = new LinkedList<>();
		Queue<Point> vanishingIcebergs = new LinkedList<>();
		q.offer(new Point(startX, startY));
		visited[startX][startY] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int cnt = 0;

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
				if (map[nx][ny] == -1)
					cnt++;
				else {
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}

			map[cur.x][cur.y] = Math.max(map[cur.x][cur.y] - cnt, 0);
			if (map[cur.x][cur.y] == 0)
				vanishingIcebergs.add(new Point(cur.x, cur.y));
		}

		for (Point vanishingIceberg : vanishingIcebergs)
			map[vanishingIceberg.x][vanishingIceberg.y] = -1;
	}

	private void init(int n, int m, int[][] map) {
		this.map = map;
		this.n = n;
		this.m = m;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) map[i][j] = -1;
			}
		}
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
