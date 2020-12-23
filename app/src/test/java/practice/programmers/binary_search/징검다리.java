package practice.programmers.binary_search;

import java.util.Arrays;

public class 징검다리 {

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int result = 0;
        int left = 1, right = distance, mid, prev, cnt;

        while (left <= right) {
            mid = (left + right) / 2;
            prev = 0;
            cnt = 0;
            for (int rock : rocks) {
                if (rock - prev < mid) cnt++;
                else prev = rock;
            }
            if (distance - prev < mid) cnt++;

            if (cnt <= n) {
                result = Math.max(result, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
