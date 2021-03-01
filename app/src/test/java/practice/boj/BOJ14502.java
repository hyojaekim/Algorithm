package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ14502 {

	@Test
	void test() {
		int solution = solution(7, 7, new int[][]{
						{2, 0, 0, 0, 1, 1, 0},
						{0, 0, 1, 0, 1, 2, 0},
						{0, 1, 1, 0, 1, 0, 0},
						{0, 1, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 1, 1},
						{0, 1, 0, 0, 0, 0, 0},
						{0, 1, 0, 0, 0, 0, 0}
		});

		Assertions.assertEquals(solution, 27);
	}

	@Test
	void input() {
//		Main main = new Main();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());
//
//		int[][] board = new int[n][m];
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			for (int j = 0; j < m; j++) {
//				board[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		int solution = main.solution(n, m, board);
//		System.out.println(solution);
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	/**
	 * [문제] https://www.acmicpc.net/problem/14502
	 * [분류] 그래프, BFS, 백트래킹
	 * [풀이]
	 * 0. 안전 영역 List 만들기
	 * 1. 3개 벽 지정하기 (백트래킹)
	 * 2. 바이러스 퍼트리기 (BFS)
	 * -> 바이러스 개수 세기
	 * -> 최소 바이러스 개수 갱신하기
	 * -> 최소보다 크면 탐색 종료
	 * 3. 안전 영역 개수 세기
	 *
	 * @param n 세로 길이
	 * @param m 가로 길이
	 * @param board 0:안전영역지역, 1:벽, 2:바이러스
	 * @return 안전 영역 지역 최대 개수
	 */
	int n, m;
	int[][] board;
	List<Pair> safeArea, virusArea;
	int virusCount;

	public int solution(int n, int m, int[][] board) {
		init(n, m, board);

		backtracking(new LinkedList<>(), new boolean[safeArea.size()], 0);
		return this.safeArea.size() - this.virusCount - 3;
	}

	private void init(int n, int m, int[][] board) {
		this.n = n;
		this.m = m;
		this.board = board;
		this.safeArea = new ArrayList<>();
		this.virusArea = new ArrayList<>();
		this.virusCount = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int area = board[i][j];
				if (area == 0) safeArea.add(new Pair(i, j));
				else if (area == 2) virusArea.add(new Pair(i, j));
			}
		}
	}

	private void backtracking(LinkedList<Pair> tempWalls, boolean[] visited, int idx) {
		if (tempWalls.size() == 3) {
			bfs(tempWalls);
			return;
		}

		for (int i = idx; i < safeArea.size(); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			tempWalls.add(safeArea.get(i));
			backtracking(tempWalls, visited, i + 1);
			tempWalls.removeLast();
			visited[i] = false;
		}
	}

	private void bfs(LinkedList<Pair> tempWalls) {
		Queue<Pair> q = new LinkedList<>();
		int[][] visited = new int[n][m];
		for (Pair tempWall : tempWalls) {
			visited[tempWall.x][tempWall.y] = 1;
		}
		for (Pair area : virusArea) {
			q.offer(area);
			visited[area.x][area.y] = 2;
		}

		int cnt = 0;
		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny] != 0 || board[nx][ny] == 1 || board[nx][ny] == 2) continue;

				visited[nx][ny] = 2;
				q.offer(new Pair(nx, ny));
				if (++cnt >= this.virusCount) return;
			}
		}

		this.virusCount = Math.min(this.virusCount, cnt);
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
