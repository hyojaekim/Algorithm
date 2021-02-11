package practice.programmers.kakao_2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 순위검색 {

	@Test
	void test() {
		int[] solution = solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
						new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
		Assertions.assertArrayEquals(solution, new int[]{1, 1, 1, 1, 2, 4});
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/72412
	 * [분류] 조합, 이진탐색
	 *
	 * @param reqInfo ex) java backend junior pizza 150
	 * @param reqQuery ex) java and backend and - and pizza 100
	 * @return 각 쿼리 조건에 만족하는 기준 점수 이상인 것들 카운트
	 */
	public int[] solution(String[] reqInfo, String[] reqQuery) {
		int[] result = new int[reqQuery.length];
		//[o] infos 생성하기 <쿼리, 점수 리스트>
		Map<String, List<Integer>> infos = initInfos(reqInfo);

		//[o] 쿼리를 순회하면서 해당하는 쿼리에 점수 n 이상인 것들 카운트
		for (int i = 0; i < reqQuery.length; i++) {
			String[] split = reqQuery[i].split(" and ");
			String[] soulFoodAndPoint = split[3].split(" ");
			String convertedQuery = split[0] + split[1] + split[2] + soulFoodAndPoint[0];
			int point = Integer.parseInt(soulFoodAndPoint[1]);

			//[o] 쿼리가 없으면 0
			if (!infos.containsKey(convertedQuery)) continue;

			//[o] 첫번째가 원하는 점수 이상이면 그대로 이진 탐색 x
			List<Integer> pointOfPerson = infos.get(convertedQuery);
			if (pointOfPerson.size() >= 1 && pointOfPerson.get(0) >= point) {
				result[i] = pointOfPerson.size();
				continue;
			}

			//[o] 이진 탐색으로 점수 n 이상인 것들을 카운트한다.
			int left = 0;
			int right = pointOfPerson.size() - 1;
			int mid;
			int idx = right + 1;
			while (left <= right) {
				mid = (left + right) / 2;
				if (pointOfPerson.get(mid) > point) {
					idx = Math.min(idx, mid);
					right = mid - 1;
				} else if (pointOfPerson.get(mid) < point) {
					left = mid + 1;
				} else {
					for (int j = mid; j >= 0; j--) {
						if (pointOfPerson.get(j) < point) break;
						idx = j;
					}
					break;
				}
			}
			if (idx >= 0 && idx < pointOfPerson.size() && pointOfPerson.get(idx) >= point) {
				result[i] = pointOfPerson.size() - idx;
			}
		}
		return result;
	}

	private Map<String, List<Integer>> initInfos(String[] reqInfo) {
		//[o] 경우의 수 구하기 (4가지)
		List<boolean[]> totalVisited = new ArrayList<>();
		combination(new boolean[4], totalVisited, 0, 4);

		Map<String, List<Integer>> result = new HashMap<>();
		for (String info : reqInfo) {
			String[] split = info.split(" ");
			//[o] 방문하지 않은 것은 "-"
			for (boolean[] visited : totalVisited) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < visited.length; i++) {
					sb.append((visited[i]) ? split[i] : "-");
				}
				result.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(Integer.parseInt(split[4]));
			}
		}

		//[o] 정렬
		for (String key : result.keySet()) {
			Collections.sort(result.get(key));
		}
		return result;
	}

	private void combination(boolean[] visited, List<boolean[]> totalVisited, int start, int r) {
		totalVisited.add(visited.clone());
		if (r == -1) return;

		for (int i = start; i < visited.length; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			combination(visited, totalVisited, i + 1, r - 1);
			visited[i] = false;
		}
	}
}
