package programmers.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class 섬연결하기 {

    public int solution(int n, int[][] costs) {
        int answer = 0;
        Comparator<int[]> c = (a, b) -> a[2] - b[2];
        Arrays.sort(costs, c);
        boolean[] ok = new boolean[n];

        for (int[] cost : costs) {
            ok[cost[0]] = true;
            ok[cost[1]] = true;
            answer += cost[2];

            int count = 0;
            for (boolean b : ok) {
                if (b) {
                    count++;
                }
            }
            if (count == n) {
                return answer;
            }
        }
        return answer;
    }
}
