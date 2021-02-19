package practice.programmers.lv3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 디스크컨트롤러 {

	@Test
	void test() {
		int solution = solution(new int[][]{{0, 3}, {1, 9}, {2, 6}});
		Assertions.assertEquals(solution, 9);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/42627
	 * [분류] 우선순위 큐
	 * [풀이]
	 * 1. 2개의 우선순위 큐를 만든다. (소요 시간 & 요청 시간 작은 순서)
	 * 2. 현재 시간보다 가장 작은 요청 시간이 더 크면 -> 요청PQ에서 계산
	 * 3. else -> 작업PQ에서 계산
	 *  -> 현재 시간보다 작거나 같고, 가장 작은 소요 시간을 가지는 작업을 꺼낸다.
	 *  -> 작업 시간 = (현재 시간 - 요청 시간) + 소요 시간
	 *  -> 현재 시간 = 현재 시간 + 소요 시간
	 *  -> 꺼낸 작업은 다시 작업PQ에 넣는다.
	 * [삽질]
	 * 1. while에 break를 빼먹어서 시간을 빼앗김.
	 * 2. 계산 로직이 잘못된 것을 파악하느라 시간을 빼앗김.
	 *
	 * @param jobs [0]:요청시간 [1]:소요시간
	 * @return 평균 처리 시간 반환
	 */
	public int solution(int[][] jobs) {
		int answer = 0;
		int currentTime = -1;
		List<PriorityQueue<Job>> totalPQ = initPQ(jobs);
		PriorityQueue<Job> requiredTimePQ = totalPQ.get(0);
		PriorityQueue<Job> requestedTimePQ = totalPQ.get(1);

		while (!requestedTimePQ.isEmpty()) {
			if (currentTime < requestedTimePQ.peek().requestedTime) {
				Job job = requestedTimePQ.poll();
				requiredTimePQ.remove(job);
				answer += job.requiredTime;
				currentTime = job.requestedTime + job.requiredTime;
			} else {
				Queue<Job> tempQ = new LinkedList<>();
				while (!requiredTimePQ.isEmpty()) {
					Job job = requiredTimePQ.poll();
					if (currentTime < job.requestedTime) {
						tempQ.add(job);
					} else {
						requestedTimePQ.remove(job);
						answer += ((currentTime - job.requestedTime) + job.requiredTime);
						currentTime += job.requiredTime;
						break;
					}
				}
				requiredTimePQ.addAll(tempQ);
			}
		}
		return answer / jobs.length;
	}

	private List<PriorityQueue<Job>> initPQ(int[][] jobs) {
		PriorityQueue<Job> requiredTimePQ = new PriorityQueue<>(
						Comparator.comparingInt((Job o) -> o.requiredTime).thenComparingInt(o -> o.requestedTime));
		PriorityQueue<Job> requestedTimePQ = new PriorityQueue<>(
						Comparator.comparingInt((Job o) -> o.requestedTime).thenComparingInt(o -> o.requiredTime));

		for (int[] job : jobs) {
			Job convertedJob = new Job(job[0], job[1]);
			requiredTimePQ.add(convertedJob);
			requestedTimePQ.add(convertedJob);
		}
		return Arrays.asList(requiredTimePQ, requestedTimePQ);
	}

	static class Job {
		int requestedTime; //요청 시간
		int requiredTime; //소요 시간

		public Job(int requestedTime, int requiredTime) {
			this.requestedTime = requestedTime;
			this.requiredTime = requiredTime;
		}
	}
}
