package practice.programmers.greedy;

public class 조이스틱 {

    public int solution(String requestName) {
        int answer = -1;
        char[] name = requestName.toCharArray();

        for (int i = 0; i < name.length; i++) {
            if (name[i] != 'A') {
                answer += Math.min(name[i] - 'A', 'Z' - name[i] + 1);
            } else {
                answer++;
            }

            if (i + 1 < name.length && name[i + 1] == 'A') {
                int findIndex = 0;
                for (int j = i + 1; j < name.length; j++) {
                    if (name[j] != 'A') {
                        findIndex = j;
                        break;
                    }
                }

                int right = findIndex - i;
                int min = Math.min(right, Math.abs((findIndex - name.length) - i));
                answer += min;
                i = findIndex - 1;
            } else {
                answer++;
            }
        }
        return answer;
    }
}
