package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ5547 {

	@Test
	void test() {
		int solution = solution(8, 4, new int[][]{
						{0, 1, 0, 1, 0, 1, 1, 1},
						{0, 1, 1, 0, 0, 1, 0, 0},
						{1, 0, 1, 0, 1, 1, 1, 1},
						{0, 1, 1, 0, 1, 0, 1, 0}
		});
		Assertions.assertEquals(solution, 64);
	}

	@Test
	void input() {
//		Main main = new Main();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		int w = Integer.parseInt(st.nextToken());
//		int h = Integer.parseInt(st.nextToken());
//
//		int[][] board = new int[h][w];
//		for (int i = 0; i < h; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			for (int j = 0; j < w; j++) {
//				board[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		int solution = main.solution(w, h, board);
//		System.out.println(solution);
	}

	int[] evenX = {1, -1, 0, 0, -1, 1};
	int[] evenY = {0, 0, 1, -1, -1, -1};

	int[] oddX = {1, -1, 0, 0, 1, -1};
	int[] oddY = {0, 0, 1, -1, 1, 1};

	/**
	 * [문제] https://www.acmicpc.net/problem/5547
	 * [분류] 그래프, BFS
	 * [풀이]
	 * 1. 6방향이 홀수, 짝수 여부에 따라 다르다.
	 * 2. 비어있는 칸을 padding
	 * 3. 비어있는 칸을 기준으로 bfs -> 맞닿은 곳이 건물이 있다면 카운트
	 *
	 * @param w 가로 길이
	 * @param h 세로 길이
	 * @param board 0:건물없음, 1:건물있음
	 * @return 테두리의 수를 구하라
	 */
	public int solution(int w, int h, int[][] board) {
		board = padding(w, h, board);
		w += 2;
		h += 2;
		boolean[][] visited = new boolean[h][w];
		return bfs(h, w, 0, 0, board, visited);
	}

	private int bfs(int h, int w, int startX, int startY, int[][] board, boolean[][] visited) {
		int result = 0;
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(startX, startY));
		visited[startX][startY] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int i = 0; i < 6; i++) {
				int nx = cur.x;
				int ny = cur.y;

				if (cur.x % 2 == 0) {
					nx += evenX[i];
					ny += evenY[i];
				} else {
					nx += oddX[i];
					ny += oddY[i];
				}

				if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
				if (board[nx][ny] == 1) {
					result++;
				} else if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new Pair(nx, ny));
				}
			}
		}
		return result;
	}

	private int[][] padding(int w, int h, int[][] board) {
		int[][] result = new int[h + 2][w + 2];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				result[i + 1][j + 1] = board[i][j];
			}
		}
		return result;
	}

	class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
