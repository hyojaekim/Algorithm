package practice.programmers.kakao_2020_internship;

import java.util.*;

public class E_동굴탐험 {

	public boolean solution(int n, int[][] path, int[][] order) {
		Map<Integer, List<Integer>> adj = getAdj(n, path); //인접 리스트 만들기
		adj = convert(n, adj);

		return topologicalSorting(n, adj, order);
	}

	private Map<Integer, List<Integer>> getAdj(int n, int[][] path) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i, new ArrayList<>());
		}

		int pathSize = path.length;
		for (int[] ints : path) { //양방향 그래프
			int a = ints[0];
			int b = ints[1];
			result.get(a).add(b);
			result.get(b).add(a);
		}
		return result;
	}

	private Map<Integer, List<Integer>> convert(int n, Map<Integer, List<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.offer(0);
		visited[0] = true;

		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i, new ArrayList<>());
		}

		while (!q.isEmpty()) {
			Integer x = q.poll();

			for (Integer next : graph.get(x)) {
				if (visited[next]) continue;
				visited[next] = true;
				result.get(x).add(next);
				q.offer(next);
			}
		}
		return result;
	}

	public boolean topologicalSorting(int n, Map<Integer, List<Integer>> adj, int[][] orders) { //위상 정렬 O(V + E)
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		int[] inDegree = new int[n];
		for (int i = 0; i < n; i++) {
			for (Integer next : adj.get(i)) {
				inDegree[next]++;
			}
		}

		for (int[] order : orders) {
			adj.get(order[0]).add(order[1]);
			inDegree[order[1]]++;
		}

		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) q.offer(i);
		}

		while (!q.isEmpty()) {
			Integer x = q.poll();
			visited.add(x);

			for (Integer next : adj.get(x)) {
				if (--inDegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		return visited.size() == n;
	}
}
