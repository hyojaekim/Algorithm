package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 일정재구성 {

	@Test
	void test() {
		List<String> result = solution(Arrays.asList(new String[]{"MUC", "LHR"}, new String[]{"JFK", "MUC"}, new String[]{"SFO", "SJC"}, new String[]{"LHR", "SFO"}));
		Assertions.assertEquals(result, Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"));

		List<String> result2 = solution(Arrays.asList(new String[]{"JFK", "SFO"}, new String[]{"JFK", "ATL"}, new String[]{"SFO", "ATL"}, new String[]{"ATL", "JFK"}, new String[]{"ATL", "SFO"}));
		Assertions.assertEquals(result2, Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"));
	}

	List<String> result;
	Map<String, Queue<String>> tickets;
	/**
	 * [사용 알고리즘] DFS, 우선순위 큐
	 * [주의 1] 여러 일정이 있는 경우 사전 어휘 순으로 방문
	 *
	 * @param tickets [0]from, [1]to
	 * @return JFK에서 출발하는 여행 일정을 구성하라.
	 */
	public List<String> solution(List<String[]> tickets) {
		this.result = new ArrayList<>();
		this.tickets = init(tickets);
		String start = "JFK";
		result.add(start);

		dfs(start);
		return result;
	}

	private void dfs(String start) {
		if (this.tickets.containsKey(start) && !tickets.get(start).isEmpty()) {
			String arrivalTicket = tickets.get(start).poll();
			result.add(arrivalTicket);
			dfs(arrivalTicket);
		}
	}

	/**
	 * 모든 티켓 정보를 저장한다.
	 * 도착 티켓은 사전 순으로 정렬한다.
	 */
	private Map<String, Queue<String>> init(List<String[]> tickets) {
		Map<String, Queue<String>> result = new HashMap<>();
		for (String[] ticket : tickets) {
			String from = ticket[0];
			String to = ticket[1];

			if (result.containsKey(from)) {
				result.get(from).add(to);
			} else {
				Queue<String> temp = new PriorityQueue<>(String::compareTo);
				temp.add(to);
				result.put(from, temp);
			}
		}
		return result;
	}
}
