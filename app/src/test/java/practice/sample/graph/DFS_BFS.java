package practice.sample.graph;

import java.util.*;

public class DFS_BFS {

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
