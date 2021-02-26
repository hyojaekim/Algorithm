package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ2667 {

	@Test
	void test() {
		int[][] board = new int[][]{
						{0,1,1,0,1,0,0},
						{0,1,1,0,1,0,1},
						{1,1,1,0,1,0,1},
						{0,0,0,0,1,1,1},
						{0,1,0,0,0,0,0},
						{0,1,1,1,1,1,0},
						{0,1,1,1,0,0,0}
		};

		List<Integer> solution = solution(7, board);
		Assertions.assertEquals(solution, Arrays.asList(3, 7, 8, 9));
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	/**
	 * [문제] https://www.acmicpc.net/problem/2667
	 * [분류] 그래프, BFS, FloodFill
	 *
	 * @param n 가로, 세로 길이
	 * @param board 0과 1로 이루어진 형태 (n * n)
	 * @return 단지수와 단지 안에 있는 집의 개수를 세서 정렬하라
	 */
	public List<Integer> solution(int n, int[][] board) {
		int count = 0;
		boolean[][] visited = new boolean[n][n];
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || board[i][j] == 0) continue;
				count++;
				int cnt = bfs(i, j, visited, board, n);
				result.add(cnt);
			}
		}
		Collections.sort(result);
		List<Integer> answer = new ArrayList<>();
		answer.add(count);
		answer.addAll(result);
		return answer;
	}

	private int bfs(int startX, int startY, boolean[][] visited, int[][] board, int n) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(startX, startY));
		visited[startX][startY] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if (visited[nx][ny] || board[nx][ny] == 0) continue;
				visited[nx][ny] = true;
				cnt++;
				q.offer(new Pair(nx, ny));
			}
		}
		return cnt;
	}

	class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
