package practice.programmers.kakao_2019_internship;

public class D_징검다리건너기 {

    public int solution(int[] stones, int k) {
        int min = 1;
        int max = 200000001;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (calculate(mid, stones, k)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return min;
    }

    private boolean calculate(int n, int[] stones, int k) {
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - n <= 0) count++;
            else count = 0;
            if (count >= k) return false;
        }
        return true;
    }
}
