package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ17836 {

	@Test
	void test() {
		String solution = solution(6, 6, 16, new int[][]{
						{0, 0, 0, 0, 1, 1},
						{0, 0, 0, 0, 0, 2},
						{1, 1, 1, 0, 1, 0},
						{0, 0, 0, 0, 0, 0},
						{0, 1, 1, 1, 1, 1},
						{0, 0, 0, 0, 0, 0}
		});

		Assertions.assertEquals(solution, "10");
	}

	int[] dx = {0, -1, 0, 1};
	int[] dy = {1, 0, -1, 0};

	/**
	 * [문제] https://www.acmicpc.net/problem/17836
	 * [분류] 그래프, BFS
	 * [풀이]
	 * 1. 3차원 배열 -> (x,y) 지점에 그람을 가졌는지 여부 -> 방문했는지 확인
	 * 2. Person -> (x,y)좌표, gram(0은 x, 1은 o), time (소요 시간)
	 * 3. 최대 시간을 넘으면 실패
	 * 4. 도착 지점에 도착 했으면 끝
	 * 5. 방문했으면 x, 그람을 가지지 않았으면 벽 x
	 * 6. 그람을 얻으면 -> 그람을 가진채로 방문체크, 큐에 삽입
	 * 7. else -> 방문 체크 후, 큐에 삽입
	 *
	 * @param n     세로 길이
	 * @param m     가로 길이
	 * @param time  해당 시간안에 도착해야 함
	 * @param board n x m -> 0:빈칸, 1:벽, 2:그람
	 * @return if 공주를 구하지 못함 -> "Fail", else -> 최소 시간
	 */
	public String solution(int n, int m, int time, int[][] board) {
		boolean[][][] visited = new boolean[n][m][2];
		Queue<Person> q = new LinkedList<>();
		q.offer(new Person(0, 0, 0, 0));
		visited[0][0][0] = true;


		while (!q.isEmpty()) {
			Person cur = q.poll();
			int gram = cur.gram;

			if (cur.time > time) continue;
			if (cur.x == n - 1 && cur.y == m - 1) return String.valueOf(cur.time);

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny][gram]) continue;
				if (gram == 0) {
					if (board[nx][ny] == 1) continue;
					else if (board[nx][ny] == 2 && !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.offer(new Person(nx, ny, 1, cur.time + 1));
					}
				}
				visited[nx][ny][gram] = true;
				q.offer(new Person(nx, ny, gram, cur.time + 1));
			}
		}
		return "Fail";
	}

	class Person {
		int x, y, gram, time;

		public Person(int x, int y, int gram, int time) {
			this.x = x;
			this.y = y;
			this.gram = gram;
			this.time = time;
		}
	}
}
