package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ13567 {

	@Test
	void test() {
		solution(11, 14, new String[]{
						"MOVE 10",
						"TURN 0",
						"MOVE 2",
						"TURN 0",
						"MOVE 5",
						"TURN 1",
						"MOVE 5",
						"TURN 1",
						"MOVE 2",
						"TURN 1",
						"MOVE 3",
						"TURN 0",
						"TURN 0",
						"MOVE 6"
		});
	}

	int[][] dx = {{0, -1, 0, 1}, {0, 1, 0, -1}};
	int[][] dy = {{1, 0, -1, 0}, {1, 0, -1, 0}};

	/**
	 * [문제] https://www.acmicpc.net/problem/13567
	 * [분류] 시뮬레이션
	 *
	 * @param M MxM
	 * @param n n개의 명령어
	 * @param orders 명령어 TURN(0, 1), MOVE
	 */
	public void solution(int M, int n, String[] orders) {
		Robot robot = new Robot(M - 1, 0, 0, 0);

		for (String order : orders) {
			String[] split = order.split(" ");
			if (split[0].equals("MOVE")) {
				if (!robot.move(M, Integer.parseInt(split[1]))) {
					System.out.println(-1);
					return;
				}
			} else {
				robot.turn(split[1]);
			}
		}
		System.out.println(robot.y + " " + (M - 1 - robot.x));
	}

	class Robot {
		int x, y;
		int dir;
		int leftOrRight;

		public Robot(int x, int y, int dir, int leftOrRight) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.leftOrRight = leftOrRight;
		}

		public boolean move(int M, int n) {
			this.x += dx[leftOrRight][dir] * n;
			this.y += dy[leftOrRight][dir] * n;
			return x >= 0 && y >= 0 && x < M && y < M;
		}

		public void turn(String dir) {
			if (dir.equals("0")) {
				turnLeft();
			} else {
				turnRight();
			}
		}

		public void turnLeft() {
			if (leftOrRight == 1) {
				if (dir == 1) dir = 3;
				else if (dir == 3) dir = 1;
			}

			dir = (dir + 1 == 4) ? 0 : dir + 1;
			leftOrRight = 0;
		}


		public void turnRight() {
			if (leftOrRight == 0) {
				if (dir == 1) dir = 3;
				else if (dir == 3) dir = 1;
			}

			dir = (dir + 1 == 4) ? 0 : dir + 1;
			leftOrRight = 1;
		}
	}
}
