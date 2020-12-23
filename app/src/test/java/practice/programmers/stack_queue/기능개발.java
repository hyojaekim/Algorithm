package practice.programmers.stack_queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> days = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int remainderProgress = 100 - progresses[i];
            int day = remainderProgress / speeds[i];
            if (remainderProgress % speeds[i] != 0) {
                day++;
            }
            days.add(day);
        }
        List<Integer> result = new ArrayList<>();

        int count = 1;
        int maxDay = days.poll();
        while (!days.isEmpty()) {
            Integer day = days.poll();
            if (maxDay >= day) {
                count++;
            }

            if (maxDay < day) {
                maxDay = day;
                result.add(count);
                count = 1;
            }

            if (days.isEmpty()) {
                result.add(count);
            }
        }
        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
