package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ11725 {

	@Test
	void test() {
		int[] solution = solution(7, new int[][]{{1, 6}, {6, 3}, {3, 5}, {4, 1}, {2, 4}, {4, 7}});
		Assertions.assertArrayEquals(solution, new int[]{4, 6, 1, 3, 1, 4});
	}


	/**
	 * [문제] https://www.acmicpc.net/problem/11725
	 * [분류] 그래프, DFS
	 *
	 * @param n 정점 개수
	 * @param info 간선 정보
	 * @return 2부터 n까지 부모 노드 반환
	 */

	public int[] solution(int n, int[][] info) {
		Map<Integer, List<Integer>> graph = init(n, info);

		int[] check = new int[n + 1];
		Arrays.fill(check, -1);
		check[0] = 0;
		check[1] = 1;

		dfs(n, graph, check, 1);
		return Arrays.copyOfRange(check, 2, n + 1);
	}

	private void dfs(int r, Map<Integer, List<Integer>> graph, int[] check, int parent) {
		if (r == 0) return;

		for (Integer node : graph.get(parent)) {
			if (check[node] != -1) continue;
			check[node] = parent;
			dfs(r - 1, graph, check, node);
		}
	}

	private Map<Integer, List<Integer>> init(int n, int[][] info) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i + 1, new ArrayList<>());
		}

		for (int[] ints : info) {
			result.get(ints[0]).add(ints[1]);
			result.get(ints[1]).add(ints[0]);
		}
		return result;
	}
}
