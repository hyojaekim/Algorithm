package practice.boj.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 특정한최단경로 {

	/**
	 * 4 6
	 * 1 2 3
	 * 2 3 3
	 * 3 4 1
	 * 1 3 5
	 * 2 4 5
	 * 1 4 4
	 * 2 3
	 */
	@Test
	void test() {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int e = Integer.parseInt(firstLine[1]);
		int[][] info = new int[e][3];
		for (int i = 0; i < e; i++) {
			String[] line = sc.nextLine().split(" ");
			info[i][0] = Integer.parseInt(line[0]);
			info[i][1] = Integer.parseInt(line[1]);
			info[i][2] = Integer.parseInt(line[2]);
		}
		String[] lastLine = sc.nextLine().split(" ");
		int v1 = Integer.parseInt(lastLine[0]);
		int v2 = Integer.parseInt(lastLine[1]);

		int result = solution(n, e, info, v1, v2);
		Assertions.assertEquals(result, 7);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/1504
	 * [분류] 그래프 최단거리 - 다익스트라 알고리즘
	 * [시간 복잡도] O(E log V) 지수 로그 시간
	 * [풀이]
	 * 1) 1 -> v1 -> v2 -> n
	 * 2) 1 -> v2 -> v1 -> n
	 * 최소 거리로 가기 위해 2가지 방법이 존재함
	 * 각 방법에 대해 1 -> v1, v1 -> v2, v2 -> n으로 총 3가지 다익스트라 알고리즘을 수행해야 한다.
	 * (dist에 최소거리 갱신하지 해야하는거 조심)
	 *
	 * @param n 정점 개수
	 * @param e 간선 개수
	 * @param info [0] -> [1] 가중치:[2]
	 * @param v1 거쳐야 하는 정점 1
	 * @param v2 거쳐야 하는 정점 2
	 * @return 1부터 n까지 최소거리(v1, v2를 거쳐야 함)
	 */
	private int solution(int n, int e, int[][] info, int v1, int v2) {
		//graph 초기화 -> 노드, 인접 리스트
		Map<Integer, List<Node>> graph = init(info, n, e);

		//1 -> v1까지, v1 -> v2까지, v2 -> n까지
		int firstRouteResult = 0;
		firstRouteResult = dijkstra(graph, 1, v1, firstRouteResult);
		firstRouteResult = dijkstra(graph, v1, v2, firstRouteResult);
		firstRouteResult = dijkstra(graph, v2, n, firstRouteResult);
		if (firstRouteResult == -1) return -1;

		//1 -> v2까지, v2 -> v1까지, v1 -> n까지
		int secondRouteResult = 0;
		secondRouteResult = dijkstra(graph, 1, v2, secondRouteResult);
		secondRouteResult = dijkstra(graph, v2, v1, secondRouteResult);
		secondRouteResult = dijkstra(graph, v1, n, secondRouteResult);

		//둘중에 최소 리턴, 하나라도 -1이 있으면 그대로 -1 반환하기
		return secondRouteResult == -1 ? -1 : Math.min(firstRouteResult, secondRouteResult);
	}

	private Map<Integer, List<Node>> init(int[][] info, int n, int e) {
		HashMap<Integer, List<Node>> result = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			result.put(i, new ArrayList<>());
		}
		for (int[] a : info) {
			result.get(a[0]).add(new Node(a[1], a[2]));
			result.get(a[1]).add(new Node(a[0], a[2]));
		}
		return result;
	}

	private int dijkstra(Map<Integer, List<Node>> graph, int start, int end, int result) {
		if (result == -1) return -1;
		Map<Integer, Integer> dist = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.vertex == end) {
				return result + cur.weight;
			}
			if (!dist.containsKey(cur.vertex)) {
				dist.put(cur.vertex, cur.weight);
				for (Node node : graph.get(cur.vertex)) {
					pq.offer(new Node(node.vertex, node.weight + cur.weight));
				}
			}
		}
		return -1;
	}

	static class Node {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
}
