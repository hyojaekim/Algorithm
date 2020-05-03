package programmers.greedy;

import java.util.Arrays;

public class 구명보트 {

    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int start = 0, end;

        for (end = people.length - 1; end >= start; end--) {
            if (people[end] + people[start] <= limit) {
                start++;
            }
            answer++;
        }
        return answer;
    }
}
