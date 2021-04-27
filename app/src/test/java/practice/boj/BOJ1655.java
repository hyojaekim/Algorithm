package practice.boj;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(br.readLine());

			if (i % 2 == 0) maxHeap.add(number);
			else minHeap.add(number);

			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if (minHeap.peek() < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(minHeap.poll());
				}
			}
			bw.write(maxHeap.peek() + "\n");
		}

		br.close();
		bw.close();
	}
}
