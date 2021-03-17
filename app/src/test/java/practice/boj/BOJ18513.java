package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BOJ18513 {

	@Test
	void test() {
		long solution = solution(2, 5, new int[]{0, 3});
		System.out.println(solution);
	}

	int[] dir = {-1, 1};

	/**
	 * [문제] https://www.acmicpc.net/problem/18513
	 * [분류] 그래프, BFS
	 * [풀이]
	 * 1. 모든 샘터를 기준으로 BFS를 수행한다.
	 * 2. 좌우로 샘터가 아니면서 방문하지 않은 곳을 거리를 구한다.
	 * 3. count를 증가시키면서 해당 count가 k라면 결과를 반환한다.
	 *
	 * @param n 샘터 개수
	 * @param k 집 개수
	 * @param numbers 샘터가 위치하는 구간
	 * @return 최소 불행도
	 */
	public long solution(int n, int k, int[] numbers) {
		long result = 0;
		int count = 0;
		Set<Long> visited = new HashSet<>();
		Set<Long> wall = new HashSet<>();
		Queue<Point> q = new LinkedList<>();
		for (long number : numbers) {
			q.offer(new Point(number, 0));
			visited.add(number);
			wall.add(number);
		}

		loop: while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 2; i++) {
				long position = cur.position + dir[i];

				if (wall.contains(position) || visited.contains(position)) continue;
				visited.add(position);
				result += cur.dist + 1;
				q.offer(new Point(position, cur.dist + 1));
				count++;
				if (count == k) break loop;
			}
		}
		return result;
	}

	class Point {
		long position, dist;

		public Point(long position, long dist) {
			this.position = position;
			this.dist = dist;
		}
	}
}
