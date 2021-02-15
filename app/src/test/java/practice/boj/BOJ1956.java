package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BOJ1956 {

	@Test
	void test() {
		int result = solution(3, 4, new int[][]{
						{1, 2, 1},
						{3, 2, 1},
						{1, 3, 5},
						{2, 3, 2}
		});
		Assertions.assertEquals(result, 3);
	}

	@Test
	void input() {
//		Scanner sc = new Scanner(System.in);
//		String[] firstLine = sc.nextLine().split(" ");
//		int v = Integer.parseInt(firstLine[0]);
//		int e = Integer.parseInt(firstLine[1]);
//
//		int[][] reqInfo = new int[e][3];
//		for (int i = 0; i < e; i++) {
//			String[] line = sc.nextLine().split(" ");
//			reqInfo[i][0] = Integer.parseInt(line[0]);
//			reqInfo[i][1] = Integer.parseInt(line[1]);
//			reqInfo[i][2] = Integer.parseInt(line[2]);
//		}
//
//		Main main = new Main();
//		int result = main.solution(v, e, reqInfo);
//		System.out.println(result);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/1956
	 * [분류] 플로이드 와샬 알고리즘
	 * - [i][j], [j][i]가 INF가 아닌 경우 사이클이 존재한다는 의미
	 * - 사이클 중에 가장 적은 경로를 선택
	 *
	 * @param v 정점 개수
	 * @param e 간선 개수
	 * @param reqInfo [0] -> [1] 가중치:[2]
	 * @return
	 */
	public int solution(int v, int e, int[][] reqInfo) {
		int[][] graph = initGraph(v, e, reqInfo);
		floydWarshall(v, graph);

		int result = INF;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (graph[i][j] != INF && graph[j][i] != INF) {
					result = Math.min(result, graph[i][j] + graph[j][i]);
				}
			}
		}
		return result == INF ? -1 : result;
	}

	int INF = 50000000;

	private int[][] initGraph(int v, int e, int[][] reqInfo) {
		int[][] result = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				result[i][j] = INF;
			}
		}

		for (int i = 0; i < e; i++) {
			int x = reqInfo[i][0] - 1;
			int y = reqInfo[i][1] - 1;
			int c = reqInfo[i][2];
			result[x][y] = Math.min(result[x][y], c);
		}
		return result;
	}

	private void floydWarshall(int v, int[][] graph) {
		for (int k = 0; k < v; k++) {
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					if (i == j) continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}
	}
}
