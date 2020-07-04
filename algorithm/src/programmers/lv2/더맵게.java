package programmers.lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class 더맵게 {
    public int solution(int[] data, int K) {
        int count = 0;
        List<Integer> numbers = new ArrayList<>();
        for (int d : data) numbers.add(d);

        while (true) {
            Collections.sort(numbers);
            if (isEnd(numbers, K)) return count;
            if (numbers.size() < 2) break;
            int num = numbers.get(0) + (numbers.get(1) * 2);
            numbers.remove(0);
            numbers.remove(0);
            numbers.add(num);
            count++;
        }
        return -1;
    }

    private boolean isEnd(List<Integer> numbers, int k) {
        for (int number : numbers) {
            if (number < k) return false;
        }
        return true;
    }

    public int solution2(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int num : scoville) q.add(num);

        while (true) {
            if (q.size() == 1 && q.peek() < K) return -1;
            if (q.peek() > K) return answer;
            Integer firstNumber = q.poll();
            Integer secondNumber = q.poll();
            q.add(firstNumber + (secondNumber * 2));
            answer++;
        }
    }

}
