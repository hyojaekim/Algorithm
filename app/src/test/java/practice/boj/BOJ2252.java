package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252 {

	@Test
	void test() {
		solution(3, new int[][]{
						{1, 3},
						{2, 3}
		});
	}

	public static void main(String[] args) throws IOException {
		BOJ2252 main = new BOJ2252();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);

		int[][] info = new int[m][2];
		for (int i = 0; i < m; i++) {
			String[] split = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(split[0]);
			info[i][1] = Integer.parseInt(split[1]);
		}

		main.solution(n, info);
	}

	/**
	 * [분류] 위상정렬
	 *
	 * @param n 정점 개수
	 * @param info A가 B보다 앞에 와야 한다.
	 */
	public void solution(int n, int[][] info) {
		int[] inDegree = new int[n + 1];
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			adj.put(i, new ArrayList<>());
		}
		for (int[] ints : info) {
			inDegree[ints[1]]++;
			adj.get(ints[0]).add(ints[1]);
		}

		List<Integer> result = topologicalSorting(inDegree, adj, n);
		for (Integer node : result) {
			System.out.print(node + " ");
		}
	}

	public List<Integer> topologicalSorting(int[] inDegree, Map<Integer, List<Integer>> adj, int n) {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) q.offer(i);
		}

		while (!q.isEmpty()) {
			Integer x = q.poll();
			result.add(x);

			for (Integer next : adj.get(x)) {
				if (--inDegree[next] == 0) q.offer(next);
			}
		}
		return result;
	}
}
