package study;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class 무지의_먹방_라이브 {
    
    public int solution(int[] food_times, long k) {
        List<Food> foods = init(food_times);
        int n = foods.size();
        long preTime = 0;
        for (int i = 0; i < foods.size(); i++) {
            Food f = foods.get(i);
            long diff = f.time - preTime;
            if (diff != 0) {
                long spendTime = diff * n;
                if (k - spendTime >= 0) {
                    k -= spendTime;
                    preTime = f.time;
                } else {
                    k %= n;
                    foods.subList(i, foods.size()).sort(Comparator.comparingInt(o -> o.idx));
                    return foods.get(i + (int) k).idx;
                }
            }
            n--;
        }
        return -1;
    }

    private List<Food> init(int[] food_times) {
        List<Food> foods = new LinkedList<>();
        for (int i = 0; i < food_times.length; i++) {
            foods.add(new Food(i, food_times[i]));
        }
        foods.sort(Comparator.comparingInt(o -> o.time));
        return foods;
    }

    class Food {
        int idx;
        int time;

        public Food(int idx, int time) {
            this.idx = idx + 1;
            this.time = time;
        }
    }

}
