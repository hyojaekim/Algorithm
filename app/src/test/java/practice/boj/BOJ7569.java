package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ7569 {

	@Test
	void test() {
		int solution = solution(3, 5, new int[][][]{
						{{0, -1, 0, 0, 0}, {-1, -1, 0, 1, 1}, {0, 0, 0, 1, 1}}
		});
		Assertions.assertEquals(solution, -1);
	}

	@Test
	void test2() {
		int solution2 = solution(3, 5, new int[][][]{
						{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
						{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}}
		});
		Assertions.assertEquals(solution2, 4);
	}

	@Test
	void input() {
//		Main main = new Main();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int m = Integer.parseInt(st.nextToken());
//		int n = Integer.parseInt(st.nextToken());
//		int h = Integer.parseInt(st.nextToken());
//		int[][][] totalBoard = new int[h][n][m];
//
//		for (int k = 0; k < h; k++) {
//			for (int i = 0; i < n; i++) {
//				st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < m; j++) {
//					totalBoard[k][i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//		}
//		int solution = main.solution(m, n, totalBoard);
//		System.out.println(solution);
	}

	int[] dk = {1, -1};
	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	/**
	 * [문제] https://www.acmicpc.net/problem/7569
	 * [분류] 그래프, BFS
	 *
	 * 1: 익은 상태
	 * 0: 익지 않은 상태
	 * -1: 토마토가 들어있지 않은 상태
	 *
	 * 이미 모두 익었으면 -> 0
	 * 모두 익지 못하면 -> -1
	 *
	 * @param m 가로 길이
	 * @param n 세로 길이
	 * @param totalBoard 토마토 현황
	 * @return 몇일만에 전부 익을지 반환
	 */
	public int solution(int m, int n, int[][][] totalBoard) {
		int cnt = 0;
		Queue<Pair> q = new LinkedList<>();
		for (int k = 0; k < totalBoard.length; k++) {
			int[][] board = totalBoard[k];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][j] == -1) continue;
					if (board[i][j] == 1) q.offer(new Pair(k, i, j));
					else cnt++;
				}
			}
		}
		if (cnt == 0) return 0;

		return bfs(q, cnt, totalBoard, m, n);
	}

	private int bfs(Queue<Pair> q, int cnt, int[][][] totalBoard, int n, int m) {
		int result = Integer.MIN_VALUE;
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int curDay = totalBoard[cur.k][cur.x][cur.y];
			result = Math.max(curDay, result);

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (totalBoard[cur.k][nx][ny] != 0) continue;
				cnt--;
				totalBoard[cur.k][nx][ny] = curDay + 1;
				q.offer(new Pair(cur.k, nx, ny));
			}

			for (int i = 0; i < 2; i++) {
				int nk = cur.k + dk[i];

				if (nk < 0 || nk >= totalBoard.length) continue;
				if (totalBoard[nk][cur.x][cur.y] != 0) continue;
				cnt--;
				totalBoard[nk][cur.x][cur.y] = curDay + 1;
				q.offer(new Pair(nk, cur.x, cur.y));
			}
		}

		if (cnt > 0) return -1;
		return result - 1;
	}

	class Pair {
		int k;
		int x;
		int y;

		public Pair(int k, int x, int y) {
			this.k = k;
			this.x = x;
			this.y = y;
		}
	}
}
