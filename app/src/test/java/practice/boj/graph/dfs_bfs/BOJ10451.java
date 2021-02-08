package practice.boj.graph.dfs_bfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BOJ10451 {

	@Test
	void test() {
		Assertions.assertEquals(solution(10, new int[]{2, 1, 3, 4, 5, 6, 7, 9, 10, 8}), 7);
		Assertions.assertEquals(solution(8, new int[]{3, 2, 7, 8, 1, 4, 5, 6}), 3);
	}

	int[] graph;
	boolean[] visited;
	public int solution(int n, int[] numbers) {
		int result = 0;
		this.graph = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int number = numbers[i];
			this.graph[i + 1] = number;
		}
		this.visited = new boolean[n + 1];

		for (int num = 1; num <= n; num++) {
			if (visited[num]) continue;
			result++;
			dfs(num);
		}
		return result;
	}

	private void dfs(int num) {
		if (visited[num] || graph[num] == 0) return;

		visited[num] = true;
		dfs(graph[num]);
	}
}
