package programmers.lv2;

public class 나라의숫자_124 {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int remainder;
        while(n != 0) {
            remainder = n % 3;
            n = n / 3;
            if (remainder == 0) {
                n--;
            }
            answer.insert(0, getNumberByRemainder(remainder));
        }
        return answer.toString();
    }

    private String getNumberByRemainder(int remainder) {
        String number = "";
        if (remainder == 0) number = "4";
        else if (remainder == 1) number = "1";
        else if (remainder == 2) number = "2";
        return number;
    }
}
