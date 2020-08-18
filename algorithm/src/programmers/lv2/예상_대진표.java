package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

public class 예상_대진표 {

    public int solution1(int n, int a, int b) {
        int round = 0;
        while (a != b) {
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);
            round++;
        }
        return round;
    }

    public int solution2(int n, int a, int b) {
        List<Integer> numbers = init(n);
        int count = 1;
        while (true) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 1; i < numbers.size(); i = i + 2) {
                int preNum = numbers.get(i - 1);
                int crtNum = numbers.get(i);

                if ((crtNum == a && preNum == b) || (crtNum == b && preNum == a)) {
                    return count;
                } else if (crtNum == b || preNum == b) {
                    temp.add(b);
                } else if (crtNum == a || preNum == a) {
                    temp.add(a);
                } else {
                    temp.add(preNum);
                }
            }
            numbers = temp;
            count++;
        }
    }

    private List<Integer> init(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(i + 1);
        }
        return result;
    }
}
