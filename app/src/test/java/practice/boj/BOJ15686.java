package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15686 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);
		int[][] map = new int[n][n];
		List<Point> chickens = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String[] split1 = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split1[j]);
				if (map[i][j] == 2) chickens.add(new Point(i, j));
			}
		}
		BOJ15686 main = new BOJ15686();
		int solution = main.solution(n, m, chickens, map);
		System.out.println(solution);
	}

	List<List<Integer>> combinations;
	public int solution(int n, int m, List<Point> chickens, int[][] map) {
		int answer = Integer.MAX_VALUE;
		//Map<Point, PQ<Chicken> -> home 위치 / 가까운 치킨 가게
		Map<Point, PriorityQueue<Chicken>> homes = initHomes(n, chickens, map);

		//chickens.length 중에 m개 경우의 수 구하기
		this.combinations = new ArrayList<>();
		combination(chickens.size(), m, 0, new LinkedList<>(), new boolean[chickens.size()]);

		for (List<Integer> combination : combinations) {
			int result = getResult(new HashSet<>(combination), chickens, homes, answer);
			if (result != -1) answer = Math.min(answer, result);
		}
		return answer;
	}

	private int getResult(Set<Integer> combination, List<Point> chickens, Map<Point, PriorityQueue<Chicken>> homes, int answer) {
		int result = 0;

		for (PriorityQueue<Chicken> pq : homes.values()) { //모든 집을 순회
			PriorityQueue<Chicken> temp = new PriorityQueue<>();
			while (!pq.isEmpty()) {
				Chicken cur = pq.poll();
				temp.add(cur); // 꺼내서 temp에 넣기
				boolean found = false;

				for (Integer idx : combination) { // 가장 먼저 나오는거 조합에 있는지 확인하기
					Point point = chickens.get(idx);
					if (cur.x == point.x && cur.y == point.y) { // 있으면 -> 결과 더하기
						found = true;
						result += cur.dist;
						break;
					}
				}
				if (found) break;
			}
			pq.addAll(temp); // temp -> 다시 넣기
			if (answer < result) return -1;
		}
		return result;
	}

	private void combination(int n, int r, int start, LinkedList<Integer> numbers, boolean[] visited) {
		if (numbers.size() == r) {
			this.combinations.add(new ArrayList<>(numbers));
			return;
		}

		for (int i = start; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			numbers.add(i);
			combination(n, r, i + 1, numbers, visited);
			numbers.removeLast();
			visited[i] = false;
		}
	}

	private Map<Point, PriorityQueue<Chicken>> initHomes(int n, List<Point> chickens, int[][] map) {
		Map<Point, PriorityQueue<Chicken>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0 || map[i][j] == 2) continue;

				for (Point chicken : chickens) {
					int dist = chicken.calDist(i, j);
					result.computeIfAbsent(new Point(i, j), key -> new PriorityQueue<>()).add(new Chicken(chicken.x, chicken.y, dist));
				}
			}
		}
		return result;
	}
	static class Chicken implements Comparable<Chicken> {
		int x, y, dist;

		public Chicken(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Chicken o) {
			return this.dist - o.dist;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		public int calDist(int x, int y) {
			return Math.abs(this.x - x) + Math.abs(this.y - y);
		}
	}
}
