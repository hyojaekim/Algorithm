package programmers.lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
