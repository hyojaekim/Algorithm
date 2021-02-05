package practice.boj.graph.dfs_bfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BOJ11724 {

	@Test
	void test() {
		int result = solution(6, 5, new int[][]{
						{1, 2},
						{2, 5},
						{5, 1},
						{3, 4},
						{4, 6}
		});

		Assertions.assertEquals(result, 2);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/11724
	 * [분류] DFS & BFS
	 *
	 * @param N       정점 개수
	 * @param M       간선 개수
	 * @param reqInfo [0] -> [1]
	 * @return 연결된 개수
	 */
	public int solution(int N, int M, int[][] reqInfo) {
		int result = 0;
		Map<Integer, List<Integer>> graph = init(N, M, reqInfo); //그래프 초기화
		boolean[] visited = new boolean[N + 1];
		visited[0] = true;

		for (int vertex = 1; vertex <= N; vertex++) { //방문하지 않은 정점에 dfs 수행
			if (visited[vertex]) continue;
			dfs(graph, visited, vertex);
			result++;
		}
		return result;
	}

	private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int curNode) {
		visited[curNode] = true;

		for (Integer node : graph.get(curNode)) {
			if (visited[node]) continue;
			dfs(graph, visited, node);
		}
	}

	private Map<Integer, List<Integer>> init(int n, int m, int[][] reqInfo) {
		HashMap<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i + 1, new LinkedList<>());
		}

		for (int i = 0; i < m; i++) {
			result.get(reqInfo[i][0]).add(reqInfo[i][1]);
			result.get(reqInfo[i][1]).add(reqInfo[i][0]);
		}
		return result;
	}
}
