package practice.boj;

import java.util.Scanner;

public class BOJ9655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());

		if (n % 2 == 0) System.out.println("CY");
		else System.out.println("SK");
	}
}
