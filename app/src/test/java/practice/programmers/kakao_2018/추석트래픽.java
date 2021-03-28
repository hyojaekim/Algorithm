package practice.programmers.kakao_2018;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class 추석트래픽 {

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17676
	 * [분류] 구현
	 * [아쉬운 점]
	 * 1. 슬라이딩 윈도우로 하면 메모리나 시간 초과 발생
	 * 2. 구현 방법을 생각하지 못해서 삽질을 헀다.
	 * 3. 시간에 대한 범위 (0.001, 0.999) -> 시작 시간을 포함하지 않았다.
	 *
	 * @param lines "2016-09-15 20:59:57.421 0.351s"
	 * @return 1초간 최대 처리량을 구하라
	 */
	public int solution(String[] lines) {
		int answer = -1;
		List<Log> logs = initLogs(lines); //로그의 시작점과 끝점 구하기

		for (int i = 0; i < logs.size(); i++) {
			Log cur = logs.get(i);
			int count = 1;
			double curEnd = Double.parseDouble(String.format("%.3f", cur.end + 0.999));
			for (int j = 0; j < logs.size(); j++) {
				if (i != j) {
					double targetStart = logs.get(j).start;
					double targetEnd = logs.get(j).end;
					if (cur.end <= targetStart && targetStart <= curEnd) count++;
					else if (cur.end <= targetEnd && targetEnd <= curEnd) count++;
					else if (targetStart <= cur.end && cur.end <= targetEnd) count++;
				}
			}
			if (answer < count) answer = count;
		}
		return answer;
	}

	private List<Log> initLogs(String[] lines) {
		List<Log> result = new ArrayList<>();
		for (String line : lines) {
			result.add(new Log(line));
		}
		return result;
	}

	class Log {
		double start;
		double end;

		public Log(String log) { //"2016-09-15 20:59:57.421 0.351s"
			String[] split = log.split(" ");
			String[] time = split[1].split(":");

			this.end = (Integer.parseInt(time[0]) * 60 * 60) + (Integer.parseInt(time[1]) * 60) + (Double.parseDouble(time[2]));
			this.end = Double.parseDouble(String.format("%.3f", this.end));

			this.start = this.end - Double.parseDouble(split[2].substring(0, split[2].length() - 1)) + 0.001;
			this.start = Double.parseDouble(String.format("%.3f", this.start));
		}
	}

	@Test
	void test() {
		int solution = solution(new String[]{
						"2016-09-15 20:59:57.421 0.351s",
						"2016-09-15 20:59:58.233 1.181s",
						"2016-09-15 20:59:58.299 0.8s",
						"2016-09-15 20:59:58.688 1.041s",
						"2016-09-15 20:59:59.591 1.412s",
						"2016-09-15 21:00:00.464 1.466s",
						"2016-09-15 21:00:00.741 1.581s",
						"2016-09-15 21:00:00.748 2.31s",
						"2016-09-15 21:00:00.966 0.381s",
						"2016-09-15 21:00:02.066 2.62s"
		});

		Assertions.assertEquals(solution, 7);
	}

	@Test
	void test2() {
		int solution = solution(new String[]{
						"2016-09-15 01:00:04.002 2.0s",
						"2016-09-15 01:00:07.000 2s"
		});

		Assertions.assertEquals(solution, 2);
	}

	@Test
	void test3() {
		int solution = solution(new String[]{
						"2016-09-15 01:00:04.001 2.0s",
						"2016-09-15 01:00:07.000 2s"
		});

		Assertions.assertEquals(solution, 1);
	}
}
