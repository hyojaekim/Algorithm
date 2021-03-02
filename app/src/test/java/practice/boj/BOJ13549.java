package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ13549 {

	@Test
	void test() {
		int solution = solution(5, 17);

		Assertions.assertEquals(solution, 2);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/13549
	 * [분류] 그래프, BFS
	 * [주의점]
	 * - 순간이동 0초, 왼쪽/오른쪽 1초
	 * - BFS는 가중치를 기준으로 돌기 떄문에 순간이동이 0초 걸리므로 가장 먼저 탐색해야 한다.
	 * - cur * 2, cur -1, cur + 1 (순서 중요 -> 가중치 기준)
	 *
	 * @param n 수빈이의 위치
	 * @param k 동생의 위치
	 * @return 가장 빠르게 찾을 수 있는 시간이 몇초 후 인가?
	 */
	public int solution(int n, int k) {
		if (n == k) return 0;

		int[] dist = new int[200_005];
		Arrays.fill(dist, -1);

		return bfs(dist, n, k);
	}

	private int bfs(int[] dist, int n, int k) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		dist[n] = 0;

		while (!q.isEmpty()) {
			Integer cur = q.poll();
			if (cur == k) break;

			for (int position : new int[]{cur * 2, cur - 1, cur + 1}) {
				if (position < 0 || position >= dist.length || dist[position] != -1) continue;

				if (position == cur * 2) dist[position] = dist[cur];
				else dist[position] = dist[cur] + 1;
				q.offer(position);
			}
		}
		return dist[k];
	}
}
