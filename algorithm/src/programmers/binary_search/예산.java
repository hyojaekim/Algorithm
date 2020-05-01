package programmers.binary_search;

public class 예산 {

    public int solution(int[] budgets, int M) {
        int answer = 0;
        int min = 0;
        int max = 0;
        for (int budget : budgets) if (max < budget) max = budget;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (binarySearch(mid, budgets, M)) {
                max = mid - 1;
            } else {
                answer = mid;
                min = mid + 1;
            }
        }
        return answer;
    }

    private boolean binarySearch(int mid, int[] budgets, int M) {
        long sum = 0;
        for (int budget : budgets) sum += Math.min(budget, mid);
        return sum > M;
    }
}
