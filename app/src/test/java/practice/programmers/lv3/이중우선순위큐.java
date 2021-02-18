package practice.programmers.lv3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class 이중우선순위큐 {

	@Test
	void test() {
		int[] solution = solution(new String[]{"I 16", "D 1"});
		Assertions.assertArrayEquals(solution, new int[]{0, 0});

		int[] solution2 = solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
		Assertions.assertArrayEquals(solution2, new int[]{333, -45});
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/42628
	 * [분류] 우선순위큐
	 *
	 * 1. I (number) : number를 삽입
	 * 2. D 1 : 최댓값 삭제
	 * 3. D -1 : 최솟값 삭제
	 *
	 * @param operations 명령어
	 * @return 모든 명령어를 실행한 뒤 -> 최댓값, 최솟값을 반환하라.
	 */
	public int[] solution(String[] operations) {
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minPQ = new PriorityQueue<>();
		for (String operation : operations) {
			if (operation.startsWith("I")) { //숫자 삽입
				int num = Integer.parseInt(operation.split(" ")[1]);
				maxPQ.add(num);
				minPQ.add(num);
			} else if (operation.equals("D 1")) { //최대값 삭제
				minPQ.remove(maxPQ.poll());
			} else if (operation.equals("D -1")) { //최소값 삭제
				maxPQ.remove(minPQ.poll());
			}
		}
		if (maxPQ.isEmpty()) return new int[]{0, 0};
		else return new int[]{maxPQ.poll(), minPQ.poll()};
	}
}
