package practice.book.coding_interview.chapter13;

import org.junit.jupiter.api.Test;

import java.util.*;

public class K경유지내가장저렴한항권권 {
	@Test
	void test() {
		int solution = findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0);
		System.out.println(solution);
	}

	/**
	 * [문제] https://leetcode.com/problems/cheapest-flights-within-k-stops/
	 * [사용 알고리즘] 다익스트라 알고리즘
	 *
	 * @param n     노드 개수
	 * @param flights [0]:정점 [1]:연결정점 [2]:간선(소요시간)
	 * @param src   시작점
	 * @param dst   도착점
	 * @param K     경유지 개수
	 * @return src부터 dst까지 가장 저렴한 가격 (경유지 개수만큼)
	 */
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		Map<Integer, List<int[]>> graph = new HashMap<>(); //그래프(인접리스트) 값 세팅
		for (int[] flight : flights) {
			graph.computeIfAbsent(flight[0], g -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
		}

		PriorityQueue<int[]> minPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); //힙 [0]:정점 [1]:비용 [2]:경유지개수
		minPQ.add(new int[]{src, 0, K});

		while (!minPQ.isEmpty()) {
			int[] curr = minPQ.poll();
			int currVertex = curr[0]; int currCost = curr[1]; int currK = curr[2];

			if (currVertex == dst) { //도착 지점이면 비용 반환
				return currCost;
			}

			if (currK >= 0) { //요청하는 경유지 개수보다 작은 경우 힙에 넣음
				if (graph.containsKey(currVertex)) {
					for (int[] nei : graph.get(currVertex)) {
						minPQ.offer(new int[]{nei[0], nei[1] + currCost, currK - 1});
					}
				}
			}
		}
		return -1;
	}
}
