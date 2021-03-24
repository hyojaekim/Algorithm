package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BOJ17144 {
	@Test
	void test() {
		int solution = solution(7, 8, 1, new int[][]{
						{0, 0, 0, 0, 0, 0, 0, 9},
						{0, 0, 0, 0, 3, 0, 0, 8},
						{-1, 0, 5, 0, 0, 0, 22, 0},
						{-1, 8, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 10, 43, 0},
						{0, 0, 5, 0, 15, 0, 0, 0},
						{0, 0, 40, 0, 0, 0, 20, 0},
		});

		Assertions.assertEquals(solution, 188);
	}

	@Test
	void test2() {
		int solution = solution(7, 8, 2, new int[][]{
						{0, 0, 0, 0, 0, 0, 0, 9},
						{0, 0, 0, 0, 3, 0, 0, 8},
						{-1, 0, 5, 0, 0, 0, 22, 0},
						{-1, 8, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 10, 43, 0},
						{0, 0, 5, 0, 15, 0, 0, 0},
						{0, 0, 40, 0, 0, 0, 20, 0},
		});

		Assertions.assertEquals(solution, 188);
	}

	@Test
	void test3() {
		int solution = solution(7, 8, 3, new int[][]{
						{0, 0, 0, 0, 0, 0, 0, 9},
						{0, 0, 0, 0, 3, 0, 0, 8},
						{-1, 0, 5, 0, 0, 0, 22, 0},
						{-1, 8, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 10, 43, 0},
						{0, 0, 5, 0, 15, 0, 0, 0},
						{0, 0, 40, 0, 0, 0, 20, 0},
		});

		Assertions.assertEquals(solution, 186);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/17144
	 * [분류] 시뮬레이션
	 * [아쉬운 점]
	 * 1. 시계 및 반시계 방향으로 밀어내는 부분에서 실수
	 * 2. 굳이 BFS로 하지 않아도 되는데 더 어렵게 접근했다.
	 *
	 * @param n 세로 길이
	 * @param m 가로 길이
	 * @param time 시간
	 * @param map -1은 공기청정기
	 * return 총 미세먼지 양을 구하라
	 */

	int[][] dx = {{0, -1, 0, 1}, {0, 1, 0, -1}, {0, -1, 0, 1}};
	int[][] dy = {{1, 0, -1, 0}, {1, 0, -1, 0}, {1, 0, -1, 0}};

	public int solution(int n, int m, int time, int[][] map) {
		AirCleaner airCleaner = getAirCleaner(n, m, map);

		while (time > 0) {
			map = spreadDust(n, m, map); //미세먼지 확산
			airCleaner.operate(n, m, map); //공기청정기 작동
			time--;
		}
		return getResult(n, m, map);
	}

	private AirCleaner getAirCleaner(int n, int m, int[][] map) {
		AirCleaner airCleaner = new AirCleaner();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != -1) continue;
				airCleaner.addPosition(new Point(i, j, 0));
			}
		}
		return airCleaner;
	}

	private int[][] spreadDust(int n, int m, int[][] map) {
		int[][] copyMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap[i][j] += map[i][j];
				if (map[i][j] < 5) continue;
				int dust = map[i][j] / 5;

				for (int k = 0; k < 4; k++) {
					int nx = i + dx[2][k];
					int ny = j + dy[2][k];

					if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == -1) continue;
					copyMap[nx][ny] += dust;
					copyMap[i][j] -= dust;
				}
			}
		}
		return copyMap;
	}

	private int getResult(int n, int m, int[][] map) {
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] > 0) result += map[i][j];
			}
		}
		return result;
	}

	class Point {
		int x, y, dust;

		public Point(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}

	class AirCleaner {
		Point[] position;

		public AirCleaner() {
			this.position = new Point[2];
		}

		public void addPosition(Point point) {
			if (position[0] == null) {
				position[0] = point;
			} else if (position[1] == null) {
				position[1] = point;
			}
		}

		public void operate(int n, int m, int[][] map) {
			int before, temp, nx, ny;
			for (int k = 0; k < 2; k++) {
				before = 0;
				nx = this.position[k].x;
				ny = this.position[k].y;

				loop:
				for (int i = 0; i < 4; i++) {
					nx += dx[k][i];
					ny += dy[k][i];
					if (!(nx >= 0 && ny >= 0 && nx < n && ny < m)) break;
					while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
						if (map[nx][ny] == -1) break loop;
						temp = map[nx][ny];
						map[nx][ny] = before;
						before = temp;

						nx += dx[k][i];
						ny += dy[k][i];
					}
					nx -= dx[k][i];
					ny -= dy[k][i];
				}
			}
		}
	}
}
