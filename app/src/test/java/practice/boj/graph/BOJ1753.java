package practice.boj.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ1753 {

	/**
	 * [input]
	 * 5 6
	 * 1
	 * 5 1 1
	 * 1 2 2
	 * 1 3 3
	 * 2 3 4
	 * 2 4 5
	 * 3 4 6
	 */
	@Test
	void test() {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int vCnt = Integer.parseInt(firstLine[0]);
		int eCnt = Integer.parseInt(firstLine[1]);
		int start = Integer.parseInt(sc.nextLine());

		int[][] info = new int[eCnt][3];
		for (int i = 0; i < eCnt; i++) {
			String[] line = sc.nextLine().split(" ");
			info[i][0] = Integer.parseInt(line[0]);
			info[i][1] = Integer.parseInt(line[1]);
			info[i][2] = Integer.parseInt(line[2]);
		}
		solution(vCnt, eCnt, info, start);

		solution(5, 6, new int[][]{{5, 1, 1}, {1, 2, 2}, {1, 3, 3}, {2, 3, 4}, {2, 4, 5},{3, 4, 6}}, 1);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/1753
	 * [분류] 그래프 최단거리 - 다익스트라 알고리즘
	 * [메모]
	 * 출력 형식에 맞지 않다고 계속 나왔는데.. 모르고 줄바꿈을 하나 더 추가했었다...
	 *
	 * @param vCnt 정점 개수
	 * @param eCnt 간선 개수
	 * @param info [0] -> [1] 가중치:[2]
	 * @param start 시작 노드
	 */
	public void solution(int vCnt, int eCnt, int[][] info, int start) {
		Map<Integer, List<Node>> graph = init(info, vCnt); //그래프 초기화(정점, 간선, 가중치)
		Map<Integer, Integer> dist = new HashMap<>(); //노드의 최소거리를 관리한다.

		dijkstra(graph, dist, start);
	}

	private Map<Integer, List<Node>> init(int[][] info, int vCnt) {
		Map<Integer, List<Node>> result = new HashMap<>();
		for (int i = 1; i <= vCnt; i++) {
			result.put(i, new ArrayList<>());
		}
		for (int[] a : info) {
			result.get(a[0]).add(new Node(a[1], a[2]));
		}
		return result;
	}

	private void dijkstra(Map<Integer, List<Node>> graph, Map<Integer, Integer> dist, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w)); //우선순위 큐 생성
		pq.offer(new Node(start, 0)); //시작 노드 추가

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (!dist.containsKey(cur.v)) {
				dist.put(cur.v, cur.w);
				for (Node node : graph.get(cur.v)) {
					pq.offer(new Node(node.v, node.w + cur.w));
				}
			}
		}

		for (int i = 1; i <= graph.size(); i++) {
			if (!dist.containsKey(i)) System.out.println("INF");
			else System.out.println(dist.get(i));
		}
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
