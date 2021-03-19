package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ16932 {
	@Test
	void test() {
		int solution = solution(3, 3, new int[][]{
						{0, 1, 1},
						{0, 0, 1},
						{0, 1, 0},
		});

		Assertions.assertEquals(solution, 5);
	}

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};
	List<Set<Point>> areas;
	Map<Point, Integer> position;

	/**
	 * [문제] https://www.acmicpc.net/problem/16932
	 * [분류] 그래프, BFS, FloodFill
	 * [아쉬운 점]
	 * 1. 문제를 잘못 읽어서, 1칸을 채워넣는 것을 보지 못했다.
	 * 2. 모든 area를 돌면서 확인하느라 시간 초과가 발생했다.
	 *
	 * @param n 세로 길이
	 * @param m 가로 길이
	 * @param map 0:빈곳, 1:모양
	 * @return 빈곳 1칸을 채워넣었을때, 만들 수 있는 모양의 최대 넓이
	 */
	public int solution(int n, int m, int[][] map) {
		int result = 0;
		this.areas = new ArrayList<>();
		this.position = new HashMap<>();
		boolean[][] visited = new boolean[n][m];

		floodFill(n, m, map, visited);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) continue;

				Set<Integer> areaIndex = new HashSet<>();
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
					Point point = new Point(nx, ny);
					if (position.containsKey(point))
						areaIndex.add(position.get(point));
				}

				int area = 1;
				for (Integer idx : areaIndex) {
					area += areas.get(idx).size();
				}
				result = Math.max(result, area);
			}
		}
		return result;
	}

	private void floodFill(int n, int m, int[][] map, boolean[][] visited) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) continue;
				if (visited[i][j]) continue;
				getArea(n, m, map, visited, i, j);
			}
		}
	}

	private void getArea(int n, int m, int[][] map, boolean[][] visited, int startX, int startY) {
		Set<Point> set = new HashSet<>();
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(startX, startY));
		visited[startX][startY] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			set.add(cur);
			position.put(cur, areas.size());

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (map[nx][ny] == 0 || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.offer(new Point(nx, ny));
			}
		}
		areas.add(set);
	}

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
