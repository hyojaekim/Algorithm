package practice.boj.graph.dfs_bfs;

import java.util.*;

public class BOJ1707 {

	/**
	 * [문제] https://www.acmicpc.net/problem/1707
	 * [분류] DFS & BFS - 이분 그래프
	 */
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int k = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < k; i++) {
			boolean possible = true;
			String[] firstLine = sc.nextLine().split(" ");
			int V = Integer.parseInt(firstLine[0]);
			int E = Integer.parseInt(firstLine[1]);
			int[][] reqInfo = new int[E][2];
			for (int j = 0; j < E; j++) {
				String[] line = sc.nextLine().split(" ");
				reqInfo[j][0] = Integer.parseInt(line[0]);
				reqInfo[j][1] = Integer.parseInt(line[1]);
			}
			Map<Integer, List<Integer>> graph = init(V, E, reqInfo);
			boolean[] visited = new boolean[V + 1];
			boolean[] color = new boolean[V + 1];

			for (int j = 1; j <= V; j++) {
				if (visited[j]) continue;
				if (!bfs(visited, color, graph, j)) {
					possible = false;
					break;
				}
			}
			String result = possible ? "YES" : "NO";
			System.out.println(result);
		}
	}

	private boolean bfs(boolean[] visited, boolean[] color, Map<Integer, List<Integer>> graph, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		color[start] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			//방문했는데 -> 인접한 노드가 같은 색깔이면 NO
			for (Integer node : graph.get(cur)) {
				if (visited[node]) {
					if (color[cur] == color[node]) return false;
				} else {
					visited[node] = true;
					color[node] = !color[cur];
					q.offer(node);
				}
			}
		}
		return true;
	}

	private Map<Integer, List<Integer>> init(int v, int e, int[][] reqInfo) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 1; i <= v; i++) {
			result.put(i, new LinkedList<>());
		}

		for (int i = 0; i < e; i++) {
			result.get(reqInfo[i][0]).add(reqInfo[i][1]);
			result.get(reqInfo[i][1]).add(reqInfo[i][0]);
		}
		return result;
	}
}
