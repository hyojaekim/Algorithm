package practice.boj;

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
		for (Integer idx : solution) {
			System.out.println(idx);
		}
	}
	int[] computer;

	public List<Integer> solution(int n, int[][] info) {
		Map<Integer, List<Integer>> graph = initGraph(n, info);
		int max = Integer.MIN_VALUE;
		this.computer = new int[n + 1];
		for (int node = 1; node <= n; node++) {
			boolean[] visited = new boolean[n + 1];
			dfs(node, node, graph, visited);
			max = Math.max(max, computer[node]);
		}

		List<Integer> result = new ArrayList<>();
		for (int node = 1; node <= n; node++) {
			if (computer[node] == max) result.add(node);
		}
		return result;
	}

	private void dfs(int start, int target, Map<Integer, List<Integer>> graph, boolean[] visited) {
		visited[target] = true;

		for (Integer node : graph.get(target)) {
			if (visited[node]) continue;
			computer[start]++;
			dfs(start, node, graph, visited);
		}
	}

	private Map<Integer, List<Integer>> initGraph(int n, int[][] info) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i + 1, new LinkedList<>());
		}

		for (int[] ints : info) {
			result.get(ints[1]).add(ints[0]);
		}
		return result;
	}
}
