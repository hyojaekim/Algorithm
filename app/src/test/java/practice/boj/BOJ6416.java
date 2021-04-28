package practice.boj;

import java.util.*;

public class BOJ6416 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 1;
		List<String> info = new ArrayList<>();
		while (true) {
			String s = sc.nextLine();
			if (s.equals("-1 -1")) break;

			String[] s1 = s.split("  ");
			for (String s2 : s1) {
				if (s2.isEmpty()) continue;
				info.add(s2);
			}

			if (s.endsWith("0 0")) {
				info.remove(info.size() - 1);
				System.out.println(solution(info) ? "Case " + count + " is a tree." : "Case " + count + " is not a tree.");
				info = new ArrayList<>();
				count++;
			}
		}
		sc.close();
	}

	public static boolean solution(List<String> info) {
		if (info.isEmpty()) return true;
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Set<Integer> childes = new HashSet<>();
		Set<Integer> nodes = new HashSet<>();

		for (String nodeInfo : info) {
			String[] s = nodeInfo.split(" ");
			int parent = Integer.parseInt(s[0]);
			int child = Integer.parseInt(s[1]);

			graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
			nodes.add(parent);
			nodes.add(child);
			if (childes.contains(child)) { //자식 노드를 2개 이상 가리키는 경우 false
				return false;
			}
			childes.add(child);
		}
		if (nodes.size() - childes.size() != 1) return false;
		//루트 노드 구하기
		int root = 0;
		for (Integer node : nodes) {
			if (childes.contains(node)) continue;
			root = node;
			break;
		}
		return bfs(graph, root, nodes.size());
	}

	public static boolean bfs(Map<Integer, List<Integer>> graph, int root, int nodeSize) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		q.offer(root);
		visited.add(root);

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			if (!graph.containsKey(cur)) continue;
			for (Integer child : graph.get(cur)) {
				if (visited.contains(child)) continue;
				visited.add(child);
				q.offer(child);
			}
		}
		return visited.size() == nodeSize;
	}
}
