package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ13567 {

	@Test
	void test() {
		String solution = solution(11, 14, new String[]{
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

		System.out.println(solution);
	}

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};
	public String solution(int m, int n, String[] commands) {
		Robot robot = new Robot(m - 1, 0, 0);

		for (String command : commands) {
			String[] split = command.split(" ");
			if (split[0].equals("MOVE")) {
				if (robot.impossibleMove(m, split[1])) return "-1";
			} else {
				robot.rotate(split[1]);
			}
		}
		return (robot.x + 1) + " " + (robot.y + 1);
	}

	class Robot {
		int x, y, dir;

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		public void rotate(String number) {
			if (number.equals("1")) {
				dir = (dir + 1 >= 4) ? 0 : dir + 1;
			} else {
				dir = (dir - 1 < 0) ? 3 : dir - 1;
			}
		}

		public boolean impossibleMove(int m, String num) {
			int max = Integer.parseInt(num);
			for (int i = 0; i < max; i++) {
				x += dx[dir];
				y += dy[dir];
				if (x < 0 || y < 0 || x >= m || y >= m) return true;
			}
			return false;
		}
	}
}
