package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1072 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int x = Integer.parseInt(split[0]);
		int y = Integer.parseInt(split[1]);

		int result = solution(x, y);
		System.out.println(result);
		br.close();
	}

	public static int solution(int x, int y) {
		int result = Integer.MAX_VALUE, left = 1, right = x, mid;
		int z = (int) ((double) y * 100 / x);
		if (z == 100) return -1;

		while (left <= right) {
			mid = (left + right) / 2;

			if (z == (int) ((double) (y + mid) * 100 / (x + mid))) {
				left = mid + 1;
			} else {
				result = Math.min(result, mid);
				right = mid - 1;
			}
		}

		return result == Integer.MAX_VALUE ? -1 : result;
	}
}
