package practice.study;

import java.util.Stack;

public class 문자열_압축 {

    public int solution(String s) {
        int result = s.length();
        for (int n = 1; n <= s.length() / 2; n++) {
            result = Math.min(result, convert(n, s).length());
        }
        return result;
    }

    private String convert(int n, String s) {
        Stack<Piece> stack = new Stack<>();
        while (s.length() >= n) {
            String piece = s.substring(0, n);
            s = s.substring(n);
            if (stack.size() != 0 && stack.peek().isSame(piece)) {
                stack.push(stack.pop().plus());
            } else {
                stack.push(new Piece(piece));
            }
        }
        if (s.length() != 0) stack.push(new Piece(s));

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().toString());
        }
        return sb.toString();
    }

    class Piece {
        String s;
        int cnt;

        public Piece(String s) {
            this.s = s;
            this.cnt = 1;
        }

        public Piece plus() {
            this.cnt++;
            return this;
        }

        public boolean isSame(String s) {
            return this.s.equals(s);
        }

        @Override
        public String toString() {
            if (cnt == 1) return s;
            return cnt + s;
        }
    }
}
