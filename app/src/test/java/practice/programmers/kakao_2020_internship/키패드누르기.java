package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class 키패드누르기 {

	@Test
	void test() {
		String result = solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
		Assertions.assertEquals(result, "LRLLLRLLRRL");
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/67256
	 * [분류] 구현
	 *
	 * @param numbers 숫자
	 * @param hand 왼손:left, 오른손:right
	 * @return 1,4,7(왼손) / 3,6,9(오른손) / 나머지(가까운손)
	 */
	public String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();
		Set<Integer> left = new HashSet<>();
		left.add(1); left.add(4); left.add(7);

		Set<Integer> right = new HashSet<>();
		right.add(3); right.add(6); right.add(9);

		boolean isLeftHand = hand.equals("left");
		Hand leftHand = new Hand(true);
		Hand rightHand = new Hand(false);

		for (int number : numbers) {
			boolean isLeft;
			if (left.contains(number)) {
				isLeft = true;
			} else if (right.contains(number)) {
				isLeft = false;
			} else {
				int leftDistance = leftHand.calculateDistance(number);
				int rightDistance = rightHand.calculateDistance(number);
				isLeft = (leftDistance == rightDistance) ? isLeftHand : leftDistance < rightDistance;
			}

			if (isLeft) {
				leftHand.setXY(number);
				answer.append("L");
			} else {
				rightHand.setXY(number);
				answer.append("R");
			}
		}
		return answer.toString();
	}

	class Hand {
		int x;
		int y;

		public Hand(boolean left) {
			this.x = 3;
			this.y = left ? 0 : 2;
		}

		public void setXY(int number) {
			if (number == 0) number = 11;
			this.x = (number - 1) / 3;
			this.y = (number - 1) % 3;
		}

		public int calculateDistance(int number) {
			if (number == 0) number = 11;
			int x2 = (number - 1) / 3;
			int y2 = (number - 1) % 3;
			return Math.abs(x - x2) + Math.abs(y - y2);
		}
	}
}
