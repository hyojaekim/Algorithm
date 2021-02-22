package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ2606 {

	@Test
	void test() {
		int solution = solution(7, 6, new int[][]{{1, 2}, {2, 3}, {1, 5}, {5, 2}, {5, 6}, {4, 7}});
		System.out.println(solution);
	}

	@Test
	void input() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		int e = Integer.parseInt(scanner.nextLine());
		int[][] network = new int[e][2];

		for (int i = 0; i < e; i++) {
			String[] split = scanner.nextLine().split(" ");
			network[i] = new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
		}
		int solution = solution(n, e, network);
		System.out.println(solution);
	}

	int answer;

	/**
	 * [문제] https://www.acmicpc.net/problem/2606
	 * [분류] 그래프, DFS
	 *
	 * @param n 정점 개수
	 * @param e 간선 개수
	 * @param network 간선 정보
	 * @return 1번 컴퓨터로부터 감염되는 컴퓨터 수 반환
	 */
	public int solution(int n, int e, int[][] network) {
		this.answer = 0;
		boolean[] visited = new boolean[n + 1];
		Map<Integer, List<Integer>> graph = initGraph(n, e, network);

		visited[0] = true;
		visited[1] = true;
		dfs(1, graph, visited);
		return answer;
	}

	private void dfs(int target, Map<Integer, List<Integer>> graph, boolean[] visited) {
		if (!graph.containsKey(target)) return;

		for (Integer node : graph.get(target)) {
			if (visited[node]) continue;
			visited[node] = true;
			answer++;
			dfs(node, graph, visited);
		}
	}

	private Map<Integer, List<Integer>> initGraph(int n, int e, int[][] network) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i + 1, new ArrayList<>());
		}

		for (int i = 0; i < e; i++) {
			int key = network[i][0];
			int value = network[i][1];
			result.get(key).add(value);
			result.get(value).add(key);
		}
		return result;
	}
}
