package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1991 {

	@Test
	void test() {
		solution(7, new String[]{
						"A B C",
						"B D .",
						"C E F",
						"E . .",
						"F . G",
						"D . .",
						"G . .",
		});
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] info = new String[n];

		for (int i = 0; i < n; i++) {
			info[i] = br.readLine();
		}

//		Main main = new Main();
		BOJ1991 main = new BOJ1991();
		main.solution(n, info);
	}

	public void solution(int n, String[] info) {
		Node root = createBinaryTree(n, info);

		root.preorder();
		System.out.println();

		root.inorder();
		System.out.println();

		root.postorder();
	}

	private Node createBinaryTree(int n, String[] info) {
		Node root = new Node("A");
		for (String s : info) {
			String[] split = s.split(" ");
			Node node = root.find(split[0]);
			if (node != null) {
				if (!split[1].equals(".")) node.setLeft(split[1]);
				if (!split[2].equals(".")) node.setRight(split[2]);
			}
		}
		return root;
	}

	class Node {

		String v;
		Node left;
		Node right;

		public Node(String v) {
			this.v = v;
		}

		public Node find(String v) {
			if (this.v.equals(v)) return this;

			if (left != null) {
				Node node = left.find(v);
				if (node != null) return node;
			}
			if (right != null) {
				return right.find(v);
			}
			return null;
		}

		public void preorder() {
			System.out.print(this.v);
			if (this.left != null) this.left.preorder();
			if (this.right != null) this.right.preorder();
		}

		public void inorder() {
			if (this.left != null) this.left.inorder();
			System.out.print(this.v);
			if (this.right != null) this.right.inorder();
		}

		public void postorder() {
			if (this.left != null) this.left.postorder();
			if (this.right != null) this.right.postorder();
			System.out.print(this.v);
		}

		public void setLeft(String node) {
			left = new Node(node);
		}

		public void setRight(String node) {
			right = new Node(node);
		}
	}
}
