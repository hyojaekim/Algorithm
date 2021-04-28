package practice.boj;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ4358 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<String, Integer> counter = new HashMap<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		int totalSize = 0;
		while (sc.hasNextLine()) {
			String tree = sc.nextLine();
			if (counter.containsKey(tree)) {
				counter.put(tree, counter.get(tree) + 1);
			} else {
				counter.put(tree, 1);
				pq.offer(tree);
			}
			totalSize++;
		}

		while (!pq.isEmpty()) {
			String tree = pq.poll();
			int treeCount = counter.get(tree);
			double result = ((double) treeCount / totalSize) * 100;
			String convertedResult = String.format("%.4f", result);
			System.out.println(tree + " " + convertedResult);
		}
	}
}
