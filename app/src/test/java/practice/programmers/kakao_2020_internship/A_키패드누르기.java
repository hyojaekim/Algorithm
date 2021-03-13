package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class A_키패드누르기 {

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
		StringBuilder result = new StringBuilder();
		Map<Integer, Point> numberPosition = initNumberPosition();
		Set<Integer> possibleLeftNumbers = new HashSet<>(Arrays.asList(1, 4, 7));
		Set<Integer> possibleRightNumbers = new HashSet<>(Arrays.asList(3, 6, 9));
		Hand currentHand = new Hand(numberPosition.get(-1), numberPosition.get(-2));

		for (int number : numbers) {
			if (possibleLeftNumbers.contains(number)) {
				Point point = numberPosition.get(number);
				String left = currentHand.setLeftPosition(point);
				result.append(left);
			} else if (possibleRightNumbers.contains(number)) {
				Point point = numberPosition.get(number);
				String right = currentHand.setRightPosition(point);
				result.append(right);
			} else {
				Point point = numberPosition.get(number);
				String nearHand = findNearHand(currentHand, point, hand);
				result.append(nearHand);
			}
		}
		return result.toString();
	}

	private Map<Integer, Point> initNumberPosition() {
		Map<Integer, Point> result = new HashMap<>();
		int[][] phoneNumbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};
		int height = phoneNumbers.length, weight = phoneNumbers[0].length;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				result.put(phoneNumbers[i][j], new Point(i, j));
			}
		}
		return result;
	}

	private String findNearHand(Hand currentHand, Point target, String hand) {
		int leftDistance = calculateDistance(target, currentHand.leftX, currentHand.leftY);
		int rightDistance = calculateDistance(target, currentHand.rightX, currentHand.rightY);

		if (leftDistance == rightDistance) return currentHand.setPosition(target, hand);
		else if (leftDistance > rightDistance) return currentHand.setRightPosition(target);
		else return currentHand.setLeftPosition(target);
	}

	private int calculateDistance(Point target, int x, int y) {
		return Math.abs((target.x - x)) + Math.abs((target.y - y));
	}

	class Hand {
		int leftX, leftY, rightX, rightY;

		public Hand(Point left, Point right) {
			this.leftX = left.x;
			this.leftY = left.y;
			this.rightX = right.x;
			this.rightY = right.y;
		}

		public String setPosition(Point point, String hand) {
			if (hand.equals("left")) {
				return setLeftPosition(point);
			} else {
				return setRightPosition(point);
			}
		}

		public String setLeftPosition(Point point) {
			this.leftX = point.x;
			this.leftY = point.y;
			return "L";
		}

		public String setRightPosition(Point point) {
			this.rightX = point.x;
			this.rightY = point.y;
			return "R";
		}
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
