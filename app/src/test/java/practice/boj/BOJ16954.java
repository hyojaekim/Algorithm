package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954 {

	@Test
	void test2() {
		String[] board = new String[]{
						"........",
						"........",
						"........",
						"........",
						"........",
						".#######",
						"#.......",
						"........"
		};

		int solution = solution(board);
		Assertions.assertEquals(solution, 1);
	}

	@Test
	void test() {
		String[] board = new String[]{
						"........",
						"........",
						"........",
						"........",
						"........",
						".#......",
						"#.......",
						".#......"
		};

		int solution = solution(board);
		Assertions.assertEquals(solution, 0);
	}
	int[] dx = {0, -1, 0, 1, 1, -1, 1, -1};
	int[] dy = {1, 0, -1, 0, 1, -1, -1, 1};

	public int solution(String[] board) {
		boolean[][][] visited = init(board);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(7, 0, 0));

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.time < 8 && visited[cur.x][cur.y][cur.time]) continue;
			if (cur.x == 0 && cur.y == 7) return 1;

			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
				if (visited[nx][ny][cur.time] || cur.time + 1 >= 8 || visited[nx][ny][cur.time + 1]) continue;
				q.offer(new Point(nx, ny, cur.time + 1));
			}
			q.offer(new Point(cur.x, cur.y, cur.time + 1));
		}
		return 0;
	}

	private boolean[][][] init(String[] board) {
		boolean[][][] result = new boolean[8][8][8];
		char[][] convertedBoard = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String[] split = board[i].split("");
			for (int j = 0; j < 8; j++) {
				convertedBoard[i][j] = split[j].charAt(0);
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (convertedBoard[i][j] == '.') continue;
				int time = 0;
				for (int k = i; k < 8; k++) {
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
