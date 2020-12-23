package practice.programmers.kakao_2019;

import java.util.ArrayList;
import java.util.List;

public class 실패율 {
    public int[] solution(int N, int[] userStages) {
        int[] answer = new int[N];
        List<Stage> stages = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int nowStage = i + 1;
            double x = 0, y = 0;
            for (int j = 0; j < userStages.length; j++) {
                int stopStage = userStages[j];
                if (stopStage >= nowStage) y++;
                if (stopStage == nowStage) x++;
            }
            if (x == 0) stages.add(new Stage(nowStage, 0));
            else stages.add(new Stage(nowStage, x / y));
        }

        stages.sort((o1, o2) -> {
            double temp = o2.failNumber - o1.failNumber;
            if (temp == 0.0) return o1.number - o2.number;
            else if (temp > 0) return 1;
            else return -1;
        });

        for (int i = 0; i < answer.length; i++) {
            answer[i] = stages.get(i).number;
        }
        return answer;
    }

    class Stage {
        int number;
        double failNumber;

        public Stage(int number, double failNumber) {
            this.number = number;
            this.failNumber = failNumber;
        }
    }
}
