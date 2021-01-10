package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 단체사진찍기 {

	@Test
	void test() {
		int solution = solution(2, new String[]{"N~F=0", "R~T>2"});
		System.out.println(solution);
		Assertions.assertEquals(solution, 3648);
	}

	int count;
	boolean[] visited;
	char[] result;
	char[] friends = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

	/**
	 * [분류] dfs
	 *
	 * @param n data 개수
	 * @param data ex) A~B=0, A~B>2, A~B<2...
	 * @return 모든 조건에 적합하는 경우의 수를 구하라.
	 */
	public int solution(int n, String[] data) {
		this.count = 0;
		this.visited = new boolean[8];
		this.result = new char[8];

		dfs(0, initConditions(data), 8);
		return count;
	}

	private Map<Character, List<Condition>> initConditions(String[] data) { //모든 조건 생성 (key, 항상 낮은 알파벳)
		Map<Character, List<Condition>> result = new HashMap<>();
		for (String d : data) {
			char from = d.charAt(0);
			char to = d.charAt(2);
			if (from > to) {
				char temp = from;
				from = to;
				to = temp;
			}
			result.computeIfAbsent(from, k -> new ArrayList<>()).add(new Condition(to, d.charAt(4), d.charAt(3)));
		}
		return result;
	}

	private void dfs(int cnt, Map<Character, List<Condition>> conditions, int max) {
		if (cnt == max) {
			Map<Character, Integer> temp = new HashMap<>();
			for (int i = 0; i < max; i++) temp.put(result[i], i);

			for (int i = 0; i < max; i++) {
				if (!conditions.containsKey(result[i])) continue;
				for (Condition condition : conditions.get(result[i])) {
					if (condition.impossible(i, temp.get(condition.to))) return;
				}
			}
			count++;
		}

		for (int i = 0; i < max; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			result[cnt] = friends[i];
			dfs(cnt + 1, conditions, max);
			visited[i] = false;
		}
	}

	static class Condition {
		char to;
		int distance;
		char symbol;

		public Condition(char to, char distance, char symbol) {
			this.to = to;
			this.distance = Integer.parseInt(String.valueOf(distance));
			this.symbol = symbol;
		}

		public boolean impossible(int c1, int c2) {
			int distance = Math.abs(c1 - c2) - 1;

			if (symbol == '=') return !(distance == this.distance);
			else if (symbol == '>') return !(distance > this.distance);
			else return !(distance < this.distance);
		}
	}
}
