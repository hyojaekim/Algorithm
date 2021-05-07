package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(reverse(br.readLine()));
	}

	private static String reverse(String s) {
		boolean openBracket = false;
		StringBuilder sb = new StringBuilder();
		StringBuilder tempSB = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (!openBracket && s.charAt(i) == '<') {
				sb.append(tempSB.reverse());
				tempSB = new StringBuilder();
				openBracket = true;
				sb.append(s.charAt(i));
			} else if (openBracket) {
				sb.append(s.charAt(i));
				if (s.charAt(i) == '>') openBracket = false;
			} else if (s.charAt(i) == ' '){
				sb.append(tempSB.reverse());
				tempSB = new StringBuilder();
				sb.append(s.charAt(i));
			} else {
				tempSB.append(s.charAt(i));
			}
		}
		sb.append(tempSB.reverse());
		return sb.toString();
	}
}
