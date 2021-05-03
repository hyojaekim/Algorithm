package practice.programmers.lv3;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class 추석트래픽 {

	@Test
	void test() {
		String[] lines = {
						"2016-09-15 01:00:04.001 2.0s",
						"2016-09-15 01:00:07.000 2s"
		};
		int solution = solution(lines);
		System.out.println(solution);
	}

	/*
		14:15
		1초간 -> 최대 처리량
		-> 응답완료시간:S 처리시간:T(소수점 3자리)
		-> 2016-09-15 03:10:33.020 0.011s -> 이때부터 0.011s 동안 처리된 요청
		-> 2016-09-15만 포함

		-> 시간을 -> start, end 변환
			-> double end -> h * 3600, m * 60, (s.000) -> double
			-> T -> s빼고 -> double -> 변환 -> 0.01 빼기
			-> double start -> end - T
		-> PQ : 끝나는 기점으로 정렬 and 같으면 start가 먼저
		-> 끝나는 기점(curStart)으로 1초(curEnd)간 개수 카운터
			-> curEnd < peek.start -> break
			-> curStart > peek.end -> poll();
			-> 카운터 & temp에 넣기 -> max 갱신
		-> temp -> PQ에 다시 넣기
	*/
	public int solution(String[] lines) {
		int answer = 1;
		PriorityQueue<Job> pq = initPQ(lines);
		while (!pq.isEmpty()) {
			Job cur = pq.poll();
			double startPoint = cur.end;
			double endPoint = startPoint + 1; //24시간 넘어가면..?

			int count = getResult(pq, startPoint, endPoint);
			answer = Math.max(answer, count);
		}
		return answer;
	}

	private int getResult(PriorityQueue<Job> pq, double startPoint, double endPoint) {
		int count = 1;
		PriorityQueue<Job> tempPQ = new PriorityQueue<>();

		while (!pq.isEmpty()) {
			Job target = pq.poll();
			if (endPoint <= target.start) {
				tempPQ.add(target);
			} else if (startPoint <= target.end) {
				count++;
				tempPQ.add(target);
			}
		}
		pq.addAll(tempPQ);
		return count;
	}

	private PriorityQueue<Job> initPQ(String[] lines) {
		PriorityQueue<Job> pq = new PriorityQueue<>();
		for (String log : lines) {
			pq.offer(new Job(log));
		}
		return pq;
	}

	class Job implements Comparable<Job> {
		double start, end;
		String log;

		public Job(String log) { //2016-09-15 01:00:04.001 2.0s
			String[] split = log.split(" ");
			this.log = log;
			this.end = getEnd(split[1]);
			this.start = getStart(end, split[2]);
		}

		private double getStart(double end, String s) { //2.0s
			s = s.substring(0, s.length() - 1); //2.0
			double t = Double.parseDouble(s);
			String format = String.format("%.3f", end + 0.001 - t);
			return Double.parseDouble(format);
		}

		private double getEnd(String s) { //01:00:04.001
			double result = 0;
			String[] split = s.split(":");
			result += Integer.parseInt(split[0]) * 3600;
			result += Integer.parseInt(split[1]) * 60;
			result += Double.parseDouble(split[2]);
			return result;
		}

		@Override
		public int compareTo(Job o) {
			int compare = Double.compare(end, o.end);
			if (compare == 0) return Double.compare(o.start, start);
			return compare;
		}
	}
}
