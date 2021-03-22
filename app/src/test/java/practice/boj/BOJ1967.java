package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ1967 {

	@Test
	void test() {
		int solution = solution(12, new int[][]{
						{1, 2, 3},
						{1, 3, 2},
						{2, 4, 5},
						{3, 5, 11},
						{3, 6, 9},
						{4, 7, 1},
						{4, 8, 7},
						{5, 9, 15},
						{5, 10, 4},
						{6, 11, 6},
						{6, 12, 10},
		});
		Assertions.assertEquals(solution, 45);
	}

	Map<Integer, List<Node>> graph;
	Node maxNode;
	int max;

	/**
	 * [문제] https://www.acmicpc.net/problem/1967
	 * [분류] 그래프, DFS
	 * [아쉬운점]
	 * 1. 트리는 각 노드에 대해서 경로는 유일하다는 점을 인지하지 못했다.
	 * 2. 인접 행렬로 표현하면서 메모리 초과가 발생했다. -> 인접 리스트로 표현
	 *
	 * @param n 노드 -> 1 ~ n
	 * @param info 0:정점 -> 1:정점 (2:가중치)
	 * @return 두 지점 사이에서 가장 긴 길이를 반환하라.
	 */
	public int solution(int n, int[][] info) {
		this.graph = initGraph(info);
		this.maxNode = new Node(1, 0);
		this.max = 0;

		boolean[] visited = new boolean[n + 1];
		visited[1] = true;
		dfs(visited, maxNode, 0); //DFS -> 가장 길이가 긴 노드 찾기

		visited = new boolean[n + 1];
		visited[maxNode.vertex] = true;
		dfs(visited, maxNode, 0); //DFS -> 가장 길이가 긴 노드부터 시작해서 가장 길이가 큰 노드 찾기
		return this.max;
	}
	private Map<Integer, List<Node>> initGraph(int[][] info) {
		Map<Integer, List<Node>> graph = new HashMap<>();
		for (int[] ints : info) {
			graph.computeIfAbsent(ints[0], key -> new ArrayList<>()).add(new Node(ints[1], ints[2]));
			graph.computeIfAbsent(ints[1], key -> new ArrayList<>()).add(new Node(ints[0], ints[2]));
		}
		return graph;
	}

	private void dfs(boolean[] visited, Node cur, int distance) {
		if (this.max < distance) {
			this.maxNode = cur;
			this.max = distance;
		}

		for (Node node : graph.get(cur.vertex)) {
			if (visited[node.vertex]) continue;
			visited[node.vertex] = true;
			dfs(visited, node, distance + node.weight);
		}
	}

	class Node {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
}
