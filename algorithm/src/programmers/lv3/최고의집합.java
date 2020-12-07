package programmers.lv3;

public class 최고의집합 {
    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};
        int defaultNumber = s / n;
        int remainder = s % n;

        int[] answer = new int[n--];
        for (; n >= 0; n--) {
            if (remainder > 0) {
                answer[n] = defaultNumber + 1;
                remainder--;
            } else {
                answer[n] = defaultNumber;
            }
        }
        return answer;
    }
}
