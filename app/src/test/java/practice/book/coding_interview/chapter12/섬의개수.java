package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 섬의개수 {

	@Test
	void test() {
		int result = solutionWithBfs(new int[][]{
						{1, 1, 1, 1, 0},
						{1, 1, 0, 1, 0},
						{1, 1, 0, 0, 0},
						{0, 0, 0, 0, 0}
		});

		Assertions.assertEquals(result, 1);
	}

	int[][] map;
	boolean[][] visited;
	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	/**
	 * 사용한 알고리즘 : BFS
	 * 1. map을 순회
	 * 2. 육지인지 확인한다.
	 * 3. 카운트 한다.
	 * 4. bfs로 돌며 방문 여부를 체크한다.
	 * @param map : 0(물) 1(육지)로 이루어진 2차원 배열
	 * @return 연결되어 있는 1(육지)의 덩어리 개수
	 */
	public int solutionWithBfs(int[][] map) {
		int count = 0;
		this.map = map;
		int n = map.length;
		int m = map[0].length;
		this.visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i, j));
					this.visited[i][j] = true;
					bfs(q, n, m);
					count++;
				}
			}
		}
		return count;
	}

	private void bfs(Queue<Point> q, int n, int m) {
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int x = cur.x + dx[i];
				int y = cur.y + dy[i];
				if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) continue;
				if (map[x][y] == 0) continue;
				visited[x][y] = true;
				q.add(new Point(x, y));
			}
		}
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
