package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2096 {

	@Test
	void test() {
		int[] solution = solution(3, new int[][]{
						{1, 2, 3},
						{4, 5, 6},
						{4, 9, 0},
		});
		System.out.println(Arrays.toString(solution));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][3];
		for (int i = 0; i < n; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		BOJ2096 main = new BOJ2096();
		int[] solution = main.solution(n, map);
		System.out.println(solution[0] + " " + solution[1]);
	}

	public int[] solution(int n, int[][] map) {
		int[][][] dp = init(n, map);

		for (int i = 1; i < n; i++) {//이전 0, 1, 2 위치 초기화
			int maxOne = dp[i - 1][0][0], maxTwo = dp[i - 1][1][0], maxThree = dp[i - 1][2][0];
			int minOne = dp[i - 1][0][1], minTwo = dp[i - 1][1][1], minThree = dp[i - 1][2][1];

			dp[i][0][0] = Math.max(maxOne, maxTwo) + map[i][0];
			dp[i][0][1] = Math.min(minOne, minTwo) + map[i][0];

			dp[i][1][0] = Math.max(maxOne, Math.max(maxTwo, maxThree)) + map[i][1];
			dp[i][1][1] = Math.min(minOne, Math.min(minTwo, minThree)) + map[i][1];

			dp[i][2][0] = Math.max(maxTwo, maxThree) + map[i][2];
			dp[i][2][1] = Math.min(minTwo, minThree) + map[i][2];
		}

		return getResult(n, dp);
	}

	private int[][][] init(int n, int[][] map) {
		int[][][] dp = new int[n][3][2]; //[0] 최대, [1] 최소
		for (int i = 0; i < 3; i++) {
			dp[0][i][0] = map[0][i];
			dp[0][i][1] = map[0][i];
		}
		return dp;
	}

	private int[] getResult(int n, int[][][] dp) {
		int max = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			max = Math.max(dp[n - 1][i][0], max);
			min = Math.min(dp[n - 1][i][1], min);
		}
		return new int[]{max, min};
	}
}
