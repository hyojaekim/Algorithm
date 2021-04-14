package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ1010 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNumber = Integer.parseInt(br.readLine());
		int[][] numbers = new int[testCaseNumber][2];
		for (int i = 0; i < testCaseNumber; i++) {
			String[] split = br.readLine().split(" ");
			numbers[i][0] = Integer.parseInt(split[0]);
			numbers[i][1] = Integer.parseInt(split[1]);
		}

		BigInteger[] dp = new BigInteger[31];
		dp[0] = BigInteger.valueOf(0);
		dp[1] = BigInteger.valueOf(1);
		for (int i = 2; i <= 30; i++) {
			dp[i] = BigInteger.valueOf(i).multiply(dp[i - 1]);
		}

		for (int i = 0; i < testCaseNumber; i++) {
			int r = numbers[i][0];
			int n = numbers[i][1];

			if (n == r) {
				System.out.println(1);
			} else {
				System.out.println(dp[n].divide(dp[r].multiply(dp[n - r])));
			}
		}
	}
}
