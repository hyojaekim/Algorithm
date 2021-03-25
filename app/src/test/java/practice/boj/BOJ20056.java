package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ20056 {

	@Test
	void test() {
		int solution = solution(4, 2, 2, new int[][]{{1, 1, 5, 2, 2}, {1, 4, 7, 1, 6}});

		Assertions.assertEquals(solution, 8);
	}

	int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	/**
	 * [문제] https://www.acmicpc.net/problem/20056
	 * [분류] 시뮬레이션
	 *
	 * @param n             NxN
	 * @param fireBallCount 파이어볼 개수
	 * @param k             k번 진행
	 * @param reqFireBalls  파이어볼 상태
	 * @return 남아있는 파이어볼의 질량의 합 반환
	 */
	public int solution(int n, int fireBallCount, int k, int[][] reqFireBalls) {
		Map<Point, List<FireBall>> map = initMap(reqFireBalls);
		while (k-- > 0) {
			map = moveAll(n, map);
			split(map);
		}
		return getResult(map);
	}

	private Map<Point, List<FireBall>> initMap(int[][] fireBalls) {
		Map<Point, List<FireBall>> result = new HashMap<>();
		for (int[] fireBall : fireBalls) {
			Point point = new Point(fireBall[0] - 1, fireBall[1] - 1);
			FireBall createdFireBall = new FireBall(fireBall[2], fireBall[3], fireBall[4]);
			result.computeIfAbsent(point, k -> new ArrayList<>()).add(createdFireBall);
		}
		return result;
	}

	private Map<Point, List<FireBall>> moveAll(int n, Map<Point, List<FireBall>> map) {
		Map<Point, List<FireBall>> result = new HashMap<>();
		for (Map.Entry<Point, List<FireBall>> entry : map.entrySet()) {
			Point point = entry.getKey();
			for (FireBall fireBall : entry.getValue()) {
				int speed = fireBall.speed % n;
				int dir = fireBall.dir;
				int x = (point.x + (dx[dir] * speed + n)) % n;
				int y = (point.y + (dy[dir] * speed + n)) % n;
				Point movingPoint = new Point(x, y);
				result.computeIfAbsent(movingPoint, k -> new ArrayList<>()).add(fireBall);
			}
		}
		return result;
	}

	private void split(Map<Point, List<FireBall>> map) {
		for (Map.Entry<Point, List<FireBall>> entry : map.entrySet()) {
			Point point = entry.getKey();
			List<FireBall> fireBalls = entry.getValue();
			if (fireBalls.size() < 2) continue;

			int totalMass = 0;
			int totalSpeed = 0;
			boolean isOddDir = false;
			boolean isEvenDir = false;

			for (FireBall fireBall : fireBalls) {
				totalMass += fireBall.mass;
				totalSpeed += fireBall.speed;
				if (fireBall.dir % 2 == 0) isEvenDir = true;
				else if (fireBall.dir % 2 == 1) isOddDir = true;
			}
			totalMass /= 5;
			totalSpeed /= fireBalls.size();

			fireBalls = new ArrayList<>();
			map.put(point, fireBalls); //리스트 모두 비우기
			if (totalMass != 0) { //질량 0이면 패스
				if (isOddDir != isEvenDir) { //4방향 리스트에 넣기
					fireBalls.add(new FireBall(totalMass, totalSpeed, 0));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 2));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 4));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 6));
				} else {
					fireBalls.add(new FireBall(totalMass, totalSpeed, 1));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 3));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 5));
					fireBalls.add(new FireBall(totalMass, totalSpeed, 7));
				}
			}
		}
	}

	private int getResult(Map<Point, List<FireBall>> map) {
		int result = 0;
		for (List<FireBall> fireBalls : map.values()) {
			for (FireBall fireBall : fireBalls) {
				result += fireBall.mass;
			}
		}
		return result;
	}


	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	class FireBall {
		int mass, speed, dir;

		public FireBall(int mass, int speed, int dir) {
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}
	}
}
