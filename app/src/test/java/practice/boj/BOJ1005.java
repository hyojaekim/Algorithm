package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005 {

	/**
	 * 1. 위상 정렬에 관한 문제들을 몇가지 풀어봐야겠다.
	 *
	 * 2. 아이디어 떠올리는데 시간이 많이 걸림.
	 * 현재 건물을 짓는데 소요되는 시간 => 현재 소요된 시간과
	 * 이전 건물 짓는데 소요되는 시간 + 현재 건물 짓는데 소요되는 시간의 MAX라는 것이라는
	 * 아이디어가 떠오르지 않아 시간이 많이 걸렸다.
	 *
	 * @param n 정점 개수 1~n
	 * @param w 내가 원하는 건물
	 * @param times 건물 짓는데 소요되는 시간들
	 * @param info A -> B
	 * @return 원하는 건물을 짓는데 소요되는 최소 시간
	 */
	public int solution(int n, int w, int[] times, int[][] info) {
		int[] inDegree = new int[n + 1];
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			adj.put(i, new ArrayList<>());
		}
		for (int[] ints : info) {
			inDegree[ints[1]]++;
			adj.get(ints[0]).add(ints[1]);
		}

		return topologicalSorting(n, w, times, inDegree, adj);
	}

	public int topologicalSorting(int n, int w, int[] times, int[] inDegree, Map<Integer, List<Integer>> adj) {
		int[] result = new int[n + 1];
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			result[i] = times[i];
			if (inDegree[i] == 0) q.offer(i);
		}

		while (!q.isEmpty()) {
			int x = q.poll();
			if (x == w) {
				return result[x];
			}

			//인접 노드 순회
			for (Integer next : adj.get(x)) {
				result[next] = Math.max(result[next], result[x] + times[next]);
				if (--inDegree[next] == 0) q.offer(next);
			}
		}
		return -1;
	}

	@Test
	void test() {
		int solution = solution(4, 4, new int[]{0, 10, 1, 100, 10}, new int[][]{
						{1, 2},
						{1, 3},
						{2, 4},
						{3, 4},
		});

		Assertions.assertEquals(solution, 120);
	}

	@Test
	void test2() {
		int solution = solution(8, 7, new int[]{0, 10, 20, 1, 5, 8, 7, 1, 43}, new int[][]{
						{1, 2},
						{1, 3},
						{2, 4},
						{2, 5},
						{3, 6},
						{5, 7},
						{6, 7},
						{7, 8},
		});

		Assertions.assertEquals(solution, 39);
	}

	public static void main(String[] args) throws IOException {
		BOJ1005 main = new BOJ1005();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount = Integer.parseInt(br.readLine());
		int[] n = new int[testCaseCount];
		int[] m = new int[testCaseCount];
		List<int[]> times = new ArrayList<>();
		List<int[][]> infos = new ArrayList<>();
		int[] w = new int[testCaseCount];

		for (int i = 0; i < testCaseCount; i++) {
			String[] split = br.readLine().split(" ");
			n[i] = Integer.parseInt(split[0]);
			m[i] = Integer.parseInt(split[1]);

			String[] split1 = br.readLine().split(" ");
			int[] curTimes = new int[n[i] + 1];
			for (int k = 1; k < n[i] + 1; k++) {
				curTimes[k] = Integer.parseInt(split1[k - 1]);
			}
			times.add(curTimes);

			int[][] info = new int[m[i]][2];
			for (int k = 0; k < m[i]; k++) {
				String[] split2 = br.readLine().split(" ");
				info[k][0] = Integer.parseInt(split2[0]);
				info[k][1] = Integer.parseInt(split2[1]);
			}
			infos.add(info);
			w[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < testCaseCount; i++) {
			int solution = main.solution(n[i], w[i], times.get(i), infos.get(i));
			System.out.println(solution);
		}
	}
}
