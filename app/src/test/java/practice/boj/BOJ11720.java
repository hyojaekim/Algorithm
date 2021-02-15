package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11720 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = Integer.valueOf(br.readLine());
		String str_number = br.readLine();
		int result = 0;
		for (int i = 0; i < max; i++) {
			result += Integer.valueOf(str_number.charAt(i) - '0');
		}
		System.out.println(result);
	}
}
