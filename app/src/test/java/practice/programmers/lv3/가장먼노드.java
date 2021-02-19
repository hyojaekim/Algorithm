package practice.programmers.lv3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 가장먼노드 {

	@Test
	void test() {
		int solution = solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
		Assertions.assertEquals(solution, 3);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/49189?language=java
	 * [분류] 그래프, BFS
	 *
	 * @param n 정점 개수
	 * @param edge 간선 정보
	 * @return 가장 먼 노드의 개수 반환
	 */
	public int solution(int n, int[][] edge) {
		int answer = 0;
		int max = 0;
		Map<Integer, Set<Integer>> graph = initGraph(n, edge);
		int[] dist = initDist(n);
		Queue<Integer> q = new LinkedList<>();
		q.add(1);

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			for (Integer node : graph.get(cur)) {
				if (dist[node] != -1) continue;
				q.offer(node);
				dist[node] = dist[cur] + 1;
				max = Math.max(max, dist[node]);
			}
		}

		for (int num : dist) {
			if (num == max) answer++;
		}
		return answer;
	}

	private int[] initDist(int n) {
		int[] result = new int[n + 1];
		Arrays.fill(result, -1);
		result[1] = 0;
		return result;
	}

	private Map<Integer, Set<Integer>> initGraph(int n, int[][] edge) {
		Map<Integer, Set<Integer>> result = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			result.put(i, new HashSet<>());
		}
		for (int[] e : edge) {
			result.get(e[0]).add(e[1]);
			result.get(e[1]).add(e[0]);
		}
		return result;
	}
}
