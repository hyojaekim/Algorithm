package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ16234 {

	@Test
	void test() {
		int solution = solution(2, 20, 50, new int[][]{
						{50, 30},
						{20, 40}
		});

		Assertions.assertEquals(solution, 1);
	}

	@Test
	void test2() {
		int solution = solution(2, 40, 50, new int[][]{
						{50, 30},
						{20, 40}
		});

		Assertions.assertEquals(solution, 0);
	}

	@Test
	void test3() {
		int solution = solution(2, 20, 50, new int[][]{
						{50, 30},
						{20, 40}
		});

		Assertions.assertEquals(solution, 1);
	}

	@Test
	void test4() {
		int solution = solution(3, 5, 10, new int[][]{
						{10, 15, 20},
						{20, 30, 25},
						{40, 22, 10}
		});

		Assertions.assertEquals(solution, 2);
	}

	@Test
	void test5() {
		int solution = solution(4, 10, 50, new int[][]{
						{10, 100, 20, 90},
						{80, 100, 60, 70},
						{70, 20, 30, 40},
						{50, 20, 100, 10},
		});

		Assertions.assertEquals(solution, 3);
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	/**
	 * [문제] https://www.acmicpc.net/problem/16234
	 * [분류] 그래프, BFS, 시뮬레이션
	 * [풀이]
	 * 1. 이동이 가능할 때까지 계속 순회한다.
	 * 1-1 2중 반복문을 돌면서 -> 방문하지 않은 곳 -> BFS 탐색
	 * 1-2. 상하좌우로 가능한 곳 -> 저장 -> 모두 더하기 -> board를 sum / 개수로 갱신하기
	 * 2. 불가능하면 while 탈출
	 *
	 * @param N N*N 배열
	 * @param L 최소 인구 수
	 * @param R 최대 인구 수
	 * @param board 각 지점마다 인구수
	 * @return 인구 이동 횟수 반환
	 */
	public int solution(int N, int L, int R, int[][] board) {
		int cnt = 0;
		while (true) {
			boolean[][] visited = new boolean[N][N];
			boolean possible = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue;
					if (bfs(board, L, R, i, j, visited)) possible = true;
				}
			}
			if (!possible) return cnt;
			else cnt++;
		}
	}

	private boolean bfs(int[][] board, int L, int R, int startX, int startY, boolean[][] visited) {
		int sum = board[startX][startY];
		Queue<Pair> q = new LinkedList<>();
		Queue<Pair> tempQ = new LinkedList<>();

		q.offer(new Pair(startX, startY));
		tempQ.offer(new Pair(startX, startY));
		visited[startX][startY] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			for (int k = 0; k < 4; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];

				if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length || visited[nx][ny]) continue;
				int num = Math.abs(board[cur.x][cur.y] - board[nx][ny]);
				if (num < L || num > R) continue;
				Pair pair = new Pair(nx, ny);
				q.offer(pair);
				tempQ.offer(pair);
				sum += board[nx][ny];
				visited[nx][ny] = true;
			}
		}

		if (tempQ.size() == 1) return false;
		else {
			int result = sum / tempQ.size();
			for (Pair pair : tempQ) {
				board[pair.x][pair.y] = result;
			}
			return true;
		}
	}

	private class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
