package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ1260 {

	@Test
	void test() {
//		solution(4, 5, 1, new int[][]{{1,2}, {1,3}, {1,4}, {2,4}, {3,4}});
		solution(5, 5, 3, new int[][]{{5,4}, {5,2}, {1,2}, {3,4}, {3,1}});
	}

	boolean[] visited;

	public void solution(int N, int M, int V, int[][] reqInfo) {
		Map<Integer, List<Integer>> graph = init(N, M, reqInfo);
		visited = new boolean[N + 1];
		visited[0] = true;
		dfs(graph, V);
		System.out.println();

		visited = new boolean[N + 1];
		visited[0] = true;
		bfs(graph, V);
		System.out.println();
	}

	private void dfs(Map<Integer, List<Integer>> graph, int v) {
		if (visited[v]) return;
		System.out.print(v + " ");
		visited[v] = true;

		for (Integer node : graph.get(v)) {
			if (!visited[node]) {
				dfs(graph, node);
			}
		}
	}

	private void bfs(Map<Integer, List<Integer>> graph, int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();
			System.out.print(cur + " ");

			for (Integer node : graph.get(cur)) {
				if (visited[node]) continue;
				visited[node] = true;
				q.offer(node);
			}
		}
	}

	private Map<Integer, List<Integer>> init(int N, int M, int[][] reqInfo) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < N; i++) {
			result.put(i + 1, new LinkedList<>());
		}
		for (int i = 0; i < M; i++) {
			result.get(reqInfo[i][0]).add(reqInfo[i][1]);
			result.get(reqInfo[i][1]).add(reqInfo[i][0]);
		}
		for (Integer key : result.keySet()) {
			result.get(key).sort(Comparator.comparingInt(o -> o));
		}
		return result;
	}
}
