package practice.sample.dijkstra;

import java.util.*;

public class Dijkstra {

	public void solution() {
		Map<Integer, List<Node>> graph = new HashMap<>(); //그래프 초기화(정점, 간선, 가중치)
		Map<Integer, Integer> dist = new HashMap<>(); //노드의 최소거리를 관리한다.
		int start = 1;
		int end = 5;

		/**
		 * TODO v1, v2를 거쳐야 한다면
		 * 1 -> v1, v1 -> v2, v2 -> n 총 3개의 다익스트라 수행(각각 더함, -1이 나온다면 종료할 수 있게 만들어야함)
		 * 1 -> v2, v2 -> v1, v1 -> n
		 */

		dijkstra(graph, dist, start, end);
	}

	private int dijkstra(Map<Integer, List<Node>> graph, Map<Integer, Integer> dist, int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w)); //우선순위 큐 생성
		pq.offer(new Node(start, 0)); //시작 노드 추가

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

//			if (cur.v == end) { //TODO 도착 지점에 오면
//				// TODO 로직
//				break;
//			}
			if (!dist.containsKey(cur.v)) {
//				if (k >= 0) { //TODO 경유지 개수가 존재한다면 (dist 제거 및 관련 로직 제거)
				dist.put(cur.v, cur.w);
				for (Node node : graph.get(cur.v)) {
					pq.offer(new Node(node.v, node.w + cur.w));
				}
			}
		}

//		if (dist.size() == n) return dist.get(시작 노드); //TODO 모두 방문해야 한다면
		return -1;
	}

	class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
