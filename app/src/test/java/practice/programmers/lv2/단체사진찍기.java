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
	Map<Character, Integer> people;

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
		this.people = initPeople();

		dfs(new LinkedList<>(), initConditions(data));
		return count;
	}

	private Map<Character, Integer> initPeople() {
		Map<Character, Integer> result = new HashMap<>();
		result.put('A', 0); result.put('C', 1); result.put('F', 2); result.put('J', 3);
		result.put('M', 4); result.put('N', 5); result.put('R', 6); result.put('T', 7);
		return result;
	}

	private Map<Character, List<Condition>> initConditions(String[] data) {
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

	private void dfs(Queue<Character> q, Map<Character, List<Condition>> conditions) {
		if (q.size() == 8) {
			Map<Character, Integer> temp = new HashMap<>(); //q에 있는 순서대로 해당 위치를 초기화
			LinkedList<Character> convertedQ = (LinkedList<Character>) q;
			for (int i = 0; i < convertedQ.size(); i++) temp.put(convertedQ.get(i), i);

			for (Character c : convertedQ) { //q에 있는 사람 순으로, 모든 조건에 적합하면 카운트
				if (!conditions.containsKey(c)) continue;
				for (Condition condition : conditions.get(c)) {
					if (!condition.isPossible(temp.get(c), temp.get(condition.to))) return;
				}
			}
			count++;
		}

		for (Character c : people.keySet()) {
			int idx = people.get(c);
			if (visited[idx]) continue;
			visited[idx] = true;
			q.offer(c);
			dfs(q, conditions);
			q.remove(c);
			visited[idx] = false;
		}
	}

	class Condition {
		char to;
		int distance;
		char symbol;

		public Condition(char to, char distance, char symbol) {
			this.to = to;
			this.distance = Integer.parseInt(String.valueOf(distance));
			this.symbol = symbol;
		}

		public boolean isPossible(int c1, int c2) {
			int distance = Math.abs(c1 - c2) - 1;

			if (symbol == '=') return this.distance == distance;
			else if (symbol == '>') return this.distance > distance;
			else return this.distance < distance;
		}
	}
}
