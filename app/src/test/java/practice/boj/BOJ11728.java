package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class BOJ11728 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);

		int[] aNumbers = new int[n];
		split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			aNumbers[i] = Integer.parseInt(split[i]);
		}

		int[] bNumbers = new int[m];
		split = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			bNumbers[i] = Integer.parseInt(split[i]);
		}

		int idxA = 0, idxB = 0;
		StringJoiner sj = new StringJoiner(" ");

		while (idxA < n || idxB < m) {
			if (((idxA < n && idxB < m) && aNumbers[idxA] <= bNumbers[idxB]) || (idxA < n && idxB >= m)) {
				sj.add(String.valueOf(aNumbers[idxA++]));
			} else {
				sj.add(String.valueOf(bNumbers[idxB++]));
			}
		}
		System.out.println(sj);
	}
}
