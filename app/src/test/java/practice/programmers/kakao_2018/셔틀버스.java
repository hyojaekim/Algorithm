package practice.programmers.kakao_2018;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 셔틀버스 {
	@Test
	void test() throws ParseException {
//		String solution = solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"});
//		Assertions.assertEquals(solution, "09:00");
		String solution2 = solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"});
		Assertions.assertEquals(solution2, "09:09");
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17678
	 *
	 * @param n 운행 횟수
	 * @param t 운행 간격
	 * @param m 태울 수 있는 최대 크루 수
	 * @param timetable 대기열에 도착하는 크루들 시간
	 * @return 제일 뒤에 대기열에 서는 시간
	 */
	public String solution(int n, int t, int m, String[] timetable) {
		List<Bus> buses = initBuses(n, t);
		PriorityQueue<Integer> users = initUsers(timetable);
		for (Bus bus : buses) {
			while (!users.isEmpty()) {
				if (bus.add(users.peek(), m)) users.poll();
				else break;
			}
		}
		Bus lastBus = buses.get(buses.size() - 1);
		int result;
		if (lastBus.users.size() < m) {
			result = lastBus.time;
		} else {
			result = lastBus.users.get(lastBus.users.size() - 1) - 1;
		}
		String hour = (result / 60) < 10 ? "0" + result / 60 : String.valueOf(result / 60);
		String minute = (result % 60) < 10 ? "0" + result % 60 : String.valueOf(result % 60);
		return hour + ":" + minute;
	}

	private PriorityQueue<Integer> initUsers(String[] timetable) {
		PriorityQueue<Integer> result = new PriorityQueue<>();
		for (String time : timetable) {
			String[] split = time.split(":");
			int convertedTime = (Integer.parseInt(split[0]) * 60) + (Integer.parseInt(split[1]));
			result.add(convertedTime);
		}
		return result;
	}

	private List<Bus> initBuses(int n, int t) {
		List<Bus> result = new ArrayList<>();
		int time = 9 * 60;
		for (int i = 0; i < n; i++) {
			result.add(new Bus(time + (i * t)));
		}
		return result;
	}

	class Bus {
		int time;
		List<Integer> users;

		public Bus(int time) {
			this.time = time;
			this.users = new ArrayList<>();
		}

		public boolean add(int userTime, int m) {
			if (users.size() >= m) return false;
			if (this.time < userTime) return false;
			users.add(userTime);
			return true;
		}
	}
}
