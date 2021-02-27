package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16918 {

	@Test
	void test() {
		solution(6, 7, 5, new int[][]{
						{-2, -2, -2, -2, -2, -2, -2},
						{-2, -2, -2, 0, -2, -2, -2},
						{-2, -2, -2, -2, 0, -2, -2},
						{-2, -2, -2, -2, -2, -2, -2},
						{0, 0, -2, -2, -2, -2, -2},
						{0, 0, -2, -2, -2, -2, -2}
		});
	}

	@Test
	void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] board = new int[r][c];
		for (int i = 0; i < r; i++) {
			String[] split = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				board[i][j] = split[j].equals(".") ? -2 : 0;
			}
		}
		solution(r, c, n, board);
	}

	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	/**
	 * [문제] https://www.acmicpc.net/problem/16918
	 * [분류] 그래프, BFS
	 * [아쉬운점]
	 * 1. 출력을 'O'로 해야하는데 '0'으로 출력하는 바람에 다른 곳에 문제가 있는 줄 알았다..
	 * 2. 문제를 잘보고 풀자. -> 빠르게 풀었지만, 엉뚱한 곳에서 실수했다.
	 *
	 * @param r 가로 길이
	 * @param c 세로 길이
	 * @param n 초
	 * @param board 'O':폭탄있음, '.':폭탄없음
	 */
	public void solution(int r, int c, int n, int[][] board) {
		boolean installable = false;
		while (n > 0) {
			Queue<Pair> q = new LinkedList<>();
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (installable && board[i][j] == -2) board[i][j] = -1;
					if (board[i][j] == -2) continue;
					board[i][j]++;
					if (board[i][j] >= 3) q.offer(new Pair(i, j));
				}
			}

			while (!q.isEmpty()) {
				Pair cur = q.poll();
				board[cur.x][cur.y] = -2;

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
					board[nx][ny] = -2;
				}
			}

			n--;
			installable = !installable;
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(board[i][j] == -2 ? "." : "O");
			}
			System.out.println();
		}
	}

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
