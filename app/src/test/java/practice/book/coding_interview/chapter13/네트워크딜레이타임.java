package practice.book.coding_interview.chapter13;

import org.junit.jupiter.api.Test;

import java.util.*;

public class 네트워크딜레이타임 {
	@Test
	void test() {
//		int result = networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
		int result = networkDelayTime(new int[][]{{5, 2, 4}, {5, 4, 2}, {4, 3, 1}, {4, 2, 1}, {2, 1, 3}, {1, 3, 6}, {1, 4, 3}, {3, 4, 2}}, 5, 5);
		System.out.println(result);
	}

	/**
	 * [문제] https://leetcode.com/problems/network-delay-time/
	 *
	 * [사용 알고리즘] BFS - 다익스트라 알고리즘
	 * [주의 1] 모든 노드 탐색 불가시 -1 리턴
	 *
	 * @param times [0]:출발, [1]:도착, [2]:소요시간
	 * @param n 노드 개수
	 * @param k 출발 노드
	 * @return 모든 노드가 신호를 받을 수 있는 시간
	 */
	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, List<int[]>> graph = new HashMap<>(); //key=정점, value=[도착정점, 간선(소요시간]
		for (int[] time : times) {
			graph.computeIfAbsent(time[0], nn -> new ArrayList<>()).add(new int[]{time[1], time[2]});
		}

		Map<Integer, Integer> dist = new HashMap<>(); //해당 정점까지 비용, key=정점 / value=간선(소요시간)
		PriorityQueue<int[]> minPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); //간선(소요시간)이 작은 순서가 먼저 나온다. value=[정점, 간선(소요시간)]
		minPQ.add(new int[]{k, 0}); //시작 정점 넣기

		while (!minPQ.isEmpty()) {
			int[] cur = minPQ.poll();
			int curNode = cur[0]; int curDis = cur[1];

			if (dist.containsKey(curNode)) continue;
			dist.put(curNode, curDis);
			if (graph.containsKey(curNode)) { //연결된 정점을 힙에 넣는다.
				for (int[] nei : graph.get(curNode)) {
					minPQ.add(new int[]{nei[0], nei[1] + curDis});
				}
			}
		}

		if (dist.size() != n) return -1;
		return dist.keySet().stream()
						.mapToInt(dist::get)
						.max()
						.getAsInt();
	}
}
