package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설 {

	@Test
	void name() {
		int solution = solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
		Assertions.assertEquals(solution, 900);
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	int[][] board;
	int[][] moneyMap;
	boolean[][] visited;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/67259
	 * [분류] BFS
	 *
	 * @param board 전체 맵 (0:비어있음, 1:벽)
	 * @return 직선도로 100원, 코너 500원으로 최소 비용으로 도착지점까지 건설하기
	 */
	public int solution(int[][] board) {
		int n = board.length;
		this.board = board;
		this.moneyMap = new int[n][n];
		this.visited = new boolean[n][n];

		bfs(n);
		return this.moneyMap[n - 1][n - 1];
	}

	private void bfs(int n) {
		Queue<Road> q = new LinkedList<>();
		q.offer(new Road(0, 0, 0, -1));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Road cur = q.poll();
			int curX = cur.x;
			int curY = cur.y;
			int curCost = cur.cost;
			int curDir = cur.dir;

			for (int dir = 0; dir < 4; dir++) { //0:우, 1:왼, 2:상, 3:하
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				int nCost = curCost;

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue; //범위에 벗어나는지 체크
				if (board[nx][ny] == 1) continue; //벽인지 확인

				nCost += (curDir == -1 || curDir == dir) ? 100 : 600; //코너 여부에 따라 최소 비용 구하기
				if (!visited[nx][ny] || moneyMap[nx][ny] >= nCost) { //방문하지 않았거나, 최소 비용으로 가능하면 Q에 넣는다.
					visited[nx][ny] = true;
					moneyMap[nx][ny] = nCost;
					q.offer(new Road(nx, ny, nCost, dir));
				}
			}
		}
	}

	static class Road {
		int x, y, cost, dir;

		public Road(int x, int y, int cost, int dir) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.dir = dir;
		}
	}
}
