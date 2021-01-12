package practice.book.coding_interview.chapter14;

public class 이진트리의최대깊이 {

	/**
	 * [문제] https://leetcode.com/problems/maximum-depth-of-binary-tree/
	 * [방법] BFS
	 *
	 * @param binaryTree 이진트리로 왼쪽부터 채운다.
	 * @return 이진트리의 깊이를 반환한다.
	 */
	public int solution(int[] binaryTree) {
		Node root = new Node(3, null, null, 1);
		//자식 노드들을 연결시킨다.
		for (int nodeNum : binaryTree) {

		}
		return 1;
	}

	class Node {
		int num;
		Node[] childNodes;
		int depth;

		public Node(int num, Node firstNode, Node secondNode, int depth) {
			this.num = num;
			this.childNodes = new Node[]{firstNode, secondNode};
			this.depth = depth;
		}
	}
}
