package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1713 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int pictureFrameCount = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		int[] orders = new int[n];
		for (int i = 0; i < n; i++) {
			orders[i] = Integer.parseInt(split[i]);
		}

		BOJ1713 main = new BOJ1713();
		List<Integer> solution = main.solution(pictureFrameCount, n, orders);
		StringJoiner sj = new StringJoiner(" ");
		for (Integer num : solution) {
			sj.add(String.valueOf(num));
		}
		System.out.println(sj);
	}

	public List<Integer> solution(int pictureFrameCount, int n, int[] nums) {
		Map<Integer, PictureFrame> pictureFrames = new HashMap<>();
		PriorityQueue<PictureFrame> pq = new PriorityQueue<>();

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (pictureFrames.containsKey(num)) {
				PriorityQueue<PictureFrame> tempPQ = new PriorityQueue<>();
				PictureFrame pictureFrame = pictureFrames.get(num);
				pictureFrame.recommend();
				tempPQ.add(pictureFrame);

				while (!pq.isEmpty()) {
					PictureFrame cur = pq.poll();
					if (cur.num != num) tempPQ.add(cur);
				}
				pq = tempPQ;
			} else {
				if (pq.size() >= pictureFrameCount) {
					PictureFrame pictureFrame = pq.poll();
					pictureFrames.remove(pictureFrame.num);
				}
				PictureFrame pictureFrame = new PictureFrame(num, i);
				pictureFrames.put(num, pictureFrame);
				pq.offer(pictureFrame);
			}
		}

		List<Integer> result = new ArrayList<>(pictureFrames.keySet());
		Collections.sort(result);
		return result;
	}

	static class PictureFrame implements Comparable<PictureFrame> {
		int num, like, order;

		public PictureFrame(int num, int order) {
			this.num = num;
			this.order = order;
			this.like = 1;
		}

		public void recommend() {
			this.like++;
		}

		@Override
		public int compareTo(PictureFrame o) {
			if (this.like == o.like) {
				return this.order - o.order;
			}
			return this.like - o.like;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			PictureFrame that = (PictureFrame) o;
			return num == that.num;
		}

		@Override
		public int hashCode() {
			return Objects.hash(num);
		}
	}
}
