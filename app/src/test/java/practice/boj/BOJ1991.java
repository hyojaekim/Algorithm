package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1991 {

	@Test
	void test() {
		List<String> solution = solution(7, new String[]{
						"A B C",
						"B D .",
						"C E F",
						"E . .",
						"F . G",
						"D . .",
						"G . .",
		});

		Assertions.assertEquals(solution.get(0), "ABDCEFG");
		Assertions.assertEquals(solution.get(1), "DBAECFG");
		Assertions.assertEquals(solution.get(2), "DBEGFCA");
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
		List<String> solution = main.solution(n, info);
		for (String s : solution) {
			System.out.println(s);
		}
	}

	public List<String> solution(int n, String[] info) {
		List<String> result = new ArrayList<>();
		Node root = createBinaryTree(n, info);

		StringBuilder sb = new StringBuilder();
		root.preorder(sb);
		result.add(sb.toString());

		sb = new StringBuilder();
		root.inorder(sb);
		result.add(sb.toString());

		sb = new StringBuilder();
		root.postorder(sb);
		result.add(sb.toString());
		return result;
	}

	private Node createBinaryTree(int n, String[] info) {
		Node root = new Node("A");
		for (String s : info) {
			String[] split = s.split(" ");
			Node node = root.find(split[0]);
			if (node == null) continue;
			if (!split[1].equals(".")) node.setLeft(split[1]);
			if (!split[2].equals(".")) node.setRight(split[2]);
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

		public void preorder(StringBuilder sb) {
			sb.append(this.v);
			if (this.left != null) this.left.preorder(sb);
			if (this.right != null) this.right.preorder(sb);
		}

		public void inorder(StringBuilder sb) {
			if (this.left != null) this.left.inorder(sb);
			sb.append(this.v);
			if (this.right != null) this.right.inorder(sb);
		}

		public void postorder(StringBuilder sb) {
			if (this.left != null) this.left.postorder(sb);
			if (this.right != null) this.right.postorder(sb);
			sb.append(this.v);
		}

		public void setLeft(String node) {
			left = new Node(node);
		}

		public void setRight(String node) {
			right = new Node(node);
		}
	}
}
