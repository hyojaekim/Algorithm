package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ1325 {

	@Test
	void test() {
		int n = 5;
		int[][] info = {
						{5, 4},
						{3, 1},
						{3, 2},
						{4, 3},
						{5, 3}
		};
		List<Integer> solution = solution(n, info);
		Assertions.assertEquals(solution, Arrays.asList(1, 2));
	}
	int[] computer;

	/**
	 * [문제] https://www.acmicpc.net/problem/1325
	 * [분류] 그래프, DFS, BFS
	 *
	 * 1. 입출력에 대한 시간 초과
	 * 2. DFS -> 시간초과
	 *
	 * @param n 정점 개수
	 * @param info 간선 정보
	 * @return 어떤 컴퓨터를 해킹해야 가장 많이 해킹 할 수 있는지 해당 컴퓨터들을 반환
	 */
	public List<Integer> solution(int n, int[][] info) {
		Map<Integer, List<Integer>> graph = initGraph(n, info);
		int max = Integer.MIN_VALUE;
		this.computer = new int[n + 1];
		boolean[] visited;
		for (int node = 1; node <= n; node++) {
			visited = new boolean[n + 1];
			bfs(node, graph, visited);
		}

		for (int node = 1; node <= n; node++) {
			max = Math.max(max, computer[node]);
		}
		List<Integer> result = new ArrayList<>();
		for (int node = 1; node <= n; node++) {
			if (computer[node] == max) result.add(node);
		}
		return result;
	}

	private void bfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			if (!graph.containsKey(cur)) continue;
			for (Integer node : graph.get(cur)) {
				if (visited[node]) continue;
				visited[node] = true;
				computer[node]++;
				q.offer(node);
			}
		}
	}

	private Map<Integer, List<Integer>> initGraph(int n, int[][] info) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int[] ints : info) {
			result.computeIfAbsent(ints[0], k -> new ArrayList<>()).add(ints[1]);
		}
		return result;
	}
}
