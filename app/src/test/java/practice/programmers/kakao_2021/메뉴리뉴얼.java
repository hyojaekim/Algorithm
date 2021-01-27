package practice.programmers.kakao_2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 메뉴리뉴얼 {

	@Test
	void test() {
		String[] solution = solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
		Assertions.assertArrayEquals(solution, new String[]{"AC", "ACDE", "BCFG", "CDE"});
	}

	List<Set<Character>> convertedOrders;
	List<Character> totalMenu;
	boolean[] visited;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/72411
	 * [분류] dfs, 조합
	 *
	 * @param orders 사람들 주문 내역
	 * @param course n개의 코스들
	 * @return 대표 메뉴로 선정된 메뉴들을 오름차순으로 정렬하여 반환하라.
	 */
	public String[] solution(String[] orders, int[] course) {
		this.convertedOrders = initOrders(orders);
		this.totalMenu = initTotalMenu(convertedOrders);
		this.visited = new boolean[totalMenu.size()];

		Map<Integer, List<String>> mainMenu = new HashMap<>();
		for (int n : course) { //n개의 조합 만들기
			Pair pair = new Pair(0, new HashSet<>());
			dfs(pair, "", n, 0);
			mainMenu.put(n, new ArrayList<>(pair.menuList));
		}

		List<String> result = new ArrayList<>(); //오름차순으로 정렬해서 반환하기
		for (List<String> value : mainMenu.values()) {
			result.addAll(value);
		}
		Collections.sort(result);
		String[] answer = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		return answer;
	}

	/**
	 * 몇개인지 세어서 가장 많이 나오는 메뉴를 대표메뉴로 선정한다. (여러개 가능, 2개 이상되어야 함)
	 * 최대 개수보다 작으면 -> 패스
	 * 최대 개수보다 크면 -> 모두 비우고 넣기
	 * 최대 개수랑 같으면 -> 현재 리스트에 넣기
	 */
	private void dfs(Pair pair, String curMenu, int max, int start) {
		if (curMenu.length() == max) {
			int cnt = 0;
			for (Set<Character> order : convertedOrders) {
				boolean possible = true;
				for (Character menu : curMenu.toCharArray()) {
					if (!order.contains(menu)) {
						possible = false;
						break;
					}
				}
				if (possible) cnt++;
			}

			if (cnt < pair.max || cnt < 2) return;
			else if (cnt > pair.max) {
				pair.menuList = new HashSet<>();
				pair.max = cnt;
			}
			pair.menuList.add(curMenu);
			return;
		}

		for (int i = start; i < totalMenu.size(); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			dfs(pair, curMenu + totalMenu.get(i), max, i);
			visited[i] = false;
		}
	}

	private List<Character> initTotalMenu(List<Set<Character>> convertedOrders) {
		Set<Character> result = new HashSet<>();
		for (Set<Character> orderSet : convertedOrders) {
			result.addAll(orderSet);
		}
		return new ArrayList<>(result);
	}

	private List<Set<Character>> initOrders(String[] orders) {
		ArrayList<Set<Character>> result = new ArrayList<>();
		for (String order : orders) {
			Set<Character> orderSet = new HashSet<>();
			for (char c : order.toCharArray()) {
				orderSet.add(c);
			}
			result.add(orderSet);
		}
		return result;
	}

	static class Pair {
		int max;
		Set<String> menuList;

		public Pair(int max, Set<String> menuList) {
			this.max = max;
			this.menuList = menuList;
		}
	}
}
