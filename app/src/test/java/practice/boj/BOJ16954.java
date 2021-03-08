package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954 {

	@Test
	void test5() {
		char[][] board = new char[][]{
						{'.','.','.','.','.','.','.','.'},
						{'.','.','.','.','.','.','.','.'},
						{'.','.','.','.','.','.','.','.'},
						{'.','.','.','.','.','.','.','.'},
						{'#','.','.','.','.','.','.','.'},
						{'.','#','#','#','#','#','#','#'},
						{'#','.','.','.','.','.','.','.'},
						{'.','.','.','.','.','.','.','.'}
		};

		int solution = solution(board);
		Assertions.assertEquals(solution, 0);
	}

	int[] dx = {0, -1, 0, 1, 1, -1, 1, -1};
	int[] dy = {1, 0, -1, 0, 1, -1, -1, 1};

	/**
	 * [문제] https://www.acmicpc.net/problem/16954
	 * [분류] 그래프, BFS
	 * [풀이]
	 * 1. wall[x][y][k] -> (x, y) 지점에 k초에 도착한 벽이 있는지 여부
	 * 2. 움직이기 (상하좌우, 대각선, 멈추기)
	 * 2-1. 멈추기 -> 8초 이후에는 멈추기 불가 (벽이 없음)
	 * 2-2. 상하좌우, 대각선 -> 8초 이하면서, 같은 시간 or 현재 시간 + 1에 벽이 없어야 함
	 *
	 * @param board '.' 이동가능, '#' 벽
	 * @return 이동 가능하면 1, 불가능하면 0
	 */
	public int solution(char[][] board) {
		int max = 8;
		boolean[][][] wall = init(board, max);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(7, 0, 0));

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.time < max && wall[cur.x][cur.y][cur.time]) continue;
			if (cur.x == 0 && cur.y == 7) return 1;

			for (int i = 0; i < max; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= max || ny >= max) continue;
				if (cur.time < max && (wall[nx][ny][cur.time] || cur.time + 1 >= max || wall[nx][ny][cur.time + 1])) continue;
				q.offer(new Point(nx, ny, cur.time + 1));
			}
			if (cur.time < max) q.offer(new Point(cur.x, cur.y, cur.time + 1));
		}
		return 0;
	}

	private boolean[][][] init(char[][] board, int max) {
		boolean[][][] result = new boolean[max][max][max];

		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				if (board[i][j] == '.') continue;
				int time = 0;
				for (int k = i; k < max; k++) {
					result[k][j][time] = true;
					time++;
				}
			}
		}
		return result;
	}

	class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
