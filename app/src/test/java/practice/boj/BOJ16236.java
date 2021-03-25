package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ16236 {

	@Test
	void test() {
		int solution = solution(3, new int[][]{
						{0, 0, 1},
						{0, 0, 0},
						{0, 0, 0},
		}, new Shark(2, 1, 2, 0));

		Assertions.assertEquals(solution, 3);
	}

	@Test
	void test2() {
		int solution = solution(4, new int[][]{
						{4, 3, 2, 1},
						{0, 0, 0, 0},
						{0, 0, 0, 0},
						{1, 2, 3, 4},
		}, new Shark(2, 2, 2, 0));

		Assertions.assertEquals(solution, 14);
	}

	@Test
	void test3() {
		int solution = solution(6, new int[][]{
						{5, 4, 3, 2, 3, 4},
						{4, 3, 2, 3, 4, 5},
						{3, 2, 0, 5, 6, 6},
						{2, 1, 2, 3, 4, 5},
						{3, 2, 1, 6, 5, 4},
						{6, 6, 6, 6, 6, 6},
		}, new Shark(2, 2, 2, 0));

		Assertions.assertEquals(solution, 60);
	}

	@Test
	void test4() {
		int solution = solution(6, new int[][]{
						{6, 0, 6, 0, 6, 1},
						{0, 0, 0, 0, 0, 2},
						{2, 3, 4, 5, 6, 6},
						{0, 0, 0, 0, 0, 2},
						{0, 2, 0, 0, 0, 0},
						{3, 0, 3, 0, 0, 1},
		}, new Shark(5, 1, 2, 0));

		Assertions.assertEquals(solution, 48);
	}

	int[] dx = {0, -1, 0, 1};
	int[] dy = {1, 0, -1, 0};

	/**
	 * [문제] https://www.acmicpc.net/problem/16236
	 * [분류] 시뮬레이션, 그래프, BFS
	 * 
	 * @param n NxN
	 * @param map 0:빈칸, 1~6:물고기
	 * @param shark 상어 위치 및 사이즈
	 * @return 먹을 수 있는 물고기를 모두 먹고, 소요되는 시간
	 */
	public int solution(int n, int[][] map, Shark shark) {
		int result = 0;

		while (true) {
			Fish edibleFish = getEdibleFish(n, map, shark);
			if (edibleFish == null) break;

			shark.eat(edibleFish);
			map[edibleFish.x][edibleFish.y] = 0;
			result += edibleFish.dist;
		}
		return result;
	}

	private Fish getEdibleFish(int n, int[][] map, Shark shark) {
		PriorityQueue<Fish> result = new PriorityQueue<>((o1, o2) -> {
			if (o1.dist - o2.dist == 0) return (o1.x - o2.x != 0) ? o1.x - o2.x : o1.y - o2.y;
			else return o1.dist - o2.dist;
		});

		Queue<Point> q = new LinkedList<>();
		int[][] dist = new int[n][n];
		q.offer(new Point(shark.x, shark.y));
		dist[shark.x][shark.y] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if (dist[nx][ny] > 0 || shark.size < map[nx][ny]) continue;
				if (map[nx][ny] != 0 && map[nx][ny] < shark.size) {
					result.offer(new Fish(nx, ny, dist[cur.x][cur.y]));
				}
				q.offer(new Point(nx, ny));
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
			}
		}
		return result.isEmpty() ? null : result.poll();
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	class Fish {
		int x, y, dist;

		public Fish(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	class Shark {
		int x, y, size, cnt;

		public Shark(int x, int y, int size, int cnt) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
		}

		public void eat(Fish fish) {
			if (++cnt == size) {
				cnt = 0;
				size++;
			}
			this.x = fish.x;
			this.y = fish.y;
		}
	}
}
