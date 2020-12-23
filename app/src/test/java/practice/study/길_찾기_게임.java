package practice.study;

import java.util.ArrayList;
import java.util.List;

public class 길_찾기_게임 {

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = sort(nodeinfo);
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            addNode(root, nodes.get(i));
        }
        return getResult(nodes, root);
    }

    private List<Node> sort(int[][] nodes) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) result.add(new Node(nodes[i], i + 1));
        result.sort((o1, o2) -> {
            int o1x = o1.x, o1y = o1.y, o2x = o2.x, o2y = o2.y;
            if (o1y - o2y != 0) return o2y - o1y;
            return o1x - o2x;
        });
        return result;
    }

    private void addNode(Node cur, Node n) {
        if (cur.x > n.x) {
            if (cur.isEmptyLeft()) cur.left = n;
            else addNode(cur.left, n);
        } else if (cur.x < n.x) {
            if (cur.isEmptyRight()) cur.right = n;
            else addNode(cur.right, n);
        }
    }

    private int[][] getResult(List<Node> nodes, Node root) {
        int[][] result = new int[2][nodes.size()];
        List<Integer> preorderNumbers = new ArrayList<>();
        List<Integer> postorderNumbers = new ArrayList<>();
        preorder(preorderNumbers, root);
        postorder(postorderNumbers, root);

        for (int i = 0; i < nodes.size(); i++) {
            result[0][i] = preorderNumbers.get(i);
            result[1][i] = postorderNumbers.get(i);
        }
        return result;
    }

    private void postorder(List<Integer> numbers, Node node) {
        if (node != null) {
            postorder(numbers, node.left);
            postorder(numbers, node.right);
            numbers.add(node.num);
        }
    }

    private void preorder(List<Integer> numbers, Node node) {
        if (node != null) {
            numbers.add(node.num);
            preorder(numbers, node.left);
            preorder(numbers, node.right);
        }
    }

    class Node {
        int num;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int[] node, int num) {
            this.x = node[0];
            this.y = node[1];
            this.num = num;
        }

        public boolean isEmptyLeft() {
            return left == null;
        }

        public boolean isEmptyRight() {
            return right == null;
        }
    }
}