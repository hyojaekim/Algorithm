package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ13023 {

	@Test
	void test() {
		int solution = solution(5, 4, new int[][]{
						{0, 1},
						{1, 2},
						{2, 3},
						{3, 4}
		});

		Assertions.assertEquals(solution, 1);
	}

	@Test
	void test1() {
		int solution = solution(5, 5, new int[][]{
						{0, 1},
						{1, 2},
						{2, 3},
						{3, 0},
						{1, 4},
		});

		Assertions.assertEquals(solution, 1);
	}

	@Test
	void test2() {
		int solution = solution(6, 5, new int[][]{
						{0, 1},
						{0, 2},
						{0, 3},
						{0, 4},
						{0, 5},
		});

		Assertions.assertEquals(solution, 0);
	}

	@Test
	void test3() {
		int solution = solution(8, 8, new int[][]{
						{1, 7},
						{3, 7},
						{4, 7},
						{3, 4},
						{4, 6},
						{3, 5},
						{0, 4},
						{2, 7}
		});

		Assertions.assertEquals(solution, 1);
	}


	boolean find;

	/**
	 * [문제] https://www.acmicpc.net/problem/13023
	 * [분류] 그래프, DFS
	 *
	 * @param n 친구수 (정점 개수)
	 * @param m 친구관계수 (간선 개수)
	 * @param connectedFriends 0과 1은 친구 관계이다.
	 * @return a -> b -> c -> d -> e 관계가 있으면 1, 없으면 0
	 */
	public int solution(int n, int m, int[][] connectedFriends) {
		this.find = false;
		Map<Integer, List<Integer>> graph = initGraph(n, m, connectedFriends);
		for (int i = 0; i < n; i++) {
			find = false;
			boolean[] visited = new boolean[n];
			visited[i] = true;
			dfs(i, graph, visited, 1);
			if (find) return 1;
		}
		return 0;
	}

	private void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, int cnt) {
		if (cnt == 5) {
			find = true;
			return;
		}

		for (Integer child : graph.get(node)) {
			if (visited[child]) continue;
			visited[child] = true;
			dfs(child, graph, visited, cnt + 1);
			visited[child] = false;
		}
	}

	private Map<Integer, List<Integer>> initGraph(int n, int m, int[][] connectedFriends) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			result.put(i, new ArrayList<>()); //속도 안나오면 바꾸기
		}

		for (int i = 0; i < m; i++) {
			int a = connectedFriends[i][0];
			int b = connectedFriends[i][1];
			result.get(a).add(b);
			result.get(b).add(a);
		}
		return result;
	}
}
