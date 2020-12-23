package practice.programmers.binary_search;

public class 입국심사 {

    public long solution(int n, int[] times) {
        long min = 1L;
        long max = 1_000_000_000L * 100_001L;

        while (min <= max) {
            long mid = (min + max) / 2;
            if (isBig(mid, times, n)) max = mid - 1L;
            else min = mid + 1L;
        }
        return min;
    }

    private boolean isBig(long totalTime, int[] times, int n) {
        long count = 0L;
        for (int time : times) {
            count += (totalTime / time);
        }
        return count >= n;
    }
}
