package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ11403 {

	@Test
	void test() {
		solution(3, new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 0, 0}});
		solution(7, new int[][]{
						{0, 0, 0, 1, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 1},
						{0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 1, 1, 0},
						{1, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 1},
						{0, 0, 1, 0, 0, 0, 0}
		});

//		Scanner sc = new Scanner(System.in);
//		int n = Integer.parseInt(sc.nextLine());
//		int[][] info = new int[n][n];
//		for (int i = 0; i < n; i++) {
//			String[] line = sc.nextLine().split(" ");
//			for (int j = 0; j < n; j++) {
//				info[i][j] = Integer.parseInt(line[j]);
//			}
//		}
//		solution(n, info);
	}

	int INF = 100000;

	/**
	 * [문제] https://www.acmicpc.net/problem/11403
	 * [분류] 그래프 최단거리 - 플로이드 와샬 알고리즘
	 *
	 * @param n 정점 개수
	 * @param info [0] -> [1]
	 */
	public void solution(int n, int[][] info) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (info[i][j] == 0) info[i][j] = INF;
			}
		}

		floyd(n, info);

		for (int[] a : info) {
			for (int i = 0; i < n; i++) {
				if (a[i] == INF) System.out.print(0 + " ");
				else System.out.print(a[i] + " ");
			}
			System.out.println();
		}
	}

	private void floyd(int n, int[][] info) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (info[i][k] + info[k][j] < info[i][j]) info[i][j] = 1;
				}
			}
		}
	}
}
