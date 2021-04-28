package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ5639 {

	@Test
	void test() {
		solution(Arrays.asList(
						50,
						30,
						24,
						5,
						28,
						45,
						98,
						52,
						60
		));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> preorder = new ArrayList<>();

		while (sc.hasNextLine()) {
			int num = Integer.parseInt(sc.nextLine());
			preorder.add(num);
		}
		sc.close();

		solution(preorder);
	}

	public static void solution(List<Integer> preorder) {
		Node root = new Node(preorder.get(0));
		for (int i = 1; i < preorder.size(); i++) {
			root.add(preorder.get(i));
		}
		root.postorder();
	}

	static class Node {
		int num;
		Node left, right;

		public Node(int num) {
			this.num = num;
		}

		public void add(int num) {
			if (this.left == null && this.num >= num) {
				this.left = new Node(num);
			} else if (this.right == null && this.num < num) {
				this.right = new Node(num);
			} else if (this.num >= num) {
				this.left.add(num);
			} else {
				this.right.add(num);
			}
		}

		public void postorder() {
			if (this.left != null) this.left.postorder();
			if (this.right != null) this.right.postorder();
			System.out.println(this.num);
		}
	}
}
