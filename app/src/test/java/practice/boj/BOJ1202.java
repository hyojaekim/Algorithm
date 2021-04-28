package practice.boj;

import java.io.*;
import java.util.PriorityQueue;

public class BOJ1202 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		PriorityQueue<Gem> gemHeap = new PriorityQueue<>((o1, o2) -> {
			int n = o1.weight - o2.weight;
			if (n == 0) return o2.price - o1.price;
			return n;
		});
		PriorityQueue<Integer> bagHeap = new PriorityQueue<>();
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int k = Integer.parseInt(split[1]);

		for (int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");
			gemHeap.add(new Gem(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}

		for (int i = 0; i < k; i++) {
			bagHeap.add(Integer.parseInt(br.readLine()));
		}

		long solution = solution(gemHeap, bagHeap);
		bw.write(solution + "\n");

		bw.close();
		br.close();
	}

	public static long solution(PriorityQueue<Gem> gemHeap, PriorityQueue<Integer> bagHeap) {
		long result = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		while (!bagHeap.isEmpty()) {
			Integer bag = bagHeap.poll();

			while (!gemHeap.isEmpty()) {
				if (gemHeap.peek().weight > bag) break;
				pq.offer(gemHeap.poll().price);
			}

			if (!pq.isEmpty()) {
				result += pq.poll();
			}
		}
		return result;
	}

	static class Gem {
		int weight, price;

		public Gem(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
	}
}
